package ru.job4j.synchronize.fileutils;

import java.io.*;

public class ParseFile {
    private File file;

    public synchronized void setFile(File f) {
        file = f;
    }

    public synchronized File getFile() {
        return file;
    }

    public synchronized String getContent(boolean readWithoutUnicode) {
        StringBuilder output = new StringBuilder();
        try (InputStream i = new FileInputStream(file)) {
            int data;
            if (readWithoutUnicode) {
                while ((data = i.read()) > 0) {
                    if (data < 0x80) {
                        output.append((char) data);
                    }
                }
            } else {
                while ((data = i.read()) > 0) {
                    output.append((char) data);
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return output.toString();
    }
}
