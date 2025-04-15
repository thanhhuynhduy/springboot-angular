import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import {AntiHeroFormComponent} from './components/anti-hero-form/anti-hero-form.component';
import { ListComponent } from './pages/list/list.component';

export const routes: Routes = [
    {path: '', component: LoginComponent, title: "Login Form"},
    {path: 'create', component: AntiHeroFormComponent, title: "Create Form"},
    {path: "list", component: ListComponent},
];
