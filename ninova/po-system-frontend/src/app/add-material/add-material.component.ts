import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-add-material',
  templateUrl: './add-material.component.html',
})
export class AddMaterialComponent {
  material = {
    name: '',
    unitPrice: 0,
  };

  constructor(private http: HttpClient) {}

  addMaterial() {
    this.http
      .post('http://localhost:8080/api/materials/add', this.material)
      .subscribe((response) => {
        console.log('Material added:', response);
      });
  }
}
