import { Component, inject, signal } from '@angular/core';
import { RouterLink } from '@angular/router';
import Keycloak from 'keycloak-js';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [RouterLink, JsonPipe],
  template: `
    <div style="padding: 2rem; font-family: sans-serif;">
      <h1>🔒 Dashboard (Protegido)</h1>
      <p>Você só vê esta página porque está autenticado.</p>

      <h2>Informações do usuário</h2>
      <ul>
        <li><strong>Username:</strong> {{ username() }}</li>
        <li><strong>Email:</strong> {{ email() }}</li>
        <li><strong>Subject (sub):</strong> {{ subject() }}</li>
      </ul>

      <h2>Roles do realm</h2>
      <pre>{{ realmRoles() | json }}</pre>

      <h2>Token (primeiros 80 caracteres)</h2>
      <pre style="white-space: pre-wrap; word-break: break-all;">
        {{ tokenPreview() }}
      </pre>

      <hr />

      <p>
        <a routerLink="/">⬅️ Voltar para a Home</a>
      </p>

      <button (click)="logout()">Fazer Logout</button>
    </div>
  `
})
export class DashboardComponent {
  private readonly keycloak = inject(Keycloak);

  username = signal<string | undefined>(undefined);
  email = signal<string | undefined>(undefined);
  subject = signal<string | undefined>(undefined);
  realmRoles = signal<string[]>([]);
  tokenPreview = signal<string>('');

  constructor() {
    const parsed = this.keycloak.tokenParsed as any;

    this.username.set(parsed?.preferred_username);
    this.email.set(parsed?.email);
    this.subject.set(parsed?.sub);
    this.realmRoles.set(parsed?.realm_access?.roles ?? []);
    this.tokenPreview.set((this.keycloak.token ?? '').substring(0, 80) + '...');
  }

  logout(): void {
    this.keycloak.logout({
      redirectUri: window.location.origin + '/'
    });
  }
}