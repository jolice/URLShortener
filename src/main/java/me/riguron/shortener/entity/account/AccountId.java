package me.riguron.shortener.entity.account;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;


public class AccountId {


    @JsonProperty("AccountId")
    private String id;


    public AccountId() {
    }

    public AccountId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AccountId)) {
            return false;
        }
        AccountId accountId = (AccountId) o;
        return Objects.equals(getId(), accountId.getId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "AccountId{"
                +
                "id='" + id + '\''
                +
                '}';
    }
}
