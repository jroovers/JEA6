import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { UserService } from '../user.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  username: string = "";
  user: User = new User();

  constructor(private userService: UserService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
       this.username = params.get("username")
    })
    this.getUser(this.username);

  }

  getUser(username: string): void {
    this.userService.getUser(username)
      .subscribe(user => this.user = user)
  }

}
