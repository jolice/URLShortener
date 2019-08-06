package me.riguron.shortener.entity.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
public class AccountId {

    @JsonProperty("AccountId")
    private String id;

    public AccountId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
