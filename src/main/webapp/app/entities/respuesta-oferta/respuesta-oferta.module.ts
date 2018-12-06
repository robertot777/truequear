import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TruequearSharedModule } from 'app/shared';
import {
    RespuestaOfertaComponent,
    RespuestaOfertaDetailComponent,
    RespuestaOfertaUpdateComponent,
    RespuestaOfertaDeletePopupComponent,
    RespuestaOfertaDeleteDialogComponent,
    respuestaOfertaRoute,
    respuestaOfertaPopupRoute
} from './';

const ENTITY_STATES = [...respuestaOfertaRoute, ...respuestaOfertaPopupRoute];

@NgModule({
    imports: [TruequearSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RespuestaOfertaComponent,
        RespuestaOfertaDetailComponent,
        RespuestaOfertaUpdateComponent,
        RespuestaOfertaDeleteDialogComponent,
        RespuestaOfertaDeletePopupComponent
    ],
    entryComponents: [
        RespuestaOfertaComponent,
        RespuestaOfertaUpdateComponent,
        RespuestaOfertaDeleteDialogComponent,
        RespuestaOfertaDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TruequearRespuestaOfertaModule {}
