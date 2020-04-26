package ru.job4j.interview.constructor;

class Toy {
    public void printName() {
        System.out.println("Toy");
    }
}

class Doll extends Toy {
    public void printName() {
        System.out.println("Doll");
    }

    public void voice() {
        System.out.println("La");
    }
}

public class TestToys {
    public static void main(String[] args) {
        Toy c = new Doll();
        // этот код не будет компилироваться: в классе Toy нет объявления метода voice, поэтому
        // через ссылочную переменную так обратиться нельзя
        //c.voice(); // ошибка!

        Doll d = new Doll();
        d.voice();
    }
}