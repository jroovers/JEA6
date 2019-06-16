import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../services/authentication.service';
import { User } from 'src/app/models/user';
import { Router } from '@angular/router';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  model: any = {};
  currentUser: User;

  constructor(
    private authenticationService: AuthenticationService,
    private router: Router,
    private titleService: Title
  ) {
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
    this.titleService.setTitle("Kwetter Login");
  }

  ngOnInit() {
    if (this.currentUser != null) {
      this.router.navigateByUrl("/");
    }
  }

  login() {
    if (this.model.username != null && this.model.password != null) {
      this.authenticationService.login(this.model.username, this.model.password);
    }
  }
}
