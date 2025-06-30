import { Injectable } from '@angular/core';
import { Product as ProductModel } from '../models/product';
import { map, Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private product: ProductModel[] = [
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

  private url: string = 'http://localhost:8080/products';

  constructor(private http: HttpClient) { }

  findAll(): Observable<ProductModel[]> {
    // return of(this.product);
    return this.http.get<ProductModel[]>(this.url).pipe(
      map((response: any) => response._embedded.products as ProductModel[])
    );
  }

  create(product: ProductModel): Observable<ProductModel> {
    return this.http.post<ProductModel>(this.url, product);
  }

  update(product: ProductModel): Observable<ProductModel> {
    return this.http.put<ProductModel>(`${this.url}/${product.id}`, product);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`)
  }
}
