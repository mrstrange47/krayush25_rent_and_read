package com.example.crio.rentAndRead.service;

import com.example.crio.rentAndRead.dto.request.RentBookRequest;
import com.example.crio.rentAndRead.entity.Book;
import com.example.crio.rentAndRead.entity.User;
import com.example.crio.rentAndRead.exception.BookNotFoundException;
import com.example.crio.rentAndRead.exception.BookUnavailableException;
import com.example.crio.rentAndRead.exception.UserNotFoundException;
import com.example.crio.rentAndRead.repository.BookRepository;
import com.example.crio.rentAndRead.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long UserId) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findById(UserId);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        else {
            throw new UserNotFoundException("Book not found with id: " + UserId);
        }
    }

//    public List<Book> rentBook(RentBookRequest rentBook) throws UserNotFoundException, BookNotFoundException, BookUnavailableException {
//        User user = null;
//        Optional<User> existingUser = userRepository.findById(rentBook.getUserId());
//        if (existingUser.isPresent()) {
//            user = existingUser.get();
//        }
//        else {
//            throw new UserNotFoundException("Book not found with id: " + rentBook.getUserId());
//        }
//
//        Book book = null;
//        Optional<Book> existingBook = bookRepository.findById(rentBook.getBookId());
//        if (existingBook.isPresent()) {
//            book = existingBook.get();
//        }
//        else {
//            throw new BookNotFoundException("Book not found with id: " + rentBook.getUserId());
//        }
//
//        return null;
//    }
}
