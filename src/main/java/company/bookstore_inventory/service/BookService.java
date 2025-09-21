package company.bookstore_inventory.service;

import company.bookstore_inventory.dto.BookResponse;
import company.bookstore_inventory.dto.LocationQuantityDto;
import company.bookstore_inventory.model.BookEntity;
import company.bookstore_inventory.model.Inventory;
import company.bookstore_inventory.model.Location;
import company.bookstore_inventory.repository.BookRepository;
import company.bookstore_inventory.repository.InventoryRepository;
import company.bookstore_inventory.repository.LocationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final LocationRepository locationRepository;
    private final InventoryRepository inventoryRepository;

    public BookService(BookRepository bookRepository, LocationRepository locationRepository, InventoryRepository inventoryRepository) {
        this.bookRepository = bookRepository;
        this.locationRepository = locationRepository;
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional
    public void addBookToLocation(String bookName, Long locationId, Integer quantity) {
        // Check if book exists, if not create it
        BookEntity bookEntity = bookRepository.findByName(bookName)
                .orElseGet(() -> bookRepository.save(new BookEntity(bookName)));

        // Check if location exists
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found with id: " + locationId));

        // Check if inventory entry already exists for this book and location
        Optional<Inventory> existingInventory = inventoryRepository.findByBookAndLocation(bookEntity, location); // ← FIXED: bookEntity (variable) not BookEntity (class)
        if (existingInventory.isPresent()) {
            // Update quantity
            Inventory inventory = existingInventory.get();
            inventory.setQuantity(inventory.getQuantity() + quantity);
            inventoryRepository.save(inventory);
        } else {
            // Create new inventory entry
            Inventory inventory = new Inventory(bookEntity, location, quantity);
            inventoryRepository.save(inventory);
        }
    }

    public BookResponse getBookWithAvailableLocations(Long bookId) {
        // Find the book by id
        BookEntity bookEntity = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));

        // Find all inventories for this book where quantity > 0
        List<Inventory> inventories = inventoryRepository.findAvailableInventoriesByBookId(bookId);

        // Convert to LocationQuantityDto list
        List<LocationQuantityDto> locationQuantityDtos = inventories.stream()
                .map(inventory -> new LocationQuantityDto(
                        inventory.getLocation().getId(),
                        inventory.getLocation().getName(),
                        inventory.getQuantity()))
                .collect(Collectors.toList());

        return new BookResponse(bookEntity.getName(), locationQuantityDtos);
    }
}