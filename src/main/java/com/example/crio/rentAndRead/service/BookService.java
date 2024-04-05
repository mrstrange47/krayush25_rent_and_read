package com.example.crio.rentAndRead.service;

import com.example.crio.rentAndRead.dto.request.BookRequest;
import com.example.crio.rentAndRead.dto.request.RentBookRequest;
import com.example.crio.rentAndRead.entity.Book;
import com.example.crio.rentAndRead.entity.Rental;
import com.example.crio.rentAndRead.exception.*;

import java.util.List;

public interface BookService {
    Book addBook(Book book);
    List<Book> findAllBooks();
    Book getBookById(Long bookId) throws BookNotFoundException;
    Book updateBookById(Long bookId, BookRequest bookRequest) throws BookNotFoundException;
    String deleteBookById(Long bookId) throws BookNotFoundException;
    String rentBook(RentBookRequest rentBookRequest) throws UserNotFoundException, BookNotFoundException, BookRentedException, BookUnavailableException;
    String returnBook(RentBookRequest rentBookRequest) throws UserNotFoundException, BookNotFoundException, BookNotRentedException;
}
