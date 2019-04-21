import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { ActivatedRoute } from '@angular/router';
import { Profile } from '../../models/dto/profile';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  currentUser: User;
  username: string = "";
  profile: Profile = new Profile();

  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    private authenticationService: AuthenticationService
  ) {
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
  }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.username = params.get("username")
    })
    this.getProfile(this.username);

  }

  getProfile(username: string): void {
    this.userService.getProfile(username)
      .subscribe(profile => this.profile = profile)
  }
}