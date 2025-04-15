import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder,FormGroup, ReactiveFormsModule, Validators } from '@angular/forms'
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  loginForm: FormGroup;

  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) {
    this.loginForm = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(6)]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    })
  }

  getusername() {
    return this.loginForm.get('username');
  }

  getpassword() {
    return this.loginForm.get('password');
  }
  
  onSubmit(): void {
    this.authService.getToken(this.loginForm.value).subscribe(
     (data) => {
        if (data && data.token) {
          localStorage.setItem("token", data.token);
          localStorage.setItem("isLoggedIn", "true");
          this.router.navigate(['/list']);
        }
      }, (error) => console.log(error));
  }
}
