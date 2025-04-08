package com.zynetic.bookstore.controller;

import com.zynetic.bookstore.dto.BookDTO;
import com.zynetic.bookstore.dto.PagedResponse;
import com.zynetic.bookstore.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {
    
    private final BookService bookService;
    
    @PostMapping("/create")
    public ResponseEntity<?> createBook(@Valid @RequestBody BookDTO bookDto) {
        
        BookDTO book = bookService.createBook(bookDto);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }
    
    @GetMapping("/get-all")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }
    
    @GetMapping("/paged")
    public ResponseEntity<PagedResponse<BookDTO>> getPagedBooks(Pageable pageable) {
        Page<BookDTO> booksPage = bookService.getAllBooks(pageable);
        
        PagedResponse<BookDTO> response = new PagedResponse<>(
                booksPage.getContent(),
                booksPage.getNumber(),
                booksPage.getSize(),
                booksPage.getTotalElements(),
                booksPage.getTotalPages(),
                booksPage.isLast()
        );
        
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Integer id) {
        BookDTO book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(
            @PathVariable Integer id,
            @Valid @RequestBody BookDTO bookDto) {
        BookDTO updatedBook = bookService.updateBook(id, bookDto);
        return ResponseEntity.ok(updatedBook);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/filter")
    public ResponseEntity<List<BookDTO>> filterBooks(
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double rating
    ) {
        List<BookDTO> books = bookService.filterBooks(author, category, rating);
        return ResponseEntity.ok(books);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<BookDTO>> searchBooks(@RequestParam String title) {
        List<BookDTO> books = bookService.searchBooksByTitle(title);
        return ResponseEntity.ok(books);
    }
    
    @GetMapping("/sort")
    public ResponseEntity<List<BookDTO>> sortBooks(
            @RequestParam(defaultValue = "price") String basedOn,
            @RequestParam(defaultValue = "true") Boolean ascending
    ) {
        List<BookDTO> books = bookService.sortBooks(basedOn, ascending);
        return ResponseEntity.ok(books);
    }
    
}