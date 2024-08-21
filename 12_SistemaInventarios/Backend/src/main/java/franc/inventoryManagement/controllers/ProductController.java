package franc.inventoryManagement.controllers;

import franc.inventoryManagement.exceptions.ProductNotFoundException;
import franc.inventoryManagement.models.Product;
import franc.inventoryManagement.services.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private IProductService productService;

    private List<Product> products;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        logger.info("ProductController getAllProducts() called.");
        this.products = productService.listAllProducts()
                .stream()
                .filter(Product::isActive)
                .toList();
        this.products.forEach(product -> logger.info(product.toString()));
        logger.info("Products have been listed successfully.");
        return this.products;
    }

    @PostMapping("/products")
    public Product saveProduct(@RequestBody Product product) {
        logger.info("ProductController saveProduct() called.");
        logger.info("Product: " + product.toString());
        return productService.saveProduct(product);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        logger.info("ProductController getProductById() called.");
        Product product = productService.getProductById(id);
        if (product == null) {
            logger.error("There's no product with id: " + id);
            throw new ProductNotFoundException("There's no product with id: " + id);
        }
        return ResponseEntity.ok(product);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        logger.info("ProductController updateProduct() called.");
        Product updatedProduct = productService.getProductById(id);
        if (updatedProduct == null) {
            logger.error("There's no product with id: " + id);
            throw new ProductNotFoundException("There's no product with id: " + id);
        }
        updatedProduct.setName(product.getName());
        updatedProduct.setDescription(product.getDescription());
        updatedProduct.setPrice(product.getPrice());
        updatedProduct.setQuantity(product.getQuantity());
        updatedProduct.setType(product.getType());
        productService.saveProduct(updatedProduct);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long id) {
        logger.info("ProductController deleteProductById() called.");
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }
}
