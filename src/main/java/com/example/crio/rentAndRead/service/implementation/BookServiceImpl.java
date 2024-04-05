package com.example.crio.rentAndRead.service.implementation;

import com.example.crio.rentAndRead.dto.request.BookRequest;
import com.example.crio.rentAndRead.dto.request.RentBookRequest;
import com.example.crio.rentAndRead.entity.Book;
import com.example.crio.rentAndRead.entity.Rental;
import com.example.crio.rentAndRead.entity.User;
import com.example.crio.rentAndRead.exception.*;
import com.example.crio.rentAndRead.repository.BookRepository;
import com.example.crio.rentAndRead.repository.RentalRepository;
import com.example.crio.rentAndRead.repository.UserRepository;
import com.example.crio.rentAndRead.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    RentalRepository rentalRepository;

    @Autowired
    UserRepository userRepository;

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

    @Override
    public String rentBook(RentBookRequest rentBookRequest) throws UserNotFoundException, BookNotFoundException, BookRentedException, BookUnavailableException {

        User user = null;
        Optional<User> existingUser = userRepository.findById(rentBookRequest.getUserId());
        if (existingUser.isPresent()) {
            user = existingUser.get();
        }
        else {
            throw new UserNotFoundException("Book not found with id: " + rentBookRequest.getUserId());
        }

        Book book = null;
        Optional<Book> existingBook = bookRepository.findById(rentBookRequest.getBookId());
        if (existingBook.isPresent()) {
            book = existingBook.get();
        }
        else {
            throw new BookNotFoundException("Book not found with id: " + rentBookRequest.getUserId());
        }

        if(book.getAvailability_status() == false){
            throw new BookUnavailableException("Book is not available");
        }

        if(rentalRepository.existsByUserAndBook(user, book)){
            throw new BookRentedException("Book is already rented");
        }

        Rental rental = new Rental();
        rental.setBook(book);
        rental.setUser(user);
        rentalRepository.save(rental);

        return book.getTitle()+" Rented By " + user.getFirstName() + " " + user.getLastName();
    }

    @Override
    public String returnBook(RentBookRequest rentBookRequest) throws UserNotFoundException, BookNotFoundException, BookNotRentedException {

        User user = null;
        Optional<User> existingUser = userRepository.findById(rentBookRequest.getUserId());
        if (existingUser.isPresent()) {
            user = existingUser.get();
        }
        else {
            throw new UserNotFoundException("Book not found with id: " + rentBookRequest.getUserId());
        }

        Book book = null;
        Optional<Book> existingBook = bookRepository.findById(rentBookRequest.getBookId());
        if (existingBook.isPresent()) {
            book = existingBook.get();
        }
        else {
            throw new BookNotFoundException("Book not found with id: " + rentBookRequest.getUserId());
        }

        if(!rentalRepository.existsByUserAndBook(user, book)){
            throw new BookNotRentedException("Book is not Rented");
        }

        rentalRepository.deleteByUserAndBook(user, book);

        return book.getTitle()+" Returned By " + user.getFirstName() + " " + user.getLastName();
    }
}
