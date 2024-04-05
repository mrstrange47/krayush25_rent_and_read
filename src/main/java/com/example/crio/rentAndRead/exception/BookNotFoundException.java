package com.example.crio.rentAndRead.exception;

public class BookNotFoundException extends Exception{
    public BookNotFoundException(String msg){
        super(msg);
    }
}
