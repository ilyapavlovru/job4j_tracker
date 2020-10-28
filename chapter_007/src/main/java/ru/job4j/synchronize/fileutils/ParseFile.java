package ru.job4j.synchronize.fileutils;

import java.io.*;
import java.util.function.Predicate;

public class ParseFile {
    private File file;

    public synchronized void setFile(File f) {
        file = f;
    }

    public synchronized File getFile() {
        return file;
    }

    public static Predicate<Integer> isNotUnicode() {
        return data -> data < 0x80;
    }

    public static Predicate<Integer> isAllData() {
        return data -> true;
    }

    public synchronized String getContent(Predicate<Integer> predict) {
        StringBuilder output = new StringBuilder();
        try (InputStream i = new FileInputStream(file)) {
            int data;
            while ((data = i.read()) > 0) {
                if (predict.test(data)) {
                    output.append((char) data);
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return output.toString();
    }
}
