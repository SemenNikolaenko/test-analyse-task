package com.test.count.letters.demo.errors;

public class EmptyWordException extends RuntimeException {
    /**
     * создает новое исключение если на вход поступает пустая строка
     * @param message
     */
    public EmptyWordException(String message) {
        super(message);
    }
}
