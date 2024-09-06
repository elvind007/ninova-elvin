import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { ApprovalDashboardComponent } from './approval-dashboard/approval-dashboard.component';
import 

export const routes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: 'dashboard', component: ApprovalDashboardComponent, canActivate: [AuthGuard] },
  ];
