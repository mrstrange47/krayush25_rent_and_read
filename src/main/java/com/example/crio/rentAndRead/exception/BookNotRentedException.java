package com.example.crio.rentAndRead.exception;

public class BookNotRentedException extends Exception{
    public BookNotRentedException(String msg){
        super(msg);
    }
}
