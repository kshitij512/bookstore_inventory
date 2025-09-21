package company.bookstore_inventory.repository;

import company.bookstore_inventory.model.BookEntity;
import company.bookstore_inventory.model.Inventory;
import company.bookstore_inventory.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findByBookIdAndQuantityGreaterThan(Long bookId, Integer quantity);

    @Query("SELECT i FROM Inventory i WHERE i.book.id = :bookId AND i.quantity > 0")
    List<Inventory> findAvailableInventoriesByBookId(@Param("bookId") Long bookId);

    // Also, we might need to check if an inventory entry already exists for a book and location
    Optional<Inventory> findByBookAndLocation(BookEntity book, Location location);
}