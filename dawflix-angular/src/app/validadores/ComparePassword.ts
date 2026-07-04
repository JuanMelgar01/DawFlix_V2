import { AbstractControl, ValidationErrors, ValidatorFn } from "@angular/forms";

export const comparadorPassValidator: ValidatorFn = (
        control: AbstractControl,
): ValidationErrors | null =>{
    const password = control.get('password');
    const confirmPass = control.get('confirmPassword');

    return password && confirmPass && password.value === confirmPass.value ? null: {comparePasswords: true};
};