import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { ProfileComponent } from './components/profile/profile.component';
import { TestnavComponent } from './testnav/testnav.component';
import { TestformComponent } from './testform/testform.component';
import { AuthGuard } from './guards/auth.guard';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'testnav', component: TestnavComponent },
  { path: 'testform', component: TestformComponent },
  { path: '404', component: PageNotFoundComponent },
  { path: ':username/edit', component: ProfileComponent, canActivate: [AuthGuard] },
  { path: ':username', component: ProfileComponent },
  { path: '**', redirectTo: '/404' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
