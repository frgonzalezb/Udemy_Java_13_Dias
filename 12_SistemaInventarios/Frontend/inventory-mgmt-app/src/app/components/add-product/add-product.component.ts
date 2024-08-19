import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { ProductType } from '../../models/product-type';
import { ProductTypeService } from '../../services/product-type.service';

@Component({
  selector: 'app-add-product',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './add-product.component.html',
  styleUrl: './add-product.component.css'
})
export class AddProductComponent {
  types: ProductType[];

  constructor(private productTypeService: ProductTypeService) {
    this.types = [];
  }

  ngOnInit() {
    this.getAllTypes();
  }

  getAllTypes() {
    this.productTypeService.getAllTypes().subscribe((types) => {
      this.types = types;
    });
  }

}
