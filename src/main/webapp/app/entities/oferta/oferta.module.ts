import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TruequearSharedModule } from 'app/shared';
import { TruequearAdminModule } from 'app/admin/admin.module';
import {
    OfertaComponent,
    OfertaDetailComponent,
    OfertaUpdateComponent,
    OfertaDeletePopupComponent,
    OfertaDeleteDialogComponent,
    ofertaRoute,
    ofertaPopupRoute
} from './';

const ENTITY_STATES = [...ofertaRoute, ...ofertaPopupRoute];

@NgModule({
    imports: [TruequearSharedModule, TruequearAdminModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [OfertaComponent, OfertaDetailComponent, OfertaUpdateComponent, OfertaDeleteDialogComponent, OfertaDeletePopupComponent],
    entryComponents: [OfertaComponent, OfertaUpdateComponent, OfertaDeleteDialogComponent, OfertaDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TruequearOfertaModule {}
