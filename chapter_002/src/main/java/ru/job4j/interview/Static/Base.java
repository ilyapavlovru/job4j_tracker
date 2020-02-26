package ru.job4j.interview.Static;


// Суперкласс
class Base {

    // Статический метод в базовом классе, который будет скрыт в подклассе
    public static void display() {
        System.out.println("Static or class method from Base");
    }

    // Нестатический метод, который будет переопределен в производном классе
    public void print()  {
        System.out.println("Non-static or Instance method from Base");
    }

}


// Подкласс
class Derived extends Base {

    // Этот метод скрывает display () в Base
    public static void display() {
        System.out.println("Static or class method from Derived");
    }

    // Этот метод переопределяет print () в Base
    public void print() {
        System.out.println("Non-static or Instance method from Derived");
    }

}


