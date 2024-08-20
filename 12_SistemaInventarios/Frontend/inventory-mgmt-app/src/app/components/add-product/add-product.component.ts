import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { ProductType } from '../../models/product-type';
import { ProductTypeService } from '../../services/product-type.service';
import { Product } from '../../models/product';
import { ProductService } from '../../services/product.service';
import { FormBuilder, FormGroup, Validators, FormsModule, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-add-product',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './add-product.component.html',
  styleUrl: './add-product.component.css'
})
export class AddProductComponent {

  addProductForm: FormGroup;

  product: Product;
  productType: ProductType;
  types: ProductType[];

  constructor(
    private fb: FormBuilder,
    private productService: ProductService,
    private productTypeService: ProductTypeService,
    private router: Router
  ) {
    this.addProductForm = this.fb.group({
      name: [null, [Validators.required, Validators.maxLength(255)]],
      description: ['', Validators.maxLength(1000)],
      price: [null, Validators.required],
      quantity: [null, Validators.required],
      type: [null, Validators.required]
    });
    this.product = new Product();
    this.productType = new ProductType();
    this.types = [];
  }

  ngOnInit() {
    this.getAllTypes();
  }

  isValid (controlName: string) {
    return this.addProductForm.get(controlName)?.valid;
  }

  isInvalid(controlName: string) {
    return this.addProductForm.get(controlName)?.invalid && this.addProductForm.get(controlName)?.touched;
  }

  onSubmit() {
    this.saveProduct();
  }

  getAllTypes() {
    this.productTypeService.getAllTypes().subscribe((types) => {
      this.types = types;
    });
  }

  saveProduct() {
    if (this.addProductForm.invalid) {
      console.error("Form is invalid:", this.addProductForm.value); // dbg
      this.addProductForm.markAllAsTouched();
      return;
    }
    // in case of reset button was clicked and textarea was empty
    if (this.addProductForm.value.description === null) {
      this.addProductForm.value.description = "";
    }
    console.log("Form is valid:", this.addProductForm.value); // dbg

    this.productType = this.addProductForm.value.type;
    console.log("Product type:", this.productType); // dbg

    this.product.name = this.addProductForm.value.name;
    this.product.description = this.addProductForm.value.description;
    this.product.price = this.addProductForm.value.price;
    this.product.quantity = this.addProductForm.value.quantity;
    this.product.type = this.productType;
    console.log("Product:", this.product); // dbg

    this.productService.addProduct(this.product).subscribe({
      next: (product) => {
        console.log("Product added:", product); // dbg
        this.router.navigate(['/products']);
      },
      error: (error) => {
        console.error("Error adding product:", error); // dbg
      }
    });
  }

}
