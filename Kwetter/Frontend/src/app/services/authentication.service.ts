import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams, HttpResponse } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { BehaviorSubject, Observable } from 'rxjs';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private currentUserSubject: BehaviorSubject<User>;
  public currentUser: Observable<User>;

  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): User {
    return this.currentUserSubject.value;
  }

  login(username: string, password: string): void {
    const params = new HttpParams()
      .set(`username`, username)
      .set(`password`, password);

    const url = `${environment.apiUrl}/users/login`;
    this.http.post<User>(url, params, { observe: 'response' })
      .subscribe(response => {
        let user: User = { ...response.body };
        if (response.headers.get('authorization')) {
          let bearerHeader = response.headers.get('authorization');
          if (bearerHeader.startsWith('Bearer ')) {
            user.token = bearerHeader.replace('Bearer ', '');
            localStorage.setItem('currentUser', JSON.stringify(user));
            this.currentUserSubject.next(user);
          }
          this.currentUserSubject.next(user)
        }
        return user;
      },
        err =>
          console.log(err)
      );
  }

  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
  }
}
