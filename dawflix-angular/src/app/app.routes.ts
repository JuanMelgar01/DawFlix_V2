import { Routes } from '@angular/router';

import { AuthPageComponent } from './componentes/inicio/auth-page/auth-page.component';
import { IntroComponent } from './componentes/inicio/intro/intro.component';
import { LandingComponent } from './componentes/inicio/landing/landing.component';

export const routes: Routes = [
	{
		path: '',
		component: IntroComponent,
	},
	{
		path: 'landing',
		component: LandingComponent,
	},
	{
		path: 'login',
		loadComponent: () => import('./componentes/inicio/auth-page/auth-page.component').then((m) => m.AuthPageComponent),
	},
	{
		path: 'register',
		loadComponent: () => import('./componentes/inicio/registro/registro').then((m) => m.Registro),
	},
		{
		path: 'home',
		loadComponent: () => import('./componentes/zonaApp/home').then((m) => m.Home),
	},
	{
		path: '**',
		redirectTo: '',
	},
];
