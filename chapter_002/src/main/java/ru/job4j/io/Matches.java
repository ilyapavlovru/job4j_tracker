package ru.job4j.io;

import java.util.Scanner;

public class Matches {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean run = true;
        int numMatches = 11;
        int x = 0;
        while (run) {
            //  Сначала первый игрок вводит число от 1 до 3, потом второй и так далее.
            int player = x++ % 2;
            System.out.println("Ходит игрок №: " + player);
            System.out.print("Введите число от 1 до 3: ");
            // сколько забрал спичек игрок
            int select = Integer.parseInt(input.nextLine());
            numMatches -= select;
            System.out.println("Количество оставшихся спичек: " + numMatches);
            if (numMatches <= 0 ) {
                System.out.println("Игра завершена. Последние спички забрал игрок " + player);
                run = false;
            }
        }
    }
}
