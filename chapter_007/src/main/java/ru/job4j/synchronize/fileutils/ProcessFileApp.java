package ru.job4j.synchronize.fileutils;

import java.io.File;

import static ru.job4j.synchronize.fileutils.ParseFile.isNotUnicode;

public class ProcessFileApp {
    public static void main(String[] args) {
        ParseFile file = new ParseFile();
        file.setFile(new File("D:/readme.txt"));
        String fileContent = file.getContent(isNotUnicode());
        System.out.println(fileContent);

        SaveFile saveFile = new SaveFile();
        saveFile.setFile(new File("D:/output.txt"));
        saveFile.saveContent("text to write to file");
    }
}
