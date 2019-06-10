import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-simple-snack-bar',
  templateUrl: './simple-snack-bar.component.html',
  styleUrls: ['./simple-snack-bar.component.scss']
})
export class SimpleSnackBarComponent {

  constructor(public snackBar: MatSnackBar) { }

  // this function will open up snackbar on top right position with custom background color (defined in css)
  openSnackBar(message: string, action: string, className: string, milliseconds: number) {
    this.snackBar.open(message, action, {
      duration: milliseconds,
      verticalPosition: 'top',
      horizontalPosition: 'end',
      panelClass: [className],
    });
  }
}
