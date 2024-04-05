package com.example.crio.rentAndRead;

import com.example.crio.rentAndRead.controller.BookController;
import com.example.crio.rentAndRead.dto.request.RentBookRequest;
import com.example.crio.rentAndRead.entity.Book;
import com.example.crio.rentAndRead.exception.BookNotFoundException;
import com.example.crio.rentAndRead.exception.BookRentedException;
import com.example.crio.rentAndRead.exception.BookUnavailableException;
import com.example.crio.rentAndRead.exception.UserNotFoundException;
import com.example.crio.rentAndRead.service.BookService;
import com.example.crio.rentAndRead.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    private MockMvc mockMvc;

    @Test
    public void createBookTest(){
        Mockito.when(bookService.addBook(any())).thenReturn(new Book());
        Book book = bookController.createBook(any());
        Assertions.assertNotNull(book);
    }

    @Test
    public void getAllBooksTest(){
        Mockito.when(bookService.findAllBooks()).thenReturn(new ArrayList<>());
        List<Book> books = bookController.getAllBook();
        Assertions.assertNotNull(books);
        Assertions.assertSame(0, books.size());
    }

    @Test
    public void rentBookTest() throws UserNotFoundException, BookRentedException, BookUnavailableException, BookNotFoundException {
        RentBookRequest req = new RentBookRequest(1L,2L);
        Mockito.when(bookService.rentBook(req)).thenReturn("book rented");

        ResponseEntity<String> result = bookController.rentBook(1L,req);
        Assertions.assertNotNull(result);
        Assertions.assertSame("book rented",result.getBody());
    }

    @Test
    public void rentBookTest1() throws UserNotFoundException, BookRentedException, BookUnavailableException, BookNotFoundException {
        RentBookRequest req = new RentBookRequest(1L,2L);
        Mockito.when(bookService.rentBook(req)).thenThrow(new BookNotFoundException("Book not found"));
        try{
            ResponseEntity<String> result = bookController.rentBook(1L,req);
        }
        catch (Exception e){
            Assertions.assertSame(e.getMessage(), "Book not found");
        }
    }

}
