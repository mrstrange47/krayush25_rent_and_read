package com.example.crio.rentAndRead.controller;

import com.example.crio.rentAndRead.dto.request.BookRequest;
import com.example.crio.rentAndRead.entity.Book;
import com.example.crio.rentAndRead.exception.BookNotFoundException;
import com.example.crio.rentAndRead.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;

    // Admin can add, update, read, delete Books
    // But User can only read book, User is not authorised to add, update, and delete.

    // POST /books
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Book createBook(@RequestBody Book book){
        return bookService.addBook(book);
    }

    // GET /books
    @GetMapping
    public List<Book> getAllBook(){
        return bookService.findAllBooks();
    }

    // GET /books/{bookId}
    @GetMapping("/{bookId}")
    public ResponseEntity<Object> getBookById(@PathVariable Long bookId){
        Book book = null;
        try{
            book = bookService.getBookById(bookId);
        }
        catch (BookNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok(book);
    }

    // PUT /books/{bookId}
    @PutMapping("/{bookId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> updateBook(@PathVariable("bookId") Long bookId, @RequestBody BookRequest bookRequest){
        Book book = null;
        try{
            book = bookService.updateBookById(bookId, bookRequest);
        }
        catch (BookNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return ResponseEntity.ok(book);
    }

    // DELETE /books/{bookId}
    @DeleteMapping("/{bookId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteUser(@PathVariable Long bookId){
        String msg = "";
        try{
            msg = bookService.deleteBookById(bookId);
        }
        catch (BookNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok(msg);
    }
}
