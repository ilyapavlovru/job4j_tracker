package ru.job4j.nonblockingalgo.nonblockingcache;

public class Base {
    int id;
    int version;

    public Base(int id, int version) {
        this.id = id;
        this.version = version;
    }

    @Override
    public String toString() {
        return "Base{"
                + "id=" + id
                + ", version=" + version
                + '}';
    }
}
