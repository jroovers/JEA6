import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  model: any = {};

  constructor(private authenthicationService: AuthenticationService) { }

  ngOnInit() {
  }

  login() {
    if (this.model.username != null && this.model.password != null) {
      console.log("WHATS UP NERD");
      this.authenthicationService.login(this.model.username, this.model.password);
    }
    console.log("Enter some credentials nerd");
  }

}
