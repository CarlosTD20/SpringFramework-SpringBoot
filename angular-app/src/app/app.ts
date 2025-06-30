import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Product as ProductComponent } from './products/components/product';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, ProductComponent],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected title = 'Hola mundo Angular';
  enabled: boolean = false;

  courses: string[] =['Angular', 'React', 'Spring Boot'];

  setEnabled(): void {
    this.enabled = this.enabled ? false : true;;
    console.log('Hemos pulsato en setEnabled')
  }
}
