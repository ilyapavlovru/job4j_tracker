package ru.job4j.interview.inner;

class Outer {
    int outer_x = 100;

    void test() {
        Inner inner = new Inner();
        inner.display();
    }

    void showy() {
        // ошибка. печать переменной "y" не скомпилируется, т.к.
        // внешний класс не имеет доступа к членам вложенного класса
        // System.out.println(y);

        // Доступ к членам вложенного класса возможен только через ссылку на экземпляр вложенного класса
        Inner inner = new Inner();
        System.out.println("вывод локальной переменной y класса Inner = " + inner.y);
    }

    // это внутренний класс
    class Inner {
        int y = 10;  // переменная класса Inner
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