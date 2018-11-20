import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { TruequearComunaModule } from './comuna/comuna.module';
import { TruequearRegionModule } from './region/region.module';
import { TruequearDireccionModule } from './direccion/direccion.module';
import { TruequearClienteModule } from './cliente/cliente.module';
import { TruequearValoracionModule } from './valoracion/valoracion.module';
import { TruequearObjetosModule } from './objetos/objetos.module';
import { TruequearOfertaModule } from './oferta/oferta.module';
import { TruequearRespuestaOfertaModule } from './respuesta-oferta/respuesta-oferta.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        TruequearComunaModule,
        TruequearRegionModule,
        TruequearDireccionModule,
        TruequearClienteModule,
        TruequearValoracionModule,
        TruequearObjetosModule,
        TruequearOfertaModule,
        TruequearRespuestaOfertaModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TruequearEntityModule {}
