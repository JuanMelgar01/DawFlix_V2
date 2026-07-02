import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { Observable } from "rxjs";
import IRespuestaBack from "../modelos/IRespuestaBack";

@Injectable({
    providedIn:'root'
})
export class ServAPIBack{

    private http = inject(HttpClient);

    public Registro(datosRegistro:{
        username: string,
        email: string,
        password: string
    }):Observable<IRespuestaBack>{
        return this.http
                    .post<IRespuestaBack>('http://localhost:8084/api/user/register',
                        datosRegistro,
                        {
                            headers: {'Content-Type': 'application/json'}
                        }
                    )
    }

    public Login (datosLogin:{
        username:string,
        password:string
    }):Observable<IRespuestaBack>{
        return this.http
                    .post<IRespuestaBack>('http://localhost:8084/api/user/login',
                        datosLogin,
                        {
                            headers:{'Content-Type': 'application/json'}
                        }
                    )
    }

    public ComprobarEmailExistente(email:string):Observable<boolean> {
        console.log("Email recibido en el servicio: " + email);
        return this.http
        .get<boolean>(
            `http://localhost:8084/api/user/check-email?email=${encodeURIComponent(email)}`,
            {
                headers: {'Content-Type': 'application/json'}
            }
        )
    }
}