import { ApplicationConfig, provideBrowserGlobalErrorListeners } from '@angular/core';
import { provideRouter } from '@angular/router';
import { routes } from './app.routes';
import { provideKeycloak } from 'keycloak-angular';

export const appConfig: ApplicationConfig = {
  providers: [
    provideKeycloak({
      config: {
        url: 'http://keycloak:8080',
        realm: 'commerce-platform',
        clientId: 'web'
      },
      initOptions: {
        onLoad: 'check-sso',
        silentCheckSsoRedirectUri: 'http://localhost:4200/silent-check-sso.html',
        redirectUri: 'http://localhost:4200',
        pkceMethod: 'S256'
      }            
    }),
    provideBrowserGlobalErrorListeners(),
    provideRouter(routes)
  ]
};
