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

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
