package ru.job4j.interview.interfaces;

public class InterfacesImpl implements InterfaceA, InterfaceB, InterfaceC {

    @Override
    public void doSomething() {
        System.out.println("doSomething реализация реального класса ");
    }

    public static void main(String[] args) {
        InterfaceA objA = new InterfacesImpl();
        InterfaceB objB = new InterfacesImpl();
        InterfaceC objC = new InterfacesImpl();

        //все вызываемые ниже методы получат одинаковую реализацию конкретного класса

        objA.doSomething();
        objB.doSomething();
        objC.doSomething();
    }
}