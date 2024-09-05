import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormArray,
  Validators,
  ReactiveFormsModule,
} from '@angular/forms';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-approval-dashboard',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, HttpClientModule], // Import ReactiveFormsModule here

  templateUrl: './approval-dashboard.component.html',
})
export class ApprovalDashboardComponent implements OnInit {
  approvals: any[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.loadApprovals();
  }

  loadApprovals() {
    this.http
      .get<any[]>('http://localhost:8080/api/po/{poId}/workflows')
      .subscribe((data: any[]) => {
        this.approvals = data;
      });
  }

  approve(workflowId: number) {
    this.http
      .post(`http://localhost:8080/api/po/approve/${workflowId}`, {
        decision: 'APPROVED',
      })
      .subscribe(() => {
        this.loadApprovals();
      });
  }

  reject(workflowId: number) {
    this.http
      .post(`http://localhost:8080/api/po/approve/${workflowId}`, {
        decision: 'REJECTED',
      })
      .subscribe(() => {
        this.loadApprovals();
      });
  }
}
