import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule, MatCardModule, MatFormFieldModule, MatInputModule, MatButtonModule, MatIconModule, MatSidenavModule, MatListModule, MatSelectModule, MatRadioModule, MatProgressSpinner, MatProgressSpinnerModule } from '@angular/material';
import { NgMatSearchBarModule } from 'ng-mat-search-bar';

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
    NgMatSearchBarModule,
    MatProgressSpinnerModule
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
    NgMatSearchBarModule,
    MatProgressSpinnerModule
  ]
})
export class MaterialModule { }
