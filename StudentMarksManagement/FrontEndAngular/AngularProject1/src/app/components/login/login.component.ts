import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent {
  userName: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router) { }

  onSubmit(): void {
    this.errorMessage = ''; // Clear any previous error messages

    this.authService.login(this.userName, this.password).subscribe({
      next: (user) => {
        if (user) {
          // Optionally store user information
           localStorage.setItem('userId', user.id);
           localStorage.setItem('userType', user.userType);

          // Navigate based on user type
          if (user.userType === 'admin') {
            this.router.navigate(['/admin-dashboard']);
          } else {
            this.router.navigate(['/student-dashboard']);
          }
        } else {
          this.handleLoginError('Invalid credentials');
        }
      },
      error: (err) => {
        // Handle any unexpected errors from the server
        this.handleLoginError('Invalid credential.');
        console.error('Login error:', err); // Log error details for debugging
      }
    });
  }

  private handleLoginError(message: string): void {
    this.errorMessage = message;
    alert(message); // Show alert with the error message
  }
}
