package ru.job4j.pools.threadpool.poohjms;

public class Message {

    String type;
    String text;

    public Message(String name, String text) {
        this.type = name;
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Message{" +
                "type='" + type + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
