package io.fnmz.cinema.services;

public record Result<T>(boolean success, String message, T value) {

    public static <T> Result<T> ok(T value) {
        return new Result<>(true, "OK", value);
    }

    public static <T> Result<T> fail(String message) {
        return new Result<>(false, message, null);
    }
}
