package franc.inventoryManagement.controllers;

import franc.inventoryManagement.models.Product;
import franc.inventoryManagement.services.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private IProductService iProductService;

    private List<Product> products;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        logger.info("ProductController getAllProducts() called.");
        this.products = iProductService.listAllProducts()
                .stream()
                .filter(Product::isActive)
                .toList();
        this.products.forEach(product -> logger.info(product.toString()));
        logger.info("Products have been listed successfully.");
        return this.products;
    }
}
