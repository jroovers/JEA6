import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Profile } from '../../models/dto/profile';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { User } from 'src/app/models/user';
import { MatDialog } from '@angular/material';
import { ProfileEditDialogComponent } from '../profile-edit-dialog/profile-edit-dialog.component';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  currentUser: User;
  username: string = "";
  editMode = false;
  profile: Profile = new Profile();

  constructor(
    public dialog: MatDialog,
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService
  ) {
    this.router.routeReuseStrategy.shouldReuseRoute = function () {
      return false;
    };
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
      .subscribe(profile => {
        this.profile = profile;
      });
  }

  updateUser(user: User): void {
    this.userService.updateUser(user)
      .subscribe(updateduser => this.profile.user = updateduser);
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(ProfileEditDialogComponent, {
      data: {
        username: this.profile.user.username,
        name: this.profile.user.name,
        bio: this.profile.user.biography,
        location: this.profile.user.location,
        website: this.profile.user.website
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The profile edit dialog was closed');
      let user: User = new User();
      Object.assign(user, this.profile.user);
      user.name = result.name;
      user.biography = result.bio;
      user.location = result.location;
      user.website = result.website;
      this.updateUser(user);
    });
  }
}