import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from '../models/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private apiUrl: string;

  constructor(private http: HttpClient) {
    this.apiUrl = environment.apiUrl;
  }

  getAllProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.apiUrl + '/products');
  }

  addProduct(product: Product): Observable<Product> {
    return this.http.post<Product>(this.apiUrl + '/products', product);
  }

  getProductById(productId: number): Observable<Product> {
    return this.http.get<Product>(this.apiUrl + '/products/' + productId);
  }

  editProduct(product: Product): Observable<Product> {
    return this.http.put<Product>(this.apiUrl + '/products/' + product.id, product);
  }

}
