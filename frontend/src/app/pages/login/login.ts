import { Component, inject } from '@angular/core';
import Keycloak from 'keycloak-js';

@Component({
  selector: 'app-login',
  standalone: true,
  template: `
    <button (click)="login()">Entrar com Keycloak</button>
  `
})
export class LoginComponent {
  private readonly keycloak = inject(Keycloak);
  login(): void {
    this.keycloak.login({
      redirectUri: window.location.origin + '/dashboard'
    });
  }  
}
