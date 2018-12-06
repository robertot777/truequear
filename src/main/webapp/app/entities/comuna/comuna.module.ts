import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TruequearSharedModule } from 'app/shared';
import {
    ComunaComponent,
    ComunaDetailComponent,
    ComunaUpdateComponent,
    ComunaDeletePopupComponent,
    ComunaDeleteDialogComponent,
    comunaRoute,
    comunaPopupRoute
} from './';

const ENTITY_STATES = [...comunaRoute, ...comunaPopupRoute];

@NgModule({
    imports: [TruequearSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [ComunaComponent, ComunaDetailComponent, ComunaUpdateComponent, ComunaDeleteDialogComponent, ComunaDeletePopupComponent],
    entryComponents: [ComunaComponent, ComunaUpdateComponent, ComunaDeleteDialogComponent, ComunaDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TruequearComunaModule {}
