package ru.job4j.interview.exceptions;

import java.io.IOException;

class Foo {
    public void doStuff(int y, String s) {
        System.out.println("doStuff in Foo");
    }

    public void moreThings(int x) {
    }
}

class Bar extends Foo {
    public void doStuff(int y, long s) throws IOException {
        System.out.println("doStuff in Bar");
    }
}

public class Test {
    public static void main(String[] args) {
        Foo foo = new Bar();
        foo.doStuff(1, "1");
    }
}