import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IOferta } from 'app/shared/model/oferta.model';

type EntityResponseType = HttpResponse<IOferta>;
type EntityArrayResponseType = HttpResponse<IOferta[]>;

@Injectable({ providedIn: 'root' })
export class OfertaService {
    public resourceUrl = SERVER_API_URL + 'api/ofertas';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/ofertas';

    constructor(private http: HttpClient) {}

    create(oferta: IOferta): Observable<EntityResponseType> {
        return this.http.post<IOferta>(this.resourceUrl, oferta, { observe: 'response' });
    }

    update(oferta: IOferta): Observable<EntityResponseType> {
        return this.http.put<IOferta>(this.resourceUrl, oferta, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IOferta>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IOferta[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IOferta[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
    }
}
