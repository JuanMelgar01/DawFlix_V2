import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { passValidator } from '../../validadores/PassValidator';
import { ServAPIBack } from '../../servicios/serv-apiback';
import IRespuestaBack from '../../modelos/IRespuestaBack';
import { emailExisteValidator } from '../../validadores/EmailExisteValidator';

@Component({
  selector: 'app-registro',
  imports: [ReactiveFormsModule],
  templateUrl: './registro.html',
  styleUrl: './registro.scss',
})
export class Registro {

  private servAPIBack = inject(ServAPIBack);
  private router:Router = inject(Router);
  public mostrarModalError: boolean = false;
  public mensajeError: string = '';

  formulario: FormGroup = new FormGroup(
    {
      username: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(50)]),
      email: new FormControl('', {
        validators: [Validators.required, Validators.email],
        asyncValidators: [emailExisteValidator()],
        updateOn: 'blur'
      }),
      password: new FormControl('', [Validators.required, passValidator])
    }
  );

  mostrarError(mensaje: string): void {
    this.mostrarModalError = true;
    this.mensajeError = mensaje;
  };

  EnviarDatosRegistro():void{
      this.servAPIBack
        .Registro(this.formulario.value)
        .subscribe(
          (respuesta:IRespuestaBack) =>{
            if(respuesta.codigo === 0){
              this.router.navigate(['/login'],{
              })
            }else{
              this.mostrarError(respuesta.mensaje);
            }
          }
        )
  };
}
