export interface ICliente {
    id?: number;
    nombre?: string;
    apellido?: string;
    descripcion?: string;
    perfilId?: number;
}

export class Cliente implements ICliente {
    constructor(
        public id?: number,
        public nombre?: string,
        public apellido?: string,
        public descripcion?: string,
        public perfilId?: number
    ) {}
}
