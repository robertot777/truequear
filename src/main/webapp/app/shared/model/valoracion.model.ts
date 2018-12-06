export const enum TipoValoracion {
    EXELENTE = 'EXELENTE',
    BUENA = 'BUENA',
    REGULAR = 'REGULAR',
    POCOSERIO = 'POCOSERIO',
    PESIMO = 'PESIMO'
}

export interface IValoracion {
    id?: number;
    tipoValoracion?: TipoValoracion;
    nombreId?: number;
}

export class Valoracion implements IValoracion {
    constructor(public id?: number, public tipoValoracion?: TipoValoracion, public nombreId?: number) {}
}
