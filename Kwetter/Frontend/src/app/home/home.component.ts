import { Component, OnInit } from '@angular/core';
import { Kweet } from '../models/kweet';
import { KweetService } from '../kweet.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  kweets: Kweet[];

  constructor(private kweetService: KweetService) {

  }

  ngOnInit() {
    this.getKweets();
    console.log(this.kweets);
  }

  getKweets(): void {
    this.kweetService.getKweetOverview().
      subscribe(kweets => this.kweets = kweets);
  }

}
