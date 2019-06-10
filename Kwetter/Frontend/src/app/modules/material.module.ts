import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule, MatCardModule, MatFormFieldModule, MatInputModule, MatButtonModule, MatIconModule, MatSidenavModule, MatListModule, MatSelectModule, MatRadioModule, MatDialogModule, MatProgressSpinnerModule, MatProgressBarModule, MatSnackBarModule } from '@angular/material';

@NgModule({
  imports: [
    BrowserAnimationsModule,
    MatToolbarModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatSidenavModule,
    MatListModule,
    MatSelectModule,
    MatRadioModule,
    MatDialogModule,
    MatProgressSpinnerModule,
    MatProgressBarModule,
    MatSnackBarModule
  ],
  exports: [
    BrowserAnimationsModule,
    MatToolbarModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatSidenavModule,
    MatListModule,
    MatSelectModule,
    MatRadioModule,
    MatDialogModule,
    MatProgressSpinnerModule,
    MatProgressBarModule,
    MatSnackBarModule
  ]
})
export class MaterialModule { }
