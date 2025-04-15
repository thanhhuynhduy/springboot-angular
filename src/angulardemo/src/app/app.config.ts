import { ApplicationConfig, provideZoneChangeDetection, isDevMode } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideState, provideStore } from '@ngrx/store';
import { provideStoreDevtools } from '@ngrx/store-devtools';
import { provideEffects } from '@ngrx/effects';
import {HTTP_INTERCEPTORS, provideHttpClient, withInterceptorsFromDi} from '@angular/common/http';
import {UniversalAppInterceptor} from './universal-app.interceptor';
import { provideClientHydration, withEventReplay } from '@angular/platform-browser';
import { AntiHeroEffects } from './state/anti-hero.effects';
import { antiHeroReducer } from './state/anti-hero.reducers';

export const appConfig: ApplicationConfig = {
  providers: [provideZoneChangeDetection({ eventCoalescing: true }), provideRouter(routes), provideHttpClient(withInterceptorsFromDi()),
    {
      provide: HTTP_INTERCEPTORS,
      useClass: UniversalAppInterceptor,
      multi:true
    }, provideStore(), provideStoreDevtools({ maxAge: 25, logOnly: !isDevMode() }), provideEffects([AntiHeroEffects]), 
    provideStore(), provideState({name:'antiHeroReducer', reducer: antiHeroReducer}), 
    provideClientHydration(withEventReplay())]
};
