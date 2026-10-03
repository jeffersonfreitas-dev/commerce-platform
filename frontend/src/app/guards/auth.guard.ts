import { ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { inject } from '@angular/core';
import { AuthGuardData, createAuthGuard } from 'keycloak-angular';

const isAccessAllowed = async (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot,
  authData: AuthGuardData
): Promise<boolean | UrlTree> => {
  const { authenticated, keycloak } = authData;

  if (!authenticated) {
    await keycloak.login({
      redirectUri: 'http://localhost:4200' + state.url
    });
    return false;
  }

  return true;
};

export const canActivateAuth = createAuthGuard<CanActivateFn>(isAccessAllowed);