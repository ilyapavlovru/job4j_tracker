package ru.job4j.pools.threadpool.poohjms;

public class Message {

    private String topic;
    private String text;

    public Message(String name, String text) {
        this.topic = name;
        this.text = text;
    }

    public String getTopic() {
        return topic;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Message{" +
                "type='" + topic + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
