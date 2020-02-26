package ru.job4j.interview.Static;


public class Test {

    public static void main(String args[ ])  {

        Base obj1 = new Derived();

        // Согласно переопределяющим правилам это должно вызывать статический класс Derive
        // переопределенный метод. Поскольку статический метод не может быть переопределен, он
        // вызывает отображение базы ()
        obj1.display();


        // Здесь работает переопределение и вызывается функция print () Derive
        obj1.print();

    }

}