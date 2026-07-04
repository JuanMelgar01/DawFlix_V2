import { Injectable, signal, WritableSignal } from "@angular/core";
import IJwtTokens from "../modelos/IJwtTokens";
import IUser from "../modelos/IUser";

@Injectable({
    providedIn: 'root'
})
export class StorageGlobal {
    private _usuario:WritableSignal<IUser | null> = signal<IUser | null>(null);
    private _tokens:WritableSignal<IJwtTokens | null> = signal<IJwtTokens | null>(null);

      SetDatosUsuario(user: IUser | null): void {
    this._usuario.update((datosUserViejos:IUser | null) =>  {
      if(datosUserViejos){
        return {...datosUserViejos, ...user};
      } else {
        return user;
      }
    });

    if (user) {
      sessionStorage.setItem('user', JSON.stringify(user));
    } else {
      sessionStorage.removeItem('user');
    }
  }

  SetTokens(tokens: IJwtTokens | null): void {
    this._tokens.update((tokensViejos: IJwtTokens | null) => {
      if (tokensViejos) {
        return { ...tokensViejos, ...tokens };
      } else {
        return tokens;
      }
    });
    if (tokens) {
      sessionStorage.setItem('tokens', JSON.stringify(tokens));
    } else {
      sessionStorage.removeItem('tokens');
    }
  }

}