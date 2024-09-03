import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-student-dashboard',
  templateUrl: './student-dashboard.component.html'
})
export class StudentDashboardComponent {
  studentMarks: any = {}; // Example student marks object

  constructor(private router: Router) { }

  logout(): void {
    // Clear any authentication-related data (like tokens)
    localStorage.removeItem('authToken'); // Example: Removing token from local storage

    // Redirect to the login page
    this.router.navigate(['/login']);
  }
}

