import { Component } from '@angular/core';
import { CreatePurchaseOrderComponent } from './create-purchase-order/create-purchase-order.component';
import { ApprovalDashboardComponent } from './approval-dashboard/approval-dashboard.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CreatePurchaseOrderComponent, ApprovalDashboardComponent],
  templateUrl: './app.component.html',
})
export class AppComponent {}
