import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Objetos } from 'app/shared/model/objetos.model';
import { ObjetosService } from './objetos.service';
import { ObjetosComponent } from './objetos.component';
import { ObjetosDetailComponent } from './objetos-detail.component';
import { ObjetosUpdateComponent } from './objetos-update.component';
import { ObjetosDeletePopupComponent } from './objetos-delete-dialog.component';
import { IObjetos } from 'app/shared/model/objetos.model';

@Injectable({ providedIn: 'root' })
export class ObjetosResolve implements Resolve<IObjetos> {
    constructor(private service: ObjetosService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Objetos> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Objetos>) => response.ok),
                map((objetos: HttpResponse<Objetos>) => objetos.body)
            );
        }
        return of(new Objetos());
    }
}

export const objetosRoute: Routes = [
    {
        path: 'objetos',
        component: ObjetosComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'truequearApp.objetos.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'objetos/:id/view',
        component: ObjetosDetailComponent,
        resolve: {
            objetos: ObjetosResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'truequearApp.objetos.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'objetos/new',
        component: ObjetosUpdateComponent,
        resolve: {
            objetos: ObjetosResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'truequearApp.objetos.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'objetos/:id/edit',
        component: ObjetosUpdateComponent,
        resolve: {
            objetos: ObjetosResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'truequearApp.objetos.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const objetosPopupRoute: Routes = [
    {
        path: 'objetos/:id/delete',
        component: ObjetosDeletePopupComponent,
        resolve: {
            objetos: ObjetosResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'truequearApp.objetos.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
