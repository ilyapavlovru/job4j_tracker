package ru.job4j.pools.threadpool.emailservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class EmailNotification {

    private ExecutorService pool;

    EmailNotification() {
        this.pool = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );
    }

    public void emailTo(User user) {
        String subject = "Notification " + user.getUserName() + " to email " + user.getEmail();
        String body = "Add new event to " + user.getUserName();
        pool.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Execute " + Thread.currentThread().getName());
                send(subject, body, user.getEmail());
            }
        });
        close();
    }

    private void send(String subject, String body, String email) {
    }

    private void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
