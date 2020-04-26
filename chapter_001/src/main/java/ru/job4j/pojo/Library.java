package ru.job4j.pojo;

public class Library {
    public static void main(String[] args) {
        Book book0 = new Book("Zolotoi Telenok", 500);
        Book book1 = new Book("Clean code", 1000);
        Book book2 = new Book("Transerfing realnosti", 600);
        Book book3 = new Book("Opit duraka", 400);

        Book[] books = new Book[4];

        books[0] = book0;
        books[1] = book1;
        books[2] = book2;
        books[3] = book3;

        for (int index = 0; index < books.length; index++) {
            Book bk = books[index];
            System.out.println(bk.getName() + " - " + bk.getPages());
        }

        System.out.println("Exchange books[0] and books[0].");
        Book tmp = books[0];
        books[0] = books[2];
        books[2] = tmp;

        for (int index = 0; index < books.length; index++) {
            Book bk = books[index];
            System.out.println(bk.getName() + " - " + bk.getPages());
        }

        System.out.println("Shown only Clean code books");
        for (int index = 0; index < books.length; index++) {
            Book bk = books[index];
            if (bk.getName().equals("Clean code")) {
                System.out.println(bk.getName() + " - " + bk.getPages());
            }
        }
    }
}
