package ru.job4j.pools.threadpool.emailservice;

public class EmailServiceUsage {
    public static void main(String[] args) {
        User user = new User("Ilya", "forcety@mail.ru");
        EmailNotification emailNotification = new EmailNotification();
        emailNotification.emailTo(user);
    }
}
