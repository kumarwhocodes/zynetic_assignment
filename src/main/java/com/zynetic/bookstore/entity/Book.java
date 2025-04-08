package com.zynetic.bookstore.entity;

import com.zynetic.bookstore.dto.BookDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "books")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String title;
    
    private String author;
    
    private String category;
    
    private BigDecimal price;
    
    private Double rating;
    
    private LocalDate publishedDate;
    
    public BookDTO toBookDTO() {
        return BookDTO.builder()
                .id(id)
                .title(title)
                .author(author)
                .category(category)
                .price(price)
                .rating(rating)
                .publishedDate(publishedDate)
                .build();
    }
}
