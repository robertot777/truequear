import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TruequearSharedModule } from 'app/shared';
import { TruequearAdminModule } from 'app/admin/admin.module';
import {
    ObjetosComponent,
    ObjetosDetailComponent,
    ObjetosUpdateComponent,
    ObjetosDeletePopupComponent,
    ObjetosDeleteDialogComponent,
    objetosRoute,
    objetosPopupRoute
} from './';

const ENTITY_STATES = [...objetosRoute, ...objetosPopupRoute];

@NgModule({
    imports: [TruequearSharedModule, TruequearAdminModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ObjetosComponent,
        ObjetosDetailComponent,
        ObjetosUpdateComponent,
        ObjetosDeleteDialogComponent,
        ObjetosDeletePopupComponent
    ],
    entryComponents: [ObjetosComponent, ObjetosUpdateComponent, ObjetosDeleteDialogComponent, ObjetosDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TruequearObjetosModule {}
