import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8085/marks-memo/'; // Spring Boot backend URL

  constructor(private http: HttpClient) { }

  login(userName: string, password: string): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}signin`, { userName, password });
  }

  register(id: string, userName: string, password: string, type: string): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}signup`, { id, userName, password, type });
  }
}
