package franc.inventoryManagement.services;

import franc.inventoryManagement.models.ProductType;
import java.util.List;

public interface IProductTypeService {
    public List<ProductType> listAllProductTypes();
    public ProductType getProductTypeById(Long id);
}
