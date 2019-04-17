import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  model: any = {};

  constructor() { }

  ngOnInit() {
  }

  login() {
    if (this.model.username != null && this.model.password != null) {
      console.log("WHATS UP NERD")
    }
    console.log("Enter some credentials nerd")
  }

}
