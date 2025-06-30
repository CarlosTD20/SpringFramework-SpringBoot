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
      this.service.update(product).subscribe(updatedProduct => {

        this.products = this.products.map(p => {
          if (p.id === product.id) {
            return { ...updatedProduct };
          }
          return p;
        });
      })
    } else {
      this.service.create(product).subscribe(newProduct => {
        this.products = [...this.products, { ...newProduct }];
      })

    }

    this.productSelected = new ProductModel();
  }

  onUpdateProduct(productRow: ProductModel): void {
    this.productSelected = { ...productRow };
  }

  onDeleteProduct(id: number): void {
    this.service.delete(id).subscribe(() => { this.products = this.products.filter(p => p.id !== id); })

  }
}
