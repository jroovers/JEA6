import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { HomeComponent } from './components/home/home.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { LoginComponent } from './components/login/login.component';
import { ProfileComponent } from './components/profile/profile.component';
import { TestnavComponent } from './testnav/testnav.component';
import { LayoutModule } from '@angular/cdk/layout';
import { TestformComponent } from './testform/testform.component';
import { MaterialModule } from './modules/material.module';
import { ErrorInterceptor } from './helpers/error.interceptor';
import { JwtInterceptor } from './helpers/jwt.interceptor';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { ProfileEditDialogComponent } from './components/profile-edit-dialog/profile-edit-dialog.component';
import { BackendUnreachableComponent } from './components/backend-unreachable/backend-unreachable.component';
import { LoaderComponent } from './components/loader/loader.component';
import { LoaderInterceptorService } from './helpers/loader.interceptor';
import { SimpleSnackBarComponent } from './components/simple-snack-bar/simple-snack-bar.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ProfileComponent,
    HomeComponent,
    TestnavComponent,
    TestformComponent,
    PageNotFoundComponent,
    ProfileEditDialogComponent,
    BackendUnreachableComponent,
    LoaderComponent,
    SimpleSnackBarComponent
  ],
  imports: [
    BrowserModule,
    MaterialModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    LayoutModule,
    ReactiveFormsModule
  ],
  entryComponents: [
    ProfileEditDialogComponent
  ],
  providers: [
    SimpleSnackBarComponent,
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: LoaderInterceptorService, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
