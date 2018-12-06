import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Valoracion } from 'app/shared/model/valoracion.model';
import { ValoracionService } from './valoracion.service';
import { ValoracionComponent } from './valoracion.component';
import { ValoracionDetailComponent } from './valoracion-detail.component';
import { ValoracionUpdateComponent } from './valoracion-update.component';
import { ValoracionDeletePopupComponent } from './valoracion-delete-dialog.component';
import { IValoracion } from 'app/shared/model/valoracion.model';

@Injectable({ providedIn: 'root' })
export class ValoracionResolve implements Resolve<IValoracion> {
    constructor(private service: ValoracionService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Valoracion> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Valoracion>) => response.ok),
                map((valoracion: HttpResponse<Valoracion>) => valoracion.body)
            );
        }
        return of(new Valoracion());
    }
}

export const valoracionRoute: Routes = [
    {
        path: 'valoracion',
        component: ValoracionComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'truequearApp.valoracion.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'valoracion/:id/view',
        component: ValoracionDetailComponent,
        resolve: {
            valoracion: ValoracionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'truequearApp.valoracion.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'valoracion/new',
        component: ValoracionUpdateComponent,
        resolve: {
            valoracion: ValoracionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'truequearApp.valoracion.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'valoracion/:id/edit',
        component: ValoracionUpdateComponent,
        resolve: {
            valoracion: ValoracionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'truequearApp.valoracion.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const valoracionPopupRoute: Routes = [
    {
        path: 'valoracion/:id/delete',
        component: ValoracionDeletePopupComponent,
        resolve: {
            valoracion: ValoracionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'truequearApp.valoracion.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
