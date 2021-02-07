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

    public Message take(String clientUserAgent, String topicName) {
        ConcurrentHashMap<String, BlockingQueue<Message>> oldConsumerMap = map.get(clientUserAgent);
        if (oldConsumerMap != null) {
            BlockingQueue<Message> consumerTopicQueue = oldConsumerMap.get(topicName);
            if (consumerTopicQueue != null) {
                return consumerTopicQueue.poll();
            }
        }
        subscribeConsumerToMessageTopic(clientUserAgent, topicName);
        return null;
    }

    public Message add(Message message) {
        addMessageToAllTopicSubscribers(message);
        return message;
    }

    private void subscribeConsumerToMessageTopic(String clientUserAgent, String topicName) {
        ConcurrentHashMap<String, BlockingQueue<Message>> oldConsumerMap = map.get(clientUserAgent);
        if (oldConsumerMap == null) {
            ConcurrentHashMap<String, BlockingQueue<Message>> newTopicMap = new ConcurrentHashMap<>();
            newTopicMap.put(topicName, new LinkedBlockingQueue<>(10));
            map.put(clientUserAgent, newTopicMap);
        } else {
            oldConsumerMap.put(topicName, new LinkedBlockingQueue<>(10));
        }
    }

    private void addMessageToAllTopicSubscribers(Message message) {
        for (Map.Entry<String, ConcurrentHashMap<String, BlockingQueue<Message>>> entry : map.entrySet()) {
            ConcurrentHashMap<String, BlockingQueue<Message>> consumerTopicMap = entry.getValue();
            BlockingQueue<Message> consumerTopicQueue = consumerTopicMap.get(message.getName());
            if (consumerTopicQueue != null) {
                consumerTopicQueue.add(message);
            }
        }
    }
}
