package com.revolut.repository;


import com.revolut.Account;
import com.revolut.exceptions.ObjectNotFoundException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AccountRepositoryInMemory implements AccountRepository {

    private List<Account> accounts = new ArrayList<>(0);


    public AccountRepositoryInMemory(){
        initValues();
    }



    @Override
    public List<Account> getAccounts() {
        return accounts;
    }

    @Override
    public Account getBy(String id) {
        return accounts.stream().
                filter(account -> account.getId().equals(id)).
                findFirst().
                orElseThrow(() -> new ObjectNotFoundException(String.format("Acount {} does not exist", id)));
    }

    @Override
    public Account update(Account from, Account to) {

        final List<Account> accountToUpdate = new ArrayList<>(Arrays.asList(from, to));
        List<Account> listToUpdate = accounts.stream()
                .filter(e -> accountToUpdate.stream().map(Account::getId).anyMatch(id -> id.equals(e.getId())))
                .collect(Collectors.toList());
        accounts.removeAll(listToUpdate);
        accounts.addAll(accountToUpdate);

        return to;
    }

    private void initValues() {
        accounts.add(new Account("asdfr124-323-ddf33", new BigDecimal(1000.50)));
        accounts.add(new Account("asdfr1uj-456-ggf33", new BigDecimal(10500)));

        accounts.add(new Account("asdfrooo-874-ddf33", new BigDecimal(31000.45)));
        accounts.add(new Account("asdfrkkk-908-dtf53", new BigDecimal(15500)));

        accounts.add(new Account("gfdhgthg-928-dki53", new BigDecimal(0)));
        accounts.add(new Account("dfgthrhh-968-dyh53", new BigDecimal(-200)));
    }
}
