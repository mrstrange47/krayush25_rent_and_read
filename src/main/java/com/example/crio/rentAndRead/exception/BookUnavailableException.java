package com.example.crio.rentAndRead.exception;

public class BookUnavailableException extends Exception{
    public BookUnavailableException(String msg){
        super(msg);
    }
}
