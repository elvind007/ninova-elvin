import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
})
export class LoginComponent {
  username = '';
  password = '';

  constructor(private http: HttpClient, private router: Router) {}

  login() {
    this.http
      .post<any>('http://localhost:8080/login', {
        username: this.username,
        password: this.password,
      })
      .subscribe((response) => {
        localStorage.setItem('token', response.jwt);
        this.router.navigate(['/dashboard']);
      });
  }
}
