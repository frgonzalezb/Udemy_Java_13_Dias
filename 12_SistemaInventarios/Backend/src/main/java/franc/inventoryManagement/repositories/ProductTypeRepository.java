package franc.inventoryManagement.repositories;

import franc.inventoryManagement.models.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
}
