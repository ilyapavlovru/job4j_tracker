package ru.job4j.synchronize.userstorage;

public class UserStorageApp {
    public static void main(String[] args) {
        UserStorage stoge = new UserStorage();
        User user1 = new User(1, 100);
        User user2 = new User(2, 200);
        stoge.add(user1);
        stoge.add(user2);
        System.out.println(stoge.getUsers());

        stoge.transfer(1, 2, 50);
        System.out.println(stoge.getUsers());

        user1.setAmount(1000);
        stoge.update(user1);
        System.out.println(stoge.getUsers());

        stoge.delete(user1);
        System.out.println(stoge.getUsers());
    }
}
