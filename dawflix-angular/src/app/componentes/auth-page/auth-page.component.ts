import { Component, inject } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';

type AuthMode = 'login' | 'register';

@Component({
  selector: 'app-auth-page',
  imports: [RouterLink],
  templateUrl: './auth-page.component.html',
  styleUrl: './auth-page.component.scss',
})
export class AuthPageComponent {
  private readonly route = inject(ActivatedRoute);

  protected readonly mode = (this.route.snapshot.data['mode'] ?? 'login') as AuthMode;
  protected readonly title = this.mode === 'login' ? 'Iniciar sesión' : 'Crear cuenta';
  protected readonly text =
    this.mode === 'login'
      ? 'Aquí irá el formulario de acceso cuando conectes autenticación real.'
      : 'Aquí irá el formulario de registro cuando conectes autenticación real.';
}