package company.bookstore_inventory.repository;

import company.bookstore_inventory.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
    Optional<BookEntity> findByName(String name);
}