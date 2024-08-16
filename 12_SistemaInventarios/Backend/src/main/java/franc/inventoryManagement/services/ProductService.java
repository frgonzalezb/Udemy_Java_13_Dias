package franc.inventoryManagement.services;

import franc.inventoryManagement.models.Product;
import franc.inventoryManagement.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> listAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return this.productRepository.findById(id).orElse(null);
    }

    @Override
    public Product saveProduct(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public void deleteProductById(Long id) {
        Product product = getProductById(id);
        if (product != null) {
            product.setActive(false);
            this.productRepository.save(product);
        }
    }
}
