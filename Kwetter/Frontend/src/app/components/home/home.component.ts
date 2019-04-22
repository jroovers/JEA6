import { Component, OnInit } from '@angular/core';
import { Kweet } from '../../models/kweet';
import { KweetService } from '../../services/kweet.service';
import { User } from 'src/app/models/user';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { MutualFriend } from 'src/app/models/dto/mutual-friend';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  currentUser: User;
  model: any = { "message": "" };
  kweets: Kweet[] = [];
  suggestions: MutualFriend[] = [];
  

  constructor(
    private userService: UserService,
    private kweetService: KweetService,
    private authenticationService: AuthenticationService,
    private router: Router
  ) {
    this.router.routeReuseStrategy.shouldReuseRoute = function () {
      return false;
    };
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
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

  getFriendSuggestions(): void{
      this.userService.getFriendSuggestions()
      .subscribe(result => 
        {
          this.suggestions = result;
        });
  }

  place() {
    if (this.model.message != null && this.model.message.length >= 2 && this.model.message.length <= 255) {
      this.kweetService.createKweet(this.model.message).subscribe(
        data => {
          this.model.message = "";
          this.ngOnInit();
        }
      )
    }
  }
}
