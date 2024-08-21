import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: 'home',
    loadComponent: () => import('./components/product-list/product-list.component').then(m => m.ProductListComponent),
  },
  {
    path: 'add-product',
    loadComponent: () => import('./components/add-product/add-product.component').then(m => m.AddProductComponent),
  },
  {
    path: 'edit-product/:id',
    loadComponent: () => import('./components/edit-product/edit-product.component').then(m => m.EditProductComponent),
  },
  {
    path: '**',
    redirectTo: 'home',
  }
];
