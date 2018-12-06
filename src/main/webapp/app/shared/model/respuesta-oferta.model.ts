export interface IRespuestaOferta {
    id?: number;
    aceptar?: boolean;
    generaRespuestasId?: number;
}

export class RespuestaOferta implements IRespuestaOferta {
    constructor(public id?: number, public aceptar?: boolean, public generaRespuestasId?: number) {
        this.aceptar = this.aceptar || false;
    }
}
