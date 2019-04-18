import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private http: HttpClient) { }

  login(username: string, password: string): void {
    const params = new HttpParams()
      .set(`username`, username)
      .set(`password`, password);

    const url = `${environment.apiUrl}/users/login`;
    this.http.post(url, params)
      .subscribe(
        (res) => {
          console.log(res);
        },
        err => console.log(err)
      );
  }
}
