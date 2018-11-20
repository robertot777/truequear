export interface IRegion {
    id?: number;
    nombreRegion?: string;
}

export class Region implements IRegion {
    constructor(public id?: number, public nombreRegion?: string) {}
}
