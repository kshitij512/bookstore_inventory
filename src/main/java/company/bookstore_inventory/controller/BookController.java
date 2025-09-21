package company.bookstore_inventory.controller;

import company.bookstore_inventory.dto.BookRequest;
import company.bookstore_inventory.dto.BookResponse;
import company.bookstore_inventory.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<String> addBookToLocation(@RequestBody BookRequest bookRequest) {
        try {
            bookService.addBookToLocation(bookRequest.getBookName(), bookRequest.getLocationId(), bookRequest.getQuantity());
            return ResponseEntity.status(HttpStatus.CREATED).body("Book added to location successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookResponse> getBookWithAvailableLocations(@PathVariable Long bookId) {
        try {
            BookResponse response = bookService.getBookWithAvailableLocations(bookId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}