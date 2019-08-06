package me.riguron.shortener.entity.register;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;


public class URLRegistration {


    private static final int DEFAULT_REDIRECT_TYPE = 302;


    @NotBlank(message = "URL can not be blank!")
    @NotNull(message = "URL is not specified!")
    private String url;

    public URLRegistration(@NotBlank(message = "URL can not be blank!") @NotNull(message = "URL is not specified!") String url, @Min(value = 301, message = "Redirect type must be either 301 or 302") @Max(value = 302, message = "Redirect type must be either 301 or 302") int redirectType) {
        this.url = url;
        this.redirectType = redirectType;
    }


    public URLRegistration() {
    }


    @JsonProperty
    @Min(value = 301, message = "Redirect type must be either 301 or 302")
    @Max(value = 302, message = "Redirect type must be either 301 or 302")
    private int redirectType = DEFAULT_REDIRECT_TYPE;

    public String getUrl() {
        return url;
    }

    public int getRedirectType() {
        return redirectType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof URLRegistration)) {
            return false;
        }
        URLRegistration that = (URLRegistration) o;
        return getRedirectType() == that.getRedirectType()
                &&
                Objects.equals(getUrl(), that.getUrl());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getUrl(), getRedirectType());
    }

    @Override
    public String toString() {
        return "URLRegistration{"
                +
                "url='" + url + '\''
                +
                ", redirectType=" + redirectType
                +
                '}';
    }
}
