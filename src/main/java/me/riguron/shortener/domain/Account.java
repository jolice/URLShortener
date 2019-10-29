package me.riguron.shortener.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Setter(AccessLevel.NONE)
public class Account {

    @Id
    @Column
    private String accountId;

    @Column
    private String password;

    public Account(String accountId, String password) {
        this.accountId = accountId;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

}
