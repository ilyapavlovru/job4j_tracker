package ru.job4j.bank;

import ru.job4j.collection.Citizen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankService {
    private Map<User, List<Account>> users = new HashMap<>();

    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<Account>());
    }

    public void addAccount(String passport, Account account) {
        if (passport != null) {
            User user = findByPassport(passport);
            List<Account> accounts = users.get(user);
            int ind = accounts.indexOf(account);
            if (accounts.indexOf(account) == -1) {
                accounts.add(account);
            }
            users.put(user, accounts);
        }
    }

    public User findByPassport(String passport) {
        User rst = null;
        for (User user : users.keySet()) {
            if (passport != null && user.getPassport().equals(passport)) {
                rst = user;
                break;
            }
        }
        return rst;
    }

    public Account findByRequisite(String passport, String requisite) {
        Account rst = null;
        User user = findByPassport(passport);
        List<Account> accounts = users.get(user);
        for (Account account : accounts) {
            if (account.getRequisite().equals(requisite)) {
                rst = account;
            }
        }
        return rst;
    }

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String dеstRequisite, double amount) {
        boolean rsl = false;

        Account srcAccount = findByRequisite(srcPassport, srcRequisite);
        Account destAccount = findByRequisite(destPassport, dеstRequisite);

        if (srcAccount == null || destAccount == null) {
            return false;
        }

        double srcNewBalance = srcAccount.getBalance() - amount;
        if (srcNewBalance >= 0) {
            srcAccount.setBalance(srcNewBalance);
            destAccount.setBalance(destAccount.getBalance() + amount);
            rsl = true;
        }

        return rsl;
    }

    public static void main(String[] args) {
        List<Account> accounts = new ArrayList<>();
        String requisite = "3fdsbb9";
        accounts.add(new Account("3fdsbb9", 140));
        int index = accounts.indexOf(new Account(requisite, -1));
        Account find = accounts.get(index);
        System.out.println(find.getRequisite() + " -> " + find.getBalance());
    }
}