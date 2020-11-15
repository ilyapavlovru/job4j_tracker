package ru.job4j.whaitnotifynotifyall.waitnotifyqueue;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Queue;

public class SimpleBlockingQueueTest {

    @Test
    public void whenProducerOfferMoreItems() throws InterruptedException {
        SimpleBlockingQueue simpleBlockingQueue = new SimpleBlockingQueue<Integer>();
        Thread producerThread = new Thread(new Producer(simpleBlockingQueue), "ProducerThread");
        Thread consumerThread = new Thread(new Consumer(simpleBlockingQueue), "ConsumerThread");
        producerThread.start();
        consumerThread.start();
        producerThread.join();
        consumerThread.join();
        Queue queue = simpleBlockingQueue.getQueue();
        assertThat(queue.size(), is(1));
    }
}