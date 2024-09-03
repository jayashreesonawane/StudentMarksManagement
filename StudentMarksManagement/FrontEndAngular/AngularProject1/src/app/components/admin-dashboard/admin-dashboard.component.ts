import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../services/admin.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html'
})
export class AdminDashboardComponent implements OnInit {
  students: any[] = [];
  currentStudent: any = { id: '', sem1: 0, sem2: 0, sem3: 0, total: 0 };
  isEditing: boolean = false;
  editingIndex: number = -1;

  constructor(private adminService: AdminService, private router: Router) { }

  ngOnInit(): void {
    this.loadStudentMarks();
  }

  loadStudentMarks(): void {
    this.adminService.getAllStudentMarks().subscribe(data => {
      this.students = data;
    });
  }

  addStudent(): void {
    this.isEditing = true;
    this.editingIndex = -1;
    this.currentStudent = { id: '', sem1: 0, sem2: 0, sem3: 0, total: 0 };
  }

  saveStudent(): void {
    this.currentStudent.total = this.currentStudent.sem1 + this.currentStudent.sem2 + this.currentStudent.sem3;

    if (this.editingIndex === -1) {
      console.log(this.currentStudent)
      // Add new student
      this.adminService.addStudent(this.currentStudent).subscribe(newStudent => {
        this.students.push(newStudent);
        this.cancelEdit();
      });
    } else {
      // Update existing student
      const studentToUpdate = { ...this.currentStudent, id: this.students[this.editingIndex].id };
      this.adminService.updateStudent(studentToUpdate).subscribe(updatedStudent => {
        this.students[this.editingIndex] = updatedStudent;
        this.cancelEdit();
      });
    }
  }

  editStudent(index: number): void {
    this.isEditing = true;
    this.editingIndex = index;
    this.currentStudent = { ...this.students[index] };
  }

  deleteStudent(index: number): void {
    const studentId = this.students[index].id;
    this.adminService.deleteStudent(studentId).subscribe(() => {
      this.students.splice(index, 1);
    });
  }

  cancelEdit(): void {
    this.isEditing = false;
    this.currentStudent = { rollNo: '', name: '', sem1: 0, sem2: 0, sem3: 0, total: 0 };
    this.editingIndex = -1;
  }

  logout(): void {
    // Clear any session or authentication tokens here
    console.log('Logout clicked');
    // Redirect to the login page
    this.router.navigate(['/login']); // Adjust the route as needed
  }
}
