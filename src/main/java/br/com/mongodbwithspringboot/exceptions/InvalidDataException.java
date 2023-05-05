package br.com.mongodbwithspringboot.exceptions;

public class InvalidDataException extends Exception{

    public InvalidDataException(String message) {
        super(message);
    }

}
