import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from './shared/components/navbar.compoment/navbar.compoment';
import { FooterComponent } from './shared/components/footer.compoment';
import { ToastComponent } from './shared/components/toast.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NavbarComponent, FooterComponent, ToastComponent],
  template: `
    <app-navbar></app-navbar>
    <main class="app-main">
      <router-outlet></router-outlet>
    </main>
    <app-footer></app-footer>
    <app-toast></app-toast>
  `,
  styles: [`
    :host { display: flex; flex-direction: column; min-height: 100vh; }
    .app-main { flex: 1 0 auto; }
  `]
})
export class AppComponent {}