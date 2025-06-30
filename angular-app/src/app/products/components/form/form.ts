import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Product as ProductModel } from '../../models/product';
import { FormsModule, NgForm } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'product-form',
  imports: [FormsModule, CommonModule],
  templateUrl: './form.html',
  styleUrl: './form.css'
})
export class Form {

  @Input() product: ProductModel = { id: 0, name: 'Teclado', description: 'Teclado mecánico', price: 100 }

  @Output() newProductEvent = new EventEmitter()


  onSubmit(productForm: NgForm): void {
    if (productForm.valid) {
      this.newProductEvent.emit(this.product)
      console.log(this.product)
    }

    productForm.resetForm();
  }

  clean() {
    this.product = new ProductModel();
  }

}
