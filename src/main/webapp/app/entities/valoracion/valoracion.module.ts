import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TruequearSharedModule } from 'app/shared';
import {
    ValoracionComponent,
    ValoracionDetailComponent,
    ValoracionUpdateComponent,
    ValoracionDeletePopupComponent,
    ValoracionDeleteDialogComponent,
    valoracionRoute,
    valoracionPopupRoute
} from './';

const ENTITY_STATES = [...valoracionRoute, ...valoracionPopupRoute];

@NgModule({
    imports: [TruequearSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ValoracionComponent,
        ValoracionDetailComponent,
        ValoracionUpdateComponent,
        ValoracionDeleteDialogComponent,
        ValoracionDeletePopupComponent
    ],
    entryComponents: [ValoracionComponent, ValoracionUpdateComponent, ValoracionDeleteDialogComponent, ValoracionDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TruequearValoracionModule {}
