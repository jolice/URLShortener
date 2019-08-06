package me.riguron.shortener.entity.account;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
public class AccountResponse {

    private boolean success;
    private String description;

    public AccountResponse(boolean success, String description) {
        this.success = success;
        this.description = description;
    }


}
