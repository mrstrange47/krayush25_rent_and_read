package com.example.crio.rentAndRead.repository;

import com.example.crio.rentAndRead.entity.Book;
import com.example.crio.rentAndRead.entity.Rental;
import com.example.crio.rentAndRead.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    boolean existsByUserAndBook(User user, Book book);
    void deleteByUserAndBook(User user, Book book);
}
