import { Component, OnInit, AfterViewChecked } from '@angular/core';
import { Kweet } from '../../models/kweet';
import { KweetService } from '../../services/kweet.service';
import { User } from 'src/app/models/user';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { Router, Route, ActivatedRoute } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { MutualFriend } from 'src/app/models/dto/mutual-friend';
import { ViewportScroller } from '@angular/common';
import { WebsocketService } from 'src/app/services/websocket.service';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit, AfterViewChecked {

  currentUser: User;
  model: any = { "message": "" };
  kweets: Kweet[] = [];
  suggestions: MutualFriend[] = [];
  fragment: String;

  constructor(
    private userService: UserService,
    private kweetService: KweetService,
    private authenticationService: AuthenticationService,
    private router: Router,
    private activedRoute: ActivatedRoute,
    private scroller: ViewportScroller,
    private socketService: WebsocketService,
    private titleService: Title
  ) {
    this.router.routeReuseStrategy.shouldReuseRoute = function () {
      return false;
    };
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
    if (this.currentUser) {
      this.socketService.getKweetSubject().subscribe(x => { this.getKweets() });
    }
    this.titleService.setTitle("Kwetter")
  }

  ngOnInit() {
    if (this.currentUser) {
      this.getPersonalisedKweets();
      this.getFriendSuggestions();
    }
    else {
      this.getKweets();
    }
  }

  ngAfterViewChecked(): void {
    this.activedRoute.fragment.subscribe(fragment => {
      let kweet = this.kweets.find(kweet => kweet.id == Number(fragment));
      if (kweet != null) {
        kweet.isActive = true;
      }
      this.scroller.setOffset([0, 100]);
      this.scroller.scrollToAnchor(fragment);
    })
  }

  getKweets(): void {
    this.kweetService.getKweetOverview().
      subscribe(kweets => {
        this.kweets = kweets;
      });
  }

  getPersonalisedKweets(): void {
    this.kweetService.getKweetOverviewByUser(this.currentUser)
      .subscribe(kweets => {
        this.kweets = kweets;
      });
  }

  getFriendSuggestions(): void {
    this.userService.getFriendSuggestions()
      .subscribe(result => {
        this.suggestions = result;
      });
  }

  place() {
    if (this.model.message != null && this.model.message.length >= 2 && this.model.message.length <= 255) {
      let message = {
        username: this.currentUser.username,
        token: this.currentUser.token,
        body: this.model.message
      }
      this.socketService.sendMessage(JSON.stringify(message));
      this.model.message = "";
    }
  }
}
