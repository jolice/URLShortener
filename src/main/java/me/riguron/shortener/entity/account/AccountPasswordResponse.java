package me.riguron.shortener.entity.account;

import java.util.Objects;


public class AccountPasswordResponse extends AccountResponse {


    private String password;

    public AccountPasswordResponse() {
    }

    public AccountPasswordResponse(boolean success, String description, String password) {
        super(success, description);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AccountPasswordResponse)) {
            return false;
        }
        AccountPasswordResponse that = (AccountPasswordResponse) o;
        return Objects.equals(getPassword(), that.getPassword());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getPassword());
    }

    @Override
    public String toString() {
        return "AccountPasswordResponse{"
                +
                "password='" + password + '\''
                +
                '}';
    }
}
