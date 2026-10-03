import { Component, inject, signal } from '@angular/core';
import { RouterLink } from '@angular/router';
import Keycloak from 'keycloak-js';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [RouterLink],
  template: `
    <div style="padding: 2rem; font-family: sans-serif;">
      <h1>🏠 Página Pública</h1>
      <p>Qualquer pessoa pode ver esta página, sem login.</p>

      <p>
        <strong>Autenticado?</strong>
        {{ isAuthenticated() ? '✅ Sim' : '❌ Não' }}
      </p>

      @if (isAuthenticated()) {
        <button (click)="logout()">Fazer Logout</button>
      } @else {
        <button (click)="login()">Fazer Login</button>
      }

      <hr />

      <p>
        <a routerLink="/dashboard">➡️ Ir para o Dashboard (protegido)</a>
      </p>
    </div>
  `
})
export class HomeComponent {
  private readonly keycloak = inject(Keycloak);

  isAuthenticated = signal<boolean>(false);

  constructor() {
    this.isAuthenticated.set(this.keycloak.authenticated ?? false);
  }

  login(): void {
    this.keycloak.login({
      redirectUri: 'http://localhost:4200'
    });
  }

  logout(): void {
    this.keycloak.logout({
      redirectUri: 'http://localhost:4200'
    });
  }
}