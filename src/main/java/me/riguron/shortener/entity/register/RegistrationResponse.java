package me.riguron.shortener.entity.register;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
public class RegistrationResponse {

    @JsonProperty
    private String url;

    public RegistrationResponse(String url) {
        this.url = url;
    }


}
