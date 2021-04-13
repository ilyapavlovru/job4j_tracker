package ru.job4j.nonblockingalgo.nonblockingalgorithm;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class CASSStackTest {
    @Test
    public void when3PushThen3Poll() {
        CASSStack<Integer> cassStack = new CASSStack<>();
        cassStack.push(1);
        cassStack.push(2);
        cassStack.push(3);
        assertThat(cassStack.poll(), is(3));
        assertThat(cassStack.poll(), is(2));
        assertThat(cassStack.poll(), is(1));
    }

    @Test
    public void when1PushThen1Poll() {
        CASSStack<Integer> cassStack = new CASSStack<>();
        cassStack.push(1);
        assertThat(cassStack.poll(), is(1));
    }

    @Test
    public void when2PushThen2Poll() {
        CASSStack<Integer> cassStack = new CASSStack<>();
        cassStack.push(1);
        cassStack.push(2);
        assertThat(cassStack.poll(), is(2));
        assertThat(cassStack.poll(), is(1));
    }
}