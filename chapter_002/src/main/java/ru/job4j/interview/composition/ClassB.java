package ru.job4j.interview.composition;

public class ClassB extends SuperClass {
    @Override
    public void doSomething(){
        System.out.println("Какая-то реализация класса B");
    }
    //собственный метод класса  ClassB
    public void methodB(){
    }
}