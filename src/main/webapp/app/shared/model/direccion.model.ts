export interface IDireccion {
    id?: number;
    direccion?: string;
    numero?: string;
    adicional?: string;
    codigoPostal?: string;
    comunaId?: number;
    tipoId?: number;
}

export class Direccion implements IDireccion {
    constructor(
        public id?: number,
        public direccion?: string,
        public numero?: string,
        public adicional?: string,
        public codigoPostal?: string,
        public comunaId?: number,
        public tipoId?: number
    ) {}
}
