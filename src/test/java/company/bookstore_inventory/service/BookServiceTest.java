package company.bookstore_inventory.service;

import company.bookstore_inventory.model.Inventory;
import company.bookstore_inventory.model.Location;
import company.bookstore_inventory.model.BookEntity;
import company.bookstore_inventory.repository.BookRepository;
import company.bookstore_inventory.repository.InventoryRepository;
import company.bookstore_inventory.repository.LocationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private LocationRepository locationRepository;

    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void addBookToLocation_NewBookAndNewInventory() {
        // Mock data
        String bookName = "Harry Potter";
        Long locationId = 1L;
        Integer quantity = 5;

        Location location = new Location("Fifth Avenue");
        location.setId(locationId);

        BookEntity book = new BookEntity();
        book.setId(1L);

        when(bookRepository.findByName(bookName)).thenReturn(Optional.empty());
        when(bookRepository.save(any(BookEntity.class))).thenReturn(book);
        when(locationRepository.findById(locationId)).thenReturn(Optional.of(location));
        when(inventoryRepository.findByBookAndLocation(any(BookEntity.class), any(Location.class))).thenReturn(Optional.empty());

        // Call method
        bookService.addBookToLocation(bookName, locationId, quantity);

        // Verify interactions
        verify(bookRepository).findByName(bookName);
        verify(bookRepository).save(any(BookEntity.class));
        verify(locationRepository).findById(locationId);
        verify(inventoryRepository).findByBookAndLocation(any(company.bookstore_inventory.model.BookEntity.class), any(Location.class));
        verify(inventoryRepository).save(any(Inventory.class));
    }

    // Similarly, write tests for other scenarios
}