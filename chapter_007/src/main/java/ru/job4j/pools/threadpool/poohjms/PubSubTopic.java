package ru.job4j.pools.threadpool.poohjms;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class PubSubTopic {

    private final ConcurrentHashMap<String, ConcurrentHashMap<String, BlockingQueue<Message>>> map;

    public PubSubTopic() {
        this.map = new ConcurrentHashMap<>();
    }

    public Message add(String clientUserAgent, Message message) {

        // идентифицируем клиента: новый или старый
        ConcurrentHashMap<String, BlockingQueue<Message>> oldClientMap = map.get(clientUserAgent);

        // если ключа с таким клиентом нет (новый клиент)
        if (oldClientMap == null) {

            // добавляем клиенту новый топик и новую пустую очередь
            subscribeClientToMessageTopic(clientUserAgent, message);

            // также, всем клиентам, у которых есть этот топик, в очередь добавляется новое сообщение
            addMessageToAllTopicSubscribers(message);

            // старый клиент
        } else {
            // если топик сообщения уже есть, то сообщение добавляется в очередь данного топика
            BlockingQueue<Message> clientTopicQueue = oldClientMap.get(message.getName());
            if (clientTopicQueue != null) {
                clientTopicQueue.add(message);
            } else {
                // добавляем клиенту новый топик и новую пустую очередь
                subscribeClientToMessageTopic(clientUserAgent, message);
            }

            // также, всем клиентам, у которых есть этот топик, в очередь добавляется новое сообщение
            addMessageToAllTopicSubscribers(message);
        }

        return message;
    }

    // подписка клиента на топик
    private void subscribeClientToMessageTopic(String clientUserAgent, Message message) {
        ConcurrentHashMap<String, BlockingQueue<Message>> newTopicMap = new ConcurrentHashMap<>();
        BlockingQueue<Message> newQueue = new LinkedBlockingQueue<>(10);
        newTopicMap.put(message.getName(), newQueue);
        map.put(clientUserAgent, newTopicMap);
    }

    // рассылка сообщения всем подписчикам топика
    private void addMessageToAllTopicSubscribers(Message message) {
        // пробегаем по всем клиентам
        for (Map.Entry<String, ConcurrentHashMap<String, BlockingQueue<Message>>> entry : map.entrySet()) {
            String client = entry.getKey();
            ConcurrentHashMap<String, BlockingQueue<Message>> clientTopicMap = entry.getValue();
            // проверяем, есть ли у текущего клиента топик
            BlockingQueue<Message> clientTopicQueue = clientTopicMap.get(message.getName());
            if (clientTopicQueue != null) {
                clientTopicQueue.add(message);
            }
        }
    }
}
