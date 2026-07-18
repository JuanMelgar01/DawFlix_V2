import { HttpHandlerFn, HttpInterceptorFn, HttpRequest } from '@angular/common/http';
import { inject } from '@angular/core/primitives/di';
import { StorageGlobal } from '../servicios/storage-global';


export const authInterceptor: HttpInterceptorFn = (req:HttpRequest<any>, next:HttpHandlerFn) => {
    const storage = inject(StorageGlobal);
    const accessToken = storage.getTokens()()
                                ?.accessToken;
    
    if (!accessToken) {
        return next(req);
    }
    
    const authReq = req.clone({
        setHeaders: {
            Authorization: `Bearer ${accessToken}`
        }
    });

    return next(authReq);
}