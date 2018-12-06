import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TruequearSharedModule } from 'app/shared';
import { TruequearAdminModule } from 'app/admin/admin.module';
import {
    DireccionComponent,
    DireccionDetailComponent,
    DireccionUpdateComponent,
    DireccionDeletePopupComponent,
    DireccionDeleteDialogComponent,
    direccionRoute,
    direccionPopupRoute
} from './';

const ENTITY_STATES = [...direccionRoute, ...direccionPopupRoute];

@NgModule({
    imports: [TruequearSharedModule, TruequearAdminModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        DireccionComponent,
        DireccionDetailComponent,
        DireccionUpdateComponent,
        DireccionDeleteDialogComponent,
        DireccionDeletePopupComponent
    ],
    entryComponents: [DireccionComponent, DireccionUpdateComponent, DireccionDeleteDialogComponent, DireccionDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TruequearDireccionModule {}
