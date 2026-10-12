// import { ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
// import { AuthGuardData, createAuthGuard } from 'keycloak-angular';

// const isAccessAllowed = async (
//   route: ActivatedRouteSnapshot,
//   state: RouterStateSnapshot,
//   authData: AuthGuardData
// ): Promise<boolean | UrlTree> => {
//   const { authenticated, keycloak } = authData;

//   if (!authenticated) {
//     await keycloak.login({
//       redirectUri: 'http://localhost:4200' + state.url
//     });
//     return false;
//   }

//   return true;
// };

// export const canActivateAuth = createAuthGuard<CanActivateFn>(isAccessAllowed);

import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

export const authGuard: CanActivateFn = () => {
  const auth = inject(AuthService);
  const router = inject(Router);
  if (auth.isLogged) return true;
  // Redireciona direto para o Keycloak (o login que você já tem)
  auth.login();
  return false;
};