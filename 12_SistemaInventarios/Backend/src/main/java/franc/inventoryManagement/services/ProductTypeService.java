package franc.inventoryManagement.services;

import franc.inventoryManagement.models.ProductType;
import franc.inventoryManagement.repositories.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeService implements IProductTypeService {
    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Override
    public List<ProductType> listAllProductTypes() {
        return this.productTypeRepository.findAll();
    }

    @Override
    public ProductType getProductTypeById(Long id) {
        return this.productTypeRepository.findById(id).orElse(null);
    }
}
