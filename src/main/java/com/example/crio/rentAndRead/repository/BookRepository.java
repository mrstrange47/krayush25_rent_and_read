package com.example.crio.rentAndRead.repository;

import com.example.crio.rentAndRead.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
