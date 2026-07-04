import { Component, inject } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { ServAPIBack } from '../../../servicios/serv-apiback';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import IRespuestaBack from '../../../modelos/IRespuestaBack';
import { StorageGlobal } from '../../../servicios/storage-global';

@Component({
  selector: 'app-auth-page',
  imports: [ReactiveFormsModule],
  templateUrl: './auth-page.component.html',
  styleUrl: './auth-page.component.scss',
})
export class AuthPageComponent {

  private servApi = inject(ServAPIBack);
  private storageGlobal = inject(StorageGlobal);
  private router:Router = inject(Router);
  public mostrarModalError: boolean = false;
  public mensajeError: string = '';

  formLogin: FormGroup = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required])
  })

  mostrarError(mensaje: string): void {
    this.mostrarModalError = true;
    this.mensajeError = mensaje;
  };

  EnviarDatosLogin():void{
      this.servApi
        .Login(this.formLogin.value)
        .subscribe(
          (respuesta:IRespuestaBack) =>{
            console.log(respuesta);
            if(respuesta.codigo === 0){
              this.storageGlobal.SetDatosUsuario(respuesta.datos.usuario);
              this.storageGlobal.SetTokens({
                                            accessToken: respuesta.datos.accessToken,
                                            refreshToken: respuesta.datos.refreshToken
                                          });
              this.router.navigate(['/home'],{
              })
            }else{
              this.mostrarError(respuesta.mensaje);
            }
          }
        )
  };

}