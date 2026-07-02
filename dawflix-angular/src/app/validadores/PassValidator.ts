import { AbstractControl, ValidationErrors, ValidatorFn } from "@angular/forms";

export const passValidator:ValidatorFn = (control: AbstractControl): ValidationErrors | null =>{
    const password = control.value;
    const errores:any = {};

    if(password){
        if (!/[A-Z]/.test(password)) {
            errores['mayuscula'] = true;
        }

        if (!/[a-z]/.test(password)) {
            errores['minuscula'] = true;
        }

        if (!/[0-9]/.test(password)) {
            errores['numero'] = true;
        }

        if (!/[#?!@$%^&*-]/.test(password)) {
            errores['especial'] = true;
        }

        if (password.length < 6) {
            errores['longitud'] = true;
        }
    }

    return Object.keys(errores).length ? errores : null;
}