package me.riguron.shortener.exception;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Data
@Setter(AccessLevel.NONE)
class ApiError {

    private HttpStatus status;
    private List<String> errors;

    ApiError(HttpStatus status, List<String> errors) {
        this.status = status;
        this.errors = errors;
    }

    ApiError(HttpStatus status, String error) {
        this.status = status;
        this.errors = Collections.singletonList(error);
    }

}