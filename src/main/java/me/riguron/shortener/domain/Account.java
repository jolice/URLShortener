package me.riguron.shortener.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Account {

    @Id
    @Column
    private String accountId;

    @Column
    private String password;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "account_id")
    private Set<ShortenedUrl> shortenedUrls = new HashSet<>();

    public Account() {
    }

    public Account(String accountId, String password) {
        this.accountId = accountId;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public Set<ShortenedUrl> getShortenedUrls() {
        return shortenedUrls;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Account)) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(accountId, account.accountId)
                &&
                Objects.equals(getPassword(), account.getPassword())
                &&
                Objects.equals(getShortenedUrls(), account.getShortenedUrls());
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, getPassword(), getShortenedUrls());
    }

    @Override
    public String toString() {
        return "Account{"
                +
                "accountId='" + accountId + '\''
                +
                ", password='" + password + '\''
                +
                ", shortenedUrls=" + shortenedUrls
                +
                '}';
    }
}
