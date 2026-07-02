import { inject } from '@angular/core';
import { AbstractControl, AsyncValidatorFn } from '@angular/forms';
import { of, timer } from 'rxjs';
import { catchError, map, switchMap } from 'rxjs/operators';

import { ServAPIBack } from '../servicios/serv-apiback';

export function emailExisteValidator(): AsyncValidatorFn {
    const userService = inject(ServAPIBack);
    return (control: AbstractControl) => {
        if (!control.value) {
            return of(null);
        }

        return timer(400).pipe(
            switchMap(email =>
                userService.ComprobarEmailExistente(control.value)
            ),
            map(existe => existe ? { emailExiste: true } : null),
            catchError(() => of(null))
        );
    };

}