package com.zynetic.bookstore.repo;

import com.zynetic.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {
    List<Book> findByTitleContainingIgnoreCase(String title);
    
    List<Book> findByAuthorContainingIgnoreCaseAndCategoryContainingIgnoreCaseAndRatingGreaterThanEqual(
            String author, String category, Double rating
    );
    
}
