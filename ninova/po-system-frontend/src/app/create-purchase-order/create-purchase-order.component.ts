import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormArray,
  Validators,
  ReactiveFormsModule,
} from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClientModule, HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-create-purchase-order',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, HttpClientModule], // Import ReactiveFormsModule here
  templateUrl: './create-purchase-order.component.html',
})
export class CreatePurchaseOrderComponent implements OnInit {
  poForm!: FormGroup;

  constructor(private fb: FormBuilder, private http: HttpClient) {}

  ngOnInit() {
    this.poForm = this.fb.group({
      totalAmount: ['', Validators.required],
      workflows: this.fb.array([this.createWorkflow()]),
    });
  }

  createWorkflow(): FormGroup {
    return this.fb.group({
      level: ['', Validators.required],
      approverId: ['', Validators.required],
    });
  }

  get workflows(): FormArray {
    return this.poForm.get('workflows') as FormArray;
  }

  addWorkflow() {
    this.workflows.push(this.createWorkflow());
  }

  onSubmit() {
    if (this.poForm.valid) {
      this.http
        .post('http://localhost:8080/api/po/create', this.poForm.value)
        .subscribe((response) => {
          console.log('PO Created:', response);
        });
    }
  }
}
