import { Routes } from '@angular/router';

import { AuthPageComponent } from './componentes/inicio/auth-page/auth-page.component';
import { IntroComponent } from './componentes/inicio/intro/intro.component';
import { LandingComponent } from './componentes/inicio/landing/landing.component';
import { PublicLayout } from './componentes/layouts/public-layout/public-layout';
import { PrivateLayout } from './componentes/layouts/private-layout/private-layout';

export const routes: Routes = [
	{
		path: '',
		component: PublicLayout,
		children: [
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
			}
		]
	},
	{
    	path: '',
		component: PrivateLayout,
		// canActivate: [authGuard],
		children: [
			{
				path: 'home',
				loadComponent: () => import('./componentes/zonaApp/home').then((m) => m.Home),
			},
		]
	},
	{
		path: '**',
		redirectTo: '',
	},
];
