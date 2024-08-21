import { Component } from '@angular/core';
import { Product } from '../../models/product';
import { ProductService } from '../../services/product.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-edit-product',
  standalone: true,
  imports: [],
  templateUrl: './edit-product.component.html',
  styleUrl: './edit-product.component.css'
})
export class EditProductComponent {

  product: Product;
  id: number;

  constructor(
    private productService: ProductService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.product = new Product();
  }

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    this.productService.getProductById(this.id).subscribe({
      next: (data) => {
        this.product = data;
      },
      error: (err) => {
        console.error(err);
      }
    })
  }

}
