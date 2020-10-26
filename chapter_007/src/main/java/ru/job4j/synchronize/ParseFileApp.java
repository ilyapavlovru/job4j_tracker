package ru.job4j.synchronize;

import java.io.File;

public class ParseFileApp {
    public static void main(String[] args) {
        ParseFile file = new ParseFile();
        file.setFile(new File("D:/readme.txt"));
        String fileContent = file.getContent();
        System.out.println(fileContent);
        file.saveContent("text to write to file");
    }
}
