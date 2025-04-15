import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  getToken(credentials: any): Observable<any> {
    console.log("goi ham login");
    return this.http.post(`${environment.authURL}/authenticate`, credentials);
  }
}
