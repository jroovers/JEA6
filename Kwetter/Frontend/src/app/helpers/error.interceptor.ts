import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor, HttpResponse } from '@angular/common/http';
import { Observable, throwError, empty } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

import { AuthenticationService } from '../services/authentication.service';
import { Router } from '@angular/router';
import { SimpleSnackBarComponent } from '../components/simple-snack-bar/simple-snack-bar.component';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
    constructor(
        private authenticationService: AuthenticationService,
        private router: Router,
        private snackBar: SimpleSnackBarComponent
    ) { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(request).pipe(
            tap(event => {
                if (event instanceof HttpResponse) {
                }
            }),
            catchError(err => {
                console.log(err);
                let message = "Something went wrong... (" + err.status + ")";
                if (err.status === 0) {
                    this.authenticationService.logout();
                    this.router.navigateByUrl("/500", { replaceUrl: true });
                    message = "Server unavailable."
                }
                if (err.status === 401) {
                    // auto logout if 401 response returned from api
                    this.authenticationService.logout();
                    this.router.navigateByUrl("/login", { replaceUrl: true });
                    message = "Authorization failed."
                }
                if (err.status === 404) {
                    this.router.navigateByUrl("/404", { replaceUrl: true, });
                    return empty();
                }
                this.snackBar.openSnackBar(message, 'Dismiss', 'warn-snackbar', 4000);
                const error = err.error.message || err.statusText;
                return throwError(error);
            }))
    }
}