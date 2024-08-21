import { Component } from '@angular/core';
import { Product } from '../../models/product';
import { ProductService } from '../../services/product.service';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ProductType } from '../../models/product-type';
import { ProductTypeService } from '../../services/product-type.service';
import { log } from 'console';

@Component({
  selector: 'app-edit-product',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './edit-product.component.html',
  styleUrl: './edit-product.component.css'
})
export class EditProductComponent {

  editProductForm: FormGroup;

  product: Product;
  productId: number;
  productType: ProductType;
  types: ProductType[];

  constructor(
    private fb: FormBuilder,
    private productService: ProductService,
    private productTypeService: ProductTypeService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.editProductForm = this.fb.group({
      name: [null, [Validators.required, Validators.maxLength(255)]],
      description: ['', Validators.maxLength(1000)],
      price: [null, Validators.required],
      quantity: [null, Validators.required],
      type: [null, Validators.required]
    });
    this.getAllTypes();
  }

  ngOnInit() {
    this.productId = this.route.snapshot.params['id'];
    this.productService.getProductById(this.productId).subscribe({
      next: (data) => {
        console.log("Product:", data); // dbg
        this.product = data;
        this.editProductForm.patchValue({
          name: this.product.name,
          description: this.product.description,
          price: this.product.price,
          quantity: this.product.quantity,
          type: this.product.type
        });
      },
      error: (err) => {
        console.error(err);
      }
    });
  }

  compareProductTypes(optionType: ProductType, selectedType: ProductType) {
    /**
     * Compares the product type retrieved from the Product object with
     * the product types retrieved from the database.
     * 
     * This is important for the dropdown elements, because Angular
     * needs a way to determine which object in the list matches
     * the object bound to the form control and render it correctly,
     * improving user experience.
     *
     * @param {ProductType} optionType - Represents the product type
     * from the dropdown options.
     * @param {ProductType} selectedType - Represents the product type
     * that is currently selected or bound to the form (the type from
     * the actual Product object).
     * @return {boolean} Returns true if the optionType and selectedType
     * are equal, and false otherwise.
     */
    return optionType && selectedType ? optionType.id === selectedType.id : optionType === selectedType;
  }


  isValid(controlName: string) {
    return this.editProductForm.get(controlName)?.valid;
  }

  isInvalid(controlName: string) {
    return this.editProductForm.get(controlName)?.invalid && this.editProductForm.get(controlName)?.touched;
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
    if (this.editProductForm.invalid) {
      console.error("Form is invalid:", this.editProductForm.value); // dbg
      this.editProductForm.markAllAsTouched();
      return;
    }
    // in case of reset button was clicked and textarea was empty
    if (this.editProductForm.value.description === null) {
      this.editProductForm.value.description = "";
    }
    console.log("Form is valid:", this.editProductForm.value); // dbg

    this.productType = this.editProductForm.value.type;
    console.log("Product type:", this.productType); // dbg

    this.product.name = this.editProductForm.value.name;
    this.product.description = this.editProductForm.value.description;
    this.product.price = this.editProductForm.value.price;
    this.product.quantity = this.editProductForm.value.quantity;
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
