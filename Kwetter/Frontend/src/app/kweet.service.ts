import { Injectable } from '@angular/core';
import { Kweet } from './models/kweet';
import { KWEETS } from './mockdata/mock-kweets';
import { Observable, of } from 'rxjs';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import { User } from './models/user';

@Injectable({
  providedIn: 'root'
})
export class KweetService {

  constructor(private http: HttpClient) { }

  getMockKweets(): Observable<Kweet[]> {
    return of(KWEETS);
  }

  getKweetOverview(): Observable<Kweet[]> {
    const url = `${environment.apiUrl}/kweets`;
    return this.http.get<Kweet[]>(url)
      .pipe(
        catchError(this.handleError<Kweet[]>('getKweetOverview', []))
      );
  }

  getKweetOverviewByUser(user: User): Observable<Kweet[]> {
    const url = `${environment.apiUrl}/kweets`;
    return this.http.post<Kweet[]>(url, user, httpOptions)
      .pipe(
        catchError(this.handleError<Kweet[]>('getKweetOverviewByUser', []))
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

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': 'my-auth-token'
  })
};
