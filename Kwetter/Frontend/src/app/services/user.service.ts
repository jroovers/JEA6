import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Profile } from '../models/dto/profile';
import { User } from '../models/user';
import { MutualFriend } from '../models/dto/mutual-friend';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getProfile(username: string) {
    const url = `${environment.apiUrl}/users/` + username;
    return this.http.get<Profile>(url)
      .pipe(
        catchError(this.handleError<Profile>('getUser(username: string)'))
      );
  }

  updateUser(user: User) {
    const url = `${environment.apiUrl}/users`;
    return this.http.post<User>(url, user)
      .pipe(
        catchError(this.handleError<User>('updateUser(user: User)'))
      );
  }

  followUser(username: string): Observable<{}> {
    const url = `${environment.apiUrl}/users/follow`;
    var body = { "username": username };
    return this.http.post<{}>(url, body)
      .pipe(
        catchError(this.handleError<{}>('followUser(username: string)'))
      );
  }

  getFriendSuggestions(): Observable<MutualFriend[]> {
    const url = `${environment.apiUrl}/users/suggest`;
    return this.http.get<MutualFriend[]>(url)
      .pipe(
        catchError(this.handleError<MutualFriend[]>('getFriendSuggestions()'))
      );
  }

  /**
 * Handle Http operation that failed.
 * Let the app continue.
 * @param operation - name of the operation that failed
 * @param result - optional value to return as the observable result
 */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      console.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}