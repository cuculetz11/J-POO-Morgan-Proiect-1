package org.poo.entities.user;

import com.fasterxml.jackson.annotation.JsonGetter;
import org.poo.entities.bankAccount.Account;
import org.poo.fileio.UserInput;

import java.util.*;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private Map<String, Account> accounts;

    public User(UserInput userInput) {
        this.firstName = userInput.getFirstName();
        this.lastName = userInput.getLastName();
        this.email = userInput.getEmail();
        this.accounts = new LinkedHashMap<>();
    }
    @JsonGetter("accounts")
    public List<Account> getAccountsAsList() {
        return new ArrayList<>(new LinkedHashSet<>(accounts.values()));
    }
    public String getEmail() {
        return email;
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Map<String, Account> accounts) {
        this.accounts = accounts;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
