import { Injectable } from '@angular/core';
import { WebsocketClient } from '../models/websocket-client';
import { AuthenticationService } from './authentication.service';
import { User } from '../models/user';
import { Observable, BehaviorSubject, Subject, Observer } from 'rxjs';
import { Kweet } from '../models/kweet';

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {

  private client: WebsocketClient = null;
  private currentUser: User;

  constructor(
    private authService: AuthenticationService
  ) {
    this.authService.currentUser.subscribe(x => {
      this.currentUser = x;
      if (this.client != null) {
        this.client.disconnect();
      }
      if (this.currentUser != null) {
        let url = '/Backend/wss/' + this.currentUser.username;
        this.client = new WebsocketClient('ws', 'localhost', 8080, url)
        this.client.connect();
        this.client.getWebSocket().onmessage = function (event) {
          var msg = event.data;
          let kweet: Kweet = { ...event.data };
          console.log('onmessage::' + msg);
        };
      }
    });
  }

  sendMessage(message) {
    this.client.send(message);
  }

  getKweetSubject(): Observable<Kweet> {
    return this.client.GetInstanceStatus();
    if (this.client == null || this.client.getWebSocket() == null) {
      return null;
    }
    return this.client.currentKweetSubject;
  }
}
