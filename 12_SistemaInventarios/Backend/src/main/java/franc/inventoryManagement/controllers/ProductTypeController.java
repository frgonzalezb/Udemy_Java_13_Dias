package franc.inventoryManagement.controllers;

import franc.inventoryManagement.models.ProductType;
import franc.inventoryManagement.services.IProductTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class ProductTypeController {
    private static final Logger logger = LoggerFactory.getLogger(ProductTypeController.class);

    @Autowired
    private IProductTypeService productTypeService;

    @GetMapping("/types")
    public List<ProductType> getAllProductTypes() {
        logger.info("ProductTypeController getAllProductTypes() called.");
        return this.productTypeService.listAllProductTypes();
    }
}
