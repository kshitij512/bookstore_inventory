package company.bookstore_inventory.controller;

import company.bookstore_inventory.dto.BookRequest;
import company.bookstore_inventory.dto.BookResponse;
import company.bookstore_inventory.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookEntityControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @Test
    void addBookToLocation_Success() {
        BookRequest request = new BookRequest("Harry Potter", 1L, 5);
        doNothing().when(bookService).addBookToLocation(any(), any(), any());

        ResponseEntity<String> response = bookController.addBookToLocation(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Book added to location successfully", response.getBody());
    }

    @Test
    void getBookWithAvailableLocations_Success() {
        Long bookId = 1L;
        BookResponse mockResponse = new BookResponse("Harry Potter", List.of());
        when(bookService.getBookWithAvailableLocations(bookId)).thenReturn(mockResponse);

        ResponseEntity<BookResponse> response = bookController.getBookWithAvailableLocations(bookId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockResponse, response.getBody());
    }
}