export const enum TipoObjeto {
    ALIMENTACIONBEBIDAS = 'ALIMENTACIONBEBIDAS',
    ARTEANTIGUEDADES = 'ARTEANTIGUEDADES',
    VEHICULOS = 'VEHICULOS',
    CASAHOGAR = 'CASAHOGAR',
    COLECCION = 'COLECCION',
    DEPORTES = 'DEPORTES',
    EDUCACION = 'EDUCACION',
    IMAGENSONIDO = 'IMAGENSONIDO',
    INFORMATICAELECTRONICA = 'INFORMATICAELECTRONICA',
    INMOBILIARIA = 'INMOBILIARIA',
    JOYERIA = 'JOYERIA',
    JUEGOSJUGUETES = 'JUEGOSJUGUETES',
    LIBROSREVISTASCOMICS = 'LIBROSREVISTASCOMICS',
    MUSICA = 'MUSICA',
    ROPACALZADO = 'ROPACALZADO',
    SALUDBELLEZA = 'SALUDBELLEZA',
    SERVICIOS = 'SERVICIOS',
    MANOOBRA = 'MANOOBRA',
    TELEFONIA = 'TELEFONIA',
    TIEMPOLIBRE = 'TIEMPOLIBRE'
}

export interface IOferta {
    id?: number;
    generarOferta?: string;
    tipoObjeto?: TipoObjeto;
    generaOfertaContentType?: string;
    generaOferta?: any;
    nombreId?: number;
}

export class Oferta implements IOferta {
    constructor(
        public id?: number,
        public generarOferta?: string,
        public tipoObjeto?: TipoObjeto,
        public generaOfertaContentType?: string,
        public generaOferta?: any,
        public nombreId?: number
    ) {}
}
