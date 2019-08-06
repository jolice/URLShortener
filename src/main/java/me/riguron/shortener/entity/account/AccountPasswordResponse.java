package me.riguron.shortener.entity.account;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Setter(AccessLevel.NONE)
@NoArgsConstructor
public class AccountPasswordResponse extends AccountResponse {

    private String password;

    public AccountPasswordResponse(boolean success, String description, String password) {
        super(success, description);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
