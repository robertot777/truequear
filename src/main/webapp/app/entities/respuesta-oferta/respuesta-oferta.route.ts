import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RespuestaOferta } from 'app/shared/model/respuesta-oferta.model';
import { RespuestaOfertaService } from './respuesta-oferta.service';
import { RespuestaOfertaComponent } from './respuesta-oferta.component';
import { RespuestaOfertaDetailComponent } from './respuesta-oferta-detail.component';
import { RespuestaOfertaUpdateComponent } from './respuesta-oferta-update.component';
import { RespuestaOfertaDeletePopupComponent } from './respuesta-oferta-delete-dialog.component';
import { IRespuestaOferta } from 'app/shared/model/respuesta-oferta.model';

@Injectable({ providedIn: 'root' })
export class RespuestaOfertaResolve implements Resolve<IRespuestaOferta> {
    constructor(private service: RespuestaOfertaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<RespuestaOferta> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RespuestaOferta>) => response.ok),
                map((respuestaOferta: HttpResponse<RespuestaOferta>) => respuestaOferta.body)
            );
        }
        return of(new RespuestaOferta());
    }
}

export const respuestaOfertaRoute: Routes = [
    {
        path: 'respuesta-oferta',
        component: RespuestaOfertaComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'truequearApp.respuestaOferta.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'respuesta-oferta/:id/view',
        component: RespuestaOfertaDetailComponent,
        resolve: {
            respuestaOferta: RespuestaOfertaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'truequearApp.respuestaOferta.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'respuesta-oferta/new',
        component: RespuestaOfertaUpdateComponent,
        resolve: {
            respuestaOferta: RespuestaOfertaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'truequearApp.respuestaOferta.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'respuesta-oferta/:id/edit',
        component: RespuestaOfertaUpdateComponent,
        resolve: {
            respuestaOferta: RespuestaOfertaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'truequearApp.respuestaOferta.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const respuestaOfertaPopupRoute: Routes = [
    {
        path: 'respuesta-oferta/:id/delete',
        component: RespuestaOfertaDeletePopupComponent,
        resolve: {
            respuestaOferta: RespuestaOfertaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'truequearApp.respuestaOferta.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
