import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { ActivatedRoute } from '@angular/router';
import { Profile } from '../models/dto/profile';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  username: string = "";
  profile: Profile = new Profile();

  constructor(private userService: UserService, private route: ActivatedRoute) { }

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