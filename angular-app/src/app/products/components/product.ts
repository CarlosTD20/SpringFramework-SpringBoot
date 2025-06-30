import { Component, OnInit } from '@angular/core';
import { ProductService } from '../services/product';
import { Product as ProductModel } from '../models/product';
import { Form } from './form/form';
import { $locationShimProvider } from '@angular/common/upgrade';

@Component({
  selector: 'app-product',
  imports: [Form],
  templateUrl: './product.html',
  styleUrl: './product.css'
})
export class Product implements OnInit {
  products: ProductModel[] = [];

  productSelected: ProductModel = new ProductModel();

  constructor(private service: ProductService) { }

  ngOnInit(): void {
    this.service.findAll().subscribe(products => {
      this.products = products;
    })
  }

  addProduct(product: ProductModel): void {
    if (product.id > 0) {
      this.products = this.products.map(p => {
        if (p.id === product.id) {
          return { ...product };
        }
        return p;
      });
    } else {
      this.products = [...this.products, { ...product, id: this.products.length + 1 }];
    }

    this.productSelected= new ProductModel();
  }

  onUpdateProduct(productRow: ProductModel): void {
    this.productSelected = { ...productRow };
  }

  onDeleteProduct(id: number): void {
    this.products = this.products.filter(p => p.id !== id);
  }
}
