package ru.job4j.pools.threadpool.poohjms;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class TopicPooh {

    private final ConcurrentHashMap<String, ConcurrentHashMap<String, BlockingQueue<Message>>> map;

    public TopicPooh() {
        this.map = new ConcurrentHashMap<>();
    }

    // подписка консумера на топик или чтение сообщения
    public Message take(String clientUserAgent, String topicName) {

        // идентифицируем консумера: новый или старый
        ConcurrentHashMap<String, BlockingQueue<Message>> oldConsumerMap = map.get(clientUserAgent);

        // если ключа с таким консумером нет (новый консумер)
        if (oldConsumerMap == null) {

            // подписка консумера на топик
            subscribeConsumerToMessageTopic(clientUserAgent, topicName);

            // старый клиент
        } else {

            // если у консумера такой топик уже есть, то читаем сообщение из очереди и удаляем сообщение
            BlockingQueue<Message> consumerTopicQueue = oldConsumerMap.get(topicName);
            if (consumerTopicQueue != null) {
                return consumerTopicQueue.poll();
            } else {
                // подписка консумера на топик
                subscribeConsumerToMessageTopic(clientUserAgent, topicName);
            }
        }
        return null;
    }

    // рассылка сообщения всем подписчикам топика
    public Message add(Message message) {

        // всем консумерам, у которых есть этот топик, в очередь добавляется новое сообщение
        addMessageToAllTopicSubscribers(message);

        return message;
    }

    // подписка консумера на топик
    private void subscribeConsumerToMessageTopic(String clientUserAgent, String topicName) {

        ConcurrentHashMap<String, BlockingQueue<Message>> oldConsumerMap = map.get(clientUserAgent);

        // если ключа с таким консумером нет (новый консумер)
        if (oldConsumerMap == null) {
            ConcurrentHashMap<String, BlockingQueue<Message>> newTopicMap = new ConcurrentHashMap<>();
            newTopicMap.put(topicName, new LinkedBlockingQueue<>(10));
            map.put(clientUserAgent, newTopicMap);
        } else {
            oldConsumerMap.put(topicName, new LinkedBlockingQueue<>(10));
        }
    }

    // рассылка сообщения всем подписчикам топика
    private void addMessageToAllTopicSubscribers(Message message) {
        // пробегаем по всем консумерам
        for (Map.Entry<String, ConcurrentHashMap<String, BlockingQueue<Message>>> entry : map.entrySet()) {
            ConcurrentHashMap<String, BlockingQueue<Message>> consumerTopicMap = entry.getValue();
            // проверяем, есть ли у текущего консумера топик
            BlockingQueue<Message> consumerTopicQueue = consumerTopicMap.get(message.getName());
            if (consumerTopicQueue != null) {
                consumerTopicQueue.add(message);
            }
        }
    }
}
