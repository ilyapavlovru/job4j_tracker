package ru.job4j.oop;

public class DummyDic {
    public String engToRus(String eng) {
        String result = "Неизвестное слово " + eng;
        return result;
    }
    public static void main(String[] args) {
        DummyDic dd = new DummyDic();
        String string = dd.engToRus("jukebox");
        System.out.println("Translator says " + string);
    }
}
