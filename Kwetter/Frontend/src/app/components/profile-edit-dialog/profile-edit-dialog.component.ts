import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

export interface DialogData {
  username: string;
  name: string;
  bio: string;
  location: string;
  website: string;
}

@Component({
  selector: 'app-profile-edit-dialog',
  templateUrl: './profile-edit-dialog.component.html',
  styleUrls: ['./profile-edit-dialog.component.scss']
})
export class ProfileEditDialogComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<ProfileEditDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData
  ) { }

  ngOnInit() {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
