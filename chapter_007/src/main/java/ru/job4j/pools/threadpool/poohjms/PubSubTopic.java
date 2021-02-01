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
        ConcurrentHashMap<String, BlockingQueue<Message>> topicMap = map.get(clientUserAgent);

        // если ключа с таким клиентом нет (новый клиент)
        if (topicMap == null) {
            // то добавляем ключ клиента, топик и первое сообщение в топике
            ConcurrentHashMap<String, BlockingQueue<Message>> newTopicMap = new ConcurrentHashMap<>();
            BlockingQueue<Message> newQueue = new LinkedBlockingQueue<>(10);
            // добавляем первое сообщение в эту новую очередь
            //newQueue.add(message);
            newTopicMap.put(message.getName(), newQueue);
            map.put(clientUserAgent, newTopicMap);

            // также, всем клиентам, у которых есть этот топик, в очередь добавляется новое сообщение
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

        } else {

        }

        return message;
    }
}
