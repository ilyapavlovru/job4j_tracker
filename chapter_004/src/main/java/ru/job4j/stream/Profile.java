package ru.job4j.stream;

public class Profile {
    private String surname;
    private Address address;

    public Profile(String surname, Address address) {
        this.surname = surname;
        this.address = address;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Profile{"
                + "surname='" + surname + '\''
                + ", address=" + address
                + '}';
    }
}