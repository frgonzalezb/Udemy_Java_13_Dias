package franc.inventoryManagement.services;

import franc.inventoryManagement.models.Product;

import java.util.List;

public interface IProductService {
    public List<Product> listAllProducts();

    public Product getProductById(Long id);

    public Product saveProduct(Product product);

    public void deleteProductById(Long id);
}
