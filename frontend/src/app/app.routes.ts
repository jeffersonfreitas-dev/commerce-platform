import { Routes } from '@angular/router';
import { canActivateAuth } from './guards/auth.guard';


export const routes: Routes = [
    {
        path: '',
        loadComponent: () =>
        import('./pages/home/home').then(m => m.HomeComponent)
    },
    {
        path: 'login',
        loadComponent: () =>
        import('./pages/login/login').then(m => m.LoginComponent)
    },    
    {
        path: 'dashboard',
        loadComponent: () =>
        import('./pages/dashboard/dashboard').then(m => m.DashboardComponent),
        canActivate: [canActivateAuth]
    },
    {
        path: '**',
        redirectTo: ''
    }
];
