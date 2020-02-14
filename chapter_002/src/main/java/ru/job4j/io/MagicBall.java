package ru.job4j.io;

import java.util.Random;
import java.util.Scanner;

public class MagicBall {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Я великий Оракул. Что ты хочешь узнать? ");
        String name = input.nextLine();
        int answer = new Random().nextInt(3);
        System.out.println("answer = " + answer);
        switch (answer) {
            case 0:
                System.out.print("Да");
                break;
            case 1:
                System.out.print("Нет");
                break;
            default:
                System.out.print("Может быть");
        }
    }
}
