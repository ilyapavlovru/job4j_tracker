package ru.job4j.oop;

public class Jukebox {
    public void music(int position) {
        if (position == 1) {
            System.out.println("Пусть бегут неуклюже пешеходы по лужам...");
        } else if (position == 2) {
            System.out.println("Спят усталые игрушки, книжки спят...");
        } else {
            System.out.println("Песня не найдена");
        }
    }
    public static void main(String[] args) {
        Jukebox jukebox1 = new Jukebox();
        jukebox1.music(1);
        jukebox1.music(2);
        jukebox1.music(3);
    }
}
