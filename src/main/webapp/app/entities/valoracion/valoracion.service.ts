import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IValoracion } from 'app/shared/model/valoracion.model';

type EntityResponseType = HttpResponse<IValoracion>;
type EntityArrayResponseType = HttpResponse<IValoracion[]>;

@Injectable({ providedIn: 'root' })
export class ValoracionService {
    public resourceUrl = SERVER_API_URL + 'api/valoracions';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/valoracions';

    constructor(private http: HttpClient) {}

    create(valoracion: IValoracion): Observable<EntityResponseType> {
        return this.http.post<IValoracion>(this.resourceUrl, valoracion, { observe: 'response' });
    }

    update(valoracion: IValoracion): Observable<EntityResponseType> {
        return this.http.put<IValoracion>(this.resourceUrl, valoracion, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IValoracion>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IValoracion[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IValoracion[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
    }
}
