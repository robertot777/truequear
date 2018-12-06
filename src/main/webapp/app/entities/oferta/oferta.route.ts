import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Oferta } from 'app/shared/model/oferta.model';
import { OfertaService } from './oferta.service';
import { OfertaComponent } from './oferta.component';
import { OfertaDetailComponent } from './oferta-detail.component';
import { OfertaUpdateComponent } from './oferta-update.component';
import { OfertaDeletePopupComponent } from './oferta-delete-dialog.component';
import { IOferta } from 'app/shared/model/oferta.model';

@Injectable({ providedIn: 'root' })
export class OfertaResolve implements Resolve<IOferta> {
    constructor(private service: OfertaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Oferta> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Oferta>) => response.ok),
                map((oferta: HttpResponse<Oferta>) => oferta.body)
            );
        }
        return of(new Oferta());
    }
}

export const ofertaRoute: Routes = [
    {
        path: 'oferta',
        component: OfertaComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'truequearApp.oferta.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'oferta/:id/view',
        component: OfertaDetailComponent,
        resolve: {
            oferta: OfertaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'truequearApp.oferta.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'oferta/new',
        component: OfertaUpdateComponent,
        resolve: {
            oferta: OfertaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'truequearApp.oferta.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'oferta/:id/edit',
        component: OfertaUpdateComponent,
        resolve: {
            oferta: OfertaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'truequearApp.oferta.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const ofertaPopupRoute: Routes = [
    {
        path: 'oferta/:id/delete',
        component: OfertaDeletePopupComponent,
        resolve: {
            oferta: OfertaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'truequearApp.oferta.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
