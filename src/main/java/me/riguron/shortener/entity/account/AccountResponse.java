package me.riguron.shortener.entity.account;

import java.util.Objects;


public class AccountResponse {


    private boolean success;


    private String description;


    public AccountResponse() {
    }

    public AccountResponse(boolean success, String description) {
        this.success = success;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public boolean isSuccess() {
        return success;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AccountResponse)) {
            return false;
        }
        AccountResponse that = (AccountResponse) o;
        return isSuccess() == that.isSuccess()
                &&
                Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {

        return Objects.hash(isSuccess(), getDescription());
    }

    @Override
    public String toString() {
        return "AccountResponse{"
                +
                "success=" + success
                +
                ", description='" + description + '\''
                +
                '}';
    }
}
