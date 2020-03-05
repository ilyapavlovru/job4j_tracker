package ru.job4j.interview.inner;

class Outer {
    int outer_x = 100;

    void test() {
        Inner inner = new Inner();
        inner.display();
    }

    void showy() {
        Inner inner = new Inner();
        System.out.println("вывод локальной переменной y класса Inner = " + inner.y);
    }

    // это внутренний класс
    class Inner {
        private int y = 10;  // локальная переменная класса Inner
        void display() {
            System.out.println("вывод outer_x = " + outer_x);
        }
    }
}

class InnerClassDemo {
    public static void main(String[] args) {
        Outer outer = new Outer();
        outer.test();
        outer.showy();

        Outer.Inner inner = outer.new Inner();
        inner.display();
    }
}