package ru.job4j.lambda;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PhoneDictionary {
    private ArrayList<Person> persons = new ArrayList<Person>();

    public void add(Person person) {
        this.persons.add(person);
    }

    public ArrayList<Person> find(String key) {
        Predicate<Person> p1 = s -> s.getName().contains(key);
        Predicate<Person> p2 = s -> s.getSurname().contains(key);
        Predicate<Person> p3 = s -> s.getAddress().contains(key);
        Predicate<Person> p4 = s -> s.getPhone().contains(key);
        Predicate<Person> combine = p1.or(p2).or(p3).or(p4);

        ArrayList<Person> result = new ArrayList<>();
        for (Person person : persons) {
            if (combine.test(person)) {
                result.add(person);
            }
        }
        return result;
    }
}