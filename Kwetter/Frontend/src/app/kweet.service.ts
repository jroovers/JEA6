import { Injectable } from '@angular/core';
import { Kweet } from './models/kweet';
import { KWEETS } from './mockdata/mock-kweets';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class KweetService {

  constructor() { }

  getMockKweets(): Observable<Kweet[]> {
    return of(KWEETS);
  }

  getHomeKweets(): Observable<Kweet[]> {
    return null;
  }
}
