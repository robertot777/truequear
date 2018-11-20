export const enum TipoObjetos {
    ALIMENTACIONBEBIDA = 'ALIMENTACIONBEBIDA',
    ARTEANTIGUEDADE = 'ARTEANTIGUEDADE',
    VEHICULO = 'VEHICULO',
    CASAHOGA = 'CASAHOGA',
    COLECCIONES = 'COLECCIONES',
    DEPORTE = 'DEPORTE',
    EDUCAR = 'EDUCAR',
    IMAGENESSONIDO = 'IMAGENESSONIDO',
    INFORMATICAEELECTRONICA = 'INFORMATICAEELECTRONICA',
    INMOBILIARIAS = 'INMOBILIARIAS',
    JOYERIAS = 'JOYERIAS',
    JUEGOSJUGUETE = 'JUEGOSJUGUETE',
    LIBROSREVISTASCOMIC = 'LIBROSREVISTASCOMIC',
    MUSICAX = 'MUSICAX',
    ROPAYCALZADO = 'ROPAYCALZADO',
    SALUDYBELLEZA = 'SALUDYBELLEZA',
    SERVICIOS = 'SERVICIOS',
    MANODEOBRA = 'MANODEOBRA',
    TELEFONIAS = 'TELEFONIAS',
    TIEMPOLIBRES = 'TIEMPOLIBRES'
}

export interface IObjetos {
    id?: number;
    tipoObjeto?: string;
    agregarArchiboContentType?: string;
    agregarArchibo?: any;
    tipoObjetos?: TipoObjetos;
    descripcion?: string;
    nombreId?: number;
}

export class Objetos implements IObjetos {
    constructor(
        public id?: number,
        public tipoObjeto?: string,
        public agregarArchiboContentType?: string,
        public agregarArchibo?: any,
        public tipoObjetos?: TipoObjetos,
        public descripcion?: string,
        public nombreId?: number
    ) {}
}
