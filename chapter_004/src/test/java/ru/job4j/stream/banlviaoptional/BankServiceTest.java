package ru.job4j.stream.banlviaoptional;

import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BankServiceTest {
    @Test
    public void addUser() {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        assertThat(bank.findByPassport("3434"), is(Optional.of(user)));
    }

    @Test
    public void findByPassportButThereIsNoUser() {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        assertThat(bank.findByPassport("34341"), is(Optional.empty()));
    }

    @Test
    public void addAccount() {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("5546", 150D));
        assertThat(bank.findByRequisite("3434", "5546").get().getBalance(), is(150D));
    }

    @Test
    public void transferMoney() {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("5546", 150D));
        bank.addAccount(user.getPassport(), new Account("113", 50D));
        bank.transferMoney(user.getPassport(), "5546", user.getPassport(), "113", 150D);
        assertThat(bank.findByRequisite(user.getPassport(), "113").get().getBalance(), is(200D));
    }

    @Test
    public void transferMoneyAndDestRequisiteNotFound() {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("5546", 150D));
        bank.addAccount(user.getPassport(), new Account("113", 50D));
        boolean result = bank.transferMoney(user.getPassport(), "5546", user.getPassport(), "112", 150D);
        assertThat(result, is(false));
    }

    @Test
    public void transferMoneyForDifferentUsers() {
        User user1 = new User("3434", "Petr Arsentev");
        User user2 = new User("1234", "Ilya Pavlov");
        BankService bank = new BankService();
        bank.addUser(user1);
        bank.addUser(user2);
        bank.addAccount(user1.getPassport(), new Account("5546", 150D));
        bank.addAccount(user2.getPassport(), new Account("113", 50D));
        bank.transferMoney(user1.getPassport(), "5546", user2.getPassport(), "113", 150D);
        assertThat(bank.findByRequisite(user2.getPassport(), "113").get().getBalance(), is(200D));
    }
}