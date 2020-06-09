package ru.job4j.stream.banlviaoptional;

import java.util.*;

public class BankService {
    private Map<Optional<User>, List<Account>> users = new HashMap<>();

    public void addUser(User user) {
        users.putIfAbsent(Optional.ofNullable(user), new ArrayList<>());
    }

    public void addAccount(String passport, Account account) {
        Optional<User> user = findByPassport(passport);
        if (user.isPresent()) {
            // получаем ссылку на аккаунты привязанные к пользователю
            List<Account> accounts = users.get(user);
            if (!accounts.contains(account)) {
                accounts.add(account);
            }
        }
    }

    public Optional<User> findByPassport(String passport) {
        Optional<User> rsl;
        rsl = users.keySet().stream()
                .filter(
                        user -> {
                            if (user.isPresent()) {
                                return user.get().getPassport().equals(passport);
                            }
                            return false;
                        }
                )
                .findAny()
                .orElse(Optional.empty());

        return rsl;
    }

    /**
     * Поиск банковского счета по паспорту клиента и реквизитам счета
     */
    public Optional<Account> findByRequisite(String passport, String requisite) {
        Optional<Account> rsl = Optional.empty();
        Optional<User> user = findByPassport(passport);
        if (user.isPresent()) {
            List<Account> accounts = users.get(user);
            rsl = accounts.stream()
                    .filter(account -> account.getRequisite().equals(requisite))
                    .findAny();
        }
        return rsl;
    }

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean rsl = false;

        Optional<Account> srcAccount = findByRequisite(srcPassport, srcRequisite);
        Optional<Account> destAccount = findByRequisite(destPassport, destRequisite);

        if (srcAccount.isEmpty() || destAccount.isEmpty()) {
            return false;
        }

        double srcNewBalance = srcAccount.get().getBalance() - amount;
        if (srcNewBalance >= 0) {
            srcAccount.get().setBalance(srcNewBalance);
            destAccount.get().setBalance(destAccount.get().getBalance() + amount);
            rsl = true;
        }

        return rsl;
    }
}