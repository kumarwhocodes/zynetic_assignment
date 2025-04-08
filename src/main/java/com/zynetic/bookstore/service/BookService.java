package com.zynetic.bookstore.service;

import com.zynetic.bookstore.dto.BookDTO;
import com.zynetic.bookstore.entity.Book;
import com.zynetic.bookstore.repo.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    
    private final BookRepo bookRepository;
    
    public BookDTO createBook(BookDTO bookDto) {
        if (bookDto == null) {
            throw new IllegalArgumentException("BookDTO cannot be null");
        }
        Book savedBook = bookRepository.save(bookDto.toBook());
        return savedBook.toBookDTO();
    }
    
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(Book::toBookDTO)
                .collect(Collectors.toList());
    }
    
    public Page<BookDTO> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .map(Book::toBookDTO);
    }
    
    public BookDTO getBookById(Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + id));
        return book.toBookDTO();
    }
    
    public BookDTO updateBook(Integer id, BookDTO bookDto) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + id));
        
        existingBook.setTitle(bookDto.getTitle());
        existingBook.setAuthor(bookDto.getAuthor());
        existingBook.setCategory(bookDto.getCategory());
        existingBook.setPrice(bookDto.getPrice());
        existingBook.setRating(bookDto.getRating());
        existingBook.setPublishedDate(bookDto.getPublishedDate());
        
        Book updatedBook = bookRepository.save(existingBook);
        return updatedBook.toBookDTO();
    }
    
    public void deleteBook(Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + id));
        bookRepository.delete(book);
    }
    
    public List<BookDTO> filterBooks(String author, String category, Double rating) {
        List<Book> books = bookRepository
                .findByAuthorContainingIgnoreCaseAndCategoryContainingIgnoreCaseAndRatingGreaterThanEqual(
                        author != null ? author : "",
                        category != null ? category : "",
                        rating != null ? rating : 0.0
                );
        
        return books.stream().map(Book::toBookDTO).toList();
    }
    
    public List<BookDTO> searchBooksByTitle(String title) {
        List<Book> books = bookRepository.findByTitleContainingIgnoreCase(title);
        return books.stream().map(Book::toBookDTO).toList();
    }
    
    public List<BookDTO> sortBooks(String basedOn, Boolean ascending) {
        Sort.Direction direction = ascending ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, basedOn);
        
        List<Book> books = bookRepository.findAll(sort);
        
        return books.stream()
                .map(Book::toBookDTO)
                .collect(Collectors.toList());
    }
    
}
