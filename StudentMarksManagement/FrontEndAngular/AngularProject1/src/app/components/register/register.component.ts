import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html'
})
export class RegisterComponent {

  id: string = ''; // Added userId property
  userName: string = '';
  password: string = '';
  type: string = 'Student'; // Default value as 'Student'

  constructor(private authService: AuthService, private router: Router) { }

  onSubmit(): void {
    if (!this.id || !this.userName || !this.password) {
      alert('Please fill in all required fields.');
      return;
    }

    this.authService.register(this.id, this.userName, this.password, this.type).subscribe({
      next: (success) => {
        if (success) {
          alert('Registration successful!');
          this.router.navigate(['login']);
        } else {
          alert('Registration failed. Please try again.');
        }
      },
      error: (err) => {
        if (err === 'User already registered') {
          alert('Registration failed. The user might already be registered.');
        } else {
          alert('Registration failed. The user might already be registered.');
        }
        console.error('Registration error:', err); // Log error details for debugging
      }
    });
  }
}
