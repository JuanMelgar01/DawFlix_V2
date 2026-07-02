import { Routes } from '@angular/router';

import { AuthPageComponent } from './componentes/auth-page/auth-page.component';
import { IntroComponent } from './componentes/intro/intro.component';
import { LandingComponent } from './componentes/landing/landing.component';

export const routes: Routes = [
	{
		path: '',
		component: IntroComponent,
	},
	{
		path: 'home',
		component: LandingComponent,
	},
	{
		path: 'login',
		component: AuthPageComponent,
		data: {
			mode: 'login',
		},
	},
	{
		path: 'register',
		loadComponent: () => import('./componentes/registro/registro').then((m) => m.Registro),
	},
	{
		path: '**',
		redirectTo: '',
	},
];
