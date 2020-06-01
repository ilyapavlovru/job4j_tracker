package ru.job4j.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class BookUsageTest {

    @Test
    public void whenConvertFromListToMap() {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("The Fellowship of the Ring", 1954, "0395489318"));
        bookList.add(new Book("The Two Towers", 1954, "0345339711"));
        bookList.add(new Book("The Return of the King", 1955, "0618129111"));
        BookUsage convertToMap = new BookUsage();
        assertTrue(convertToMap.listToMap(bookList).size() == 3);
    }

    @Test(expected = IllegalStateException.class)
    public void whenMapHasDuplicateKeyWithoutMergeFunctionThenRuntimeException() {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("The Fellowship of the Ring", 1954, "0395489318"));
        bookList.add(new Book("The Two Towers", 1954, "0345339711"));
        bookList.add(new Book("The Return of the King", 1955, "0618129111"));
        BookUsage convertToMap = new BookUsage();
        convertToMap.listToMapWithDupKeyError(bookList);
    }

    @Test
    public void whenMapHasDuplicateKeyThenMergeFunctionHandlesCollision() {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("The Fellowship of the Ring", 1954, "0395489318"));
        bookList.add(new Book("The Two Towers", 1954, "0345339711"));
        bookList.add(new Book("The Return of the King", 1955, "0618129111"));
        BookUsage convertToMap = new BookUsage();
        Map<Integer, Book> booksByYear = convertToMap.listToMapWithDupKey(bookList);
        assertEquals(2, booksByYear.size());
        assertEquals("0395489318", booksByYear.get(1954).getIsbn());
    }
}