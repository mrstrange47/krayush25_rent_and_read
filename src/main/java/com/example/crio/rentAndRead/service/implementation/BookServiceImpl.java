package com.example.crio.rentAndRead.service.implementation;

import com.example.crio.rentAndRead.dto.request.BookRequest;
import com.example.crio.rentAndRead.entity.Book;
import com.example.crio.rentAndRead.exception.BookNotFoundException;
import com.example.crio.rentAndRead.repository.BookRepository;
import com.example.crio.rentAndRead.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long bookId) throws BookNotFoundException {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            return optionalBook.get();
        }
        else {
            throw new BookNotFoundException("Book not found with id: " + bookId);
        }
    }

    @Override
    public Book updateBookById(Long bookId, BookRequest bookRequest) throws BookNotFoundException {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            Book existingBook = optionalBook.get();
            existingBook.setTitle(bookRequest.getTitle());
            existingBook.setAuthor(bookRequest.getAuthor());
            existingBook.setGenre(bookRequest.getGenre());
            existingBook.setAvailability_status(bookRequest.getAvailability_status());
            return bookRepository.save(existingBook);
        } else {
            throw new BookNotFoundException("Book not found with id: " + bookId);
        }
    }

    @Override
    public String deleteBookById(Long bookId) throws BookNotFoundException {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            bookRepository.deleteById(bookId);
            return "Book with id " + bookId + " has been deleted successfully.";
        }
        else{
            throw new BookNotFoundException("Book not found with id: " + bookId);
        }
    }
}
