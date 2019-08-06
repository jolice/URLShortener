package me.riguron.shortener.exception;

import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class ApiError {


    private HttpStatus status;


    private List<String> errors;

    public ApiError(HttpStatus status, List<String> errors) {
        this.status = status;
        this.errors = errors;
    }

    public ApiError(HttpStatus status, String error) {
        this.status = status;
        this.errors = Collections.singletonList(error);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public List<String> getErrors() {
        return errors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApiError)) {
            return false;
        }
        ApiError apiError = (ApiError) o;
        return getStatus() == apiError.getStatus()
                &&
                Objects.equals(getErrors(), apiError.getErrors());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getStatus(), getErrors());
    }

    @Override
    public String toString() {
        return "ApiError{"
                +
                "status=" + status
                +
                ", errors=" + errors
                +
                '}';
    }
}