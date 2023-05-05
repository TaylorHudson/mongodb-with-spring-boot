package br.com.mongodbwithspringboot.exceptions;

public class BadRequestException extends Exception {

    public BadRequestException(String message) {
        super(message);
    }

}
