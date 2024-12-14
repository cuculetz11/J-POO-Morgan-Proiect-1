package org.poo.entities.user;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.Getter;
import lombok.Setter;
import org.poo.entities.bankAccount.Account;
import org.poo.fileio.UserInput;

import java.util.*;

@Setter
@Getter
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private Map<String, Account> accounts;

    public User(final UserInput userInput) {
        this.firstName = userInput.getFirstName();
        this.lastName = userInput.getLastName();
        this.email = userInput.getEmail();
        this.accounts = new LinkedHashMap<>();
    }
    @JsonGetter("accounts")
    public List<Account> getAccountsAsList() {
        return new ArrayList<>(new LinkedHashSet<>(accounts.values()));
    }

}
