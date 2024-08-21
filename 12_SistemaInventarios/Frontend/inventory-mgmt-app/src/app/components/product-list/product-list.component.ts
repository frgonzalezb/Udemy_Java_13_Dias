import { Component } from '@angular/core';
import { ProductService } from '../../services/product.service';
import { Product } from '../../models/product';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.css'
})
export class ProductListComponent {

  product: Product;
  products: Product[];

  constructor(
    private productService: ProductService,
    private router: Router
  ) {
    this.products = [];
  }

  ngOnInit() {
    this.getAllProducts();
  }

  getAllProducts() {
    this.productService.getAllProducts().subscribe((products) => {
      this.products = products;
    })
  }

  editProduct(productId: number) {
    console.log("Edit product with id:", productId);
    this.router.navigate(['/edit-product', productId]);
  }

  deleteProduct(productId: number) {
    console.log("Delete product with id:", productId);
    // TODO: implement
  }

}
