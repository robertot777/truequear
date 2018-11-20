export interface IComuna {
    id?: number;
    nombreComuna?: string;
    regionId?: number;
}

export class Comuna implements IComuna {
    constructor(public id?: number, public nombreComuna?: string, public regionId?: number) {}
}
