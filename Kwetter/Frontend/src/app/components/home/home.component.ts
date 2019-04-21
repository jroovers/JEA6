import { Component, OnInit } from '@angular/core';
import { Kweet } from '../../models/kweet';
import { KweetService } from '../../services/kweet.service';
import { User } from 'src/app/models/user';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  model: any = { "message": "" };
  kweets: Kweet[];
  currentUser: User;

  constructor(
    private kweetService: KweetService,
    private authenticationService: AuthenticationService
  ) {
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
  }

  ngOnInit() {
    this.getKweets();
  }

  getKweets(): void {
    this.kweetService.getKweetOverview().
      subscribe(kweets => this.kweets = kweets);
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
