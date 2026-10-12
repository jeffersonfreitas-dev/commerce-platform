import { Injectable, signal } from '@angular/core';

export interface User {
  id: string;
  name: string;
  email: string;
  token: string;
}

@Injectable({ providedIn: 'root' })
export class AuthService {
  private _user = signal<User | null>(null);
  readonly user = this._user.asReadonly();

  constructor() {
    // Hidrata do localStorage (ou do keycloak-angular, se preferir)
    const raw = localStorage.getItem('ecom_user');
    if (raw) {
      try { this._user.set(JSON.parse(raw)); } catch { /* noop */ }
    }
  }

  get isLogged(): boolean {
    return !!this._user();
  }

  /** Chame isso após o login do Keycloak retornar. */
  setUser(u: User) {
    this._user.set(u);
    localStorage.setItem('ecom_user', JSON.stringify(u));
  }

  logout() {
    this._user.set(null);
    localStorage.removeItem('ecom_user');
    // redireciona para o Keycloak logout
    const issuer = 'http://localhost:8080/realms/SEU_REALM';
    const redirect = encodeURIComponent(window.location.origin);
    window.location.href = `${issuer}/protocol/openid-connect/logout?redirect_uri=${redirect}`;
  }

  /** Redireciona para o login Keycloak. */
  login() {
    const issuer = 'http://localhost:8080/realms/SEU_REALM';
    const clientId = 'ecom-web';
    const redirect = encodeURIComponent(window.location.origin + '/auth/callback');
    window.location.href =
      `${issuer}/protocol/openid-connect/auth?client_id=${clientId}` +
      `&response_type=code&scope=openid&redirect_uri=${redirect}`;
  }
}