import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-student-dashboard',
  templateUrl: './student-dashboard.component.html'
})
export class StudentDashboardComponent implements OnInit {
  studentMarks: any; // Holds the fetched student marks

  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
    this.loadStudentMarks();
  }

  // Method to fetch student marks
  loadStudentMarks(): void {
    const userId = localStorage.getItem('userId');

    if (userId) {
      this.http.get<any>(`http://localhost:8085/marks-memo/user/${userId}`)
        .subscribe(response => {
          // Assuming the response is an array and we are interested in the first element
          this.studentMarks = response[0];
        }, error => {
          console.error('Error fetching student marks', error);
        });
    } else {
      console.warn('No user ID found in local storage');
    }
  }

  // Add the logout method
  logout(): void {
    // Clear user data from local storage
    localStorage.removeItem('userId');
    localStorage.removeItem('userType');

    console.log('Logout clicked');
    // Redirect to the login page
    this.router.navigate(['/login']);
  }
}
