import { Injectable } from '@angular/core';
import { WebsocketClient } from '../models/websocket-client';
import { AuthenticationService } from './authentication.service';
import { User } from '../models/user';

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
        this.client = new WebsocketClient('ws', 'localhost', 8080, '/Backend/wss')
        this.client.connect();
      }
    });
  }

  sendMessage(message) {
    this.client.send(message);
  }

}
