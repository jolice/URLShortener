package me.riguron.shortener.entity.register;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@NoArgsConstructor
@Data
@Setter(AccessLevel.NONE)
public class URLRegistration {

    private static final int DEFAULT_REDIRECT_TYPE = 302;

    @NotBlank(message = "URL can not be blank!")
    @NotNull(message = "URL is not specified!")
    private String url;

    public URLRegistration(@NotBlank(message = "URL can not be blank!") @NotNull(message = "URL is not specified!") String url, @Min(value = 301, message = "Redirect type must be either 301 or 302") @Max(value = 302, message = "Redirect type must be either 301 or 302") int redirectType) {
        this.url = url;
        this.redirectType = redirectType;
    }

    @JsonProperty
    @Min(value = 301, message = "Redirect type must be either 301 or 302")
    @Max(value = 302, message = "Redirect type must be either 301 or 302")
    private int redirectType = DEFAULT_REDIRECT_TYPE;


}
