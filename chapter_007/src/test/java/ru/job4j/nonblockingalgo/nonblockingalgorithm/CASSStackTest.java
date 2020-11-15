package ru.job4j.nonblockingalgo.nonblockingalgorithm;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class CASSStackTest {
    @Test
    public void when3PushThen3Poll() {
        CASSStack<Integer> CASSStack = new CASSStack<>();
        CASSStack.push(1);
        CASSStack.push(2);
        CASSStack.push(3);
        assertThat(CASSStack.poll(), is(3));
        assertThat(CASSStack.poll(), is(2));
        assertThat(CASSStack.poll(), is(1));
    }

    @Test
    public void when1PushThen1Poll() {
        CASSStack<Integer> CASSStack = new CASSStack<>();
        CASSStack.push(1);
        assertThat(CASSStack.poll(), is(1));
    }

    @Test
    public void when2PushThen2Poll() {
        CASSStack<Integer> CASSStack = new CASSStack<>();
        CASSStack.push(1);
        CASSStack.push(2);
        assertThat(CASSStack.poll(), is(2));
        assertThat(CASSStack.poll(), is(1));
    }
}