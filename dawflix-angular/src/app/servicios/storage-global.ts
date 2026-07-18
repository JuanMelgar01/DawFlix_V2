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
    this._usuario.set(user);

    if (user) {
      sessionStorage.setItem(
        'user',
        JSON.stringify(user)
      );
    } else {
      sessionStorage.removeItem('user');
    }
  }

  SetTokens(tokens: IJwtTokens | null): void {
    this._tokens.set(tokens);

    if (tokens) {
      sessionStorage.setItem(
        'tokens',
        JSON.stringify(tokens)
      );
    } else {
      sessionStorage.removeItem('tokens');
    }
  }

  getUser() {
    return this._usuario.asReadonly();
  }

  getTokens() {
    return this._tokens.asReadonly();
  }

  initialize(): void {

    const user = sessionStorage.getItem('user');
    const tokens = sessionStorage.getItem('tokens');

    if (user) {
      this._usuario.set( JSON.parse(user));
    }

    if (tokens) {
      this._tokens.set( JSON.parse(tokens));
    }
  }

}