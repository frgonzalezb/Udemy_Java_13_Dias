package franc.inventoryManagement.repositories;

import franc.inventoryManagement.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
