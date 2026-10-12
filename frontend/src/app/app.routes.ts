import { Routes } from '@angular/router';
// import { canActivateAuth } from './core/guards/auth.guard';


// export const routes: Routes = [
//     {
//         path: '',
//         loadComponent: () =>
//         import('./feature/home/home').then(m => m.HomeComponent)
//     },
//     {
//         path: 'login',
//         loadComponent: () =>
//         import('./pages/login/login').then(m => m.LoginComponent)
//     },    
//     {
//         path: 'dashboard',
//         loadComponent: () =>
//         import('./pages/dashboard/dashboard').then(m => m.DashboardComponent),
//         canActivate: [canActivateAuth]
//     },
//     {
//         path: '**',
//         redirectTo: ''
//     }
// ];
import { authGuard } from './core/guards/auth.guard';

export const routes: Routes = [
  {
    path: '',
    loadComponent: () => import('./feature/home/home').then(m => m.HomeComponent),
  },
  {
    path: 'produtos',
    loadComponent: () => import('./feature/catalog/catalog').then(m => m.CatalogComponent),
  },
  {
    path: 'produto/:id',
    loadComponent: () => import('./feature/product-detail/product-detail').then(m => m.ProductDetailComponent),
  },
  {
    path: 'carrinho',
    loadComponent: () => import('./feature/cart/cart').then(m => m.CartComponent),
  },
  {
    path: 'checkout',
    canActivate: [authGuard],
    loadComponent: () => import('./feature/checkout/checkout').then(m => m.CheckoutComponent),
  },
  {
    path: 'pedidos',
    canActivate: [authGuard],
    loadComponent: () => import('./feature/orders/orders').then(m => m.OrdersComponent),
  },
  { path: '**', redirectTo: '' },
];
