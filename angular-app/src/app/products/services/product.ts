import { Injectable } from '@angular/core';
import { Product } from '../models/product';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private product: Product[] = [
    {
      id: 1,
      name: 'Mesa Comedor',
      description: 'Mesa de madera de pino',
      price: 100
    },
    {
      id: 2,
      name: 'Teclado mecánico',
      description: 'Excelente teclado mecánico',
      price: 200
    }
  ];

  constructor() { }

  findAll(): Observable<Product[]> {
    return of(this.product);
  }
}
