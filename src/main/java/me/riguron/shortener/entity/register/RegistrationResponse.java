package me.riguron.shortener.entity.register;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;


public class RegistrationResponse {


    @JsonProperty
    private String url;


    public RegistrationResponse() {
    }

    public RegistrationResponse(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RegistrationResponse)) {
            return false;
        }
        RegistrationResponse that = (RegistrationResponse) o;
        return Objects.equals(getUrl(), that.getUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUrl());
    }

    @Override
    public String toString() {
        return "RegistrationResponse{"
                 +
                "url='" + url + '\''
                +
                '}';
    }
}
