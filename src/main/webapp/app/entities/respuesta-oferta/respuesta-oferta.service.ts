import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRespuestaOferta } from 'app/shared/model/respuesta-oferta.model';

type EntityResponseType = HttpResponse<IRespuestaOferta>;
type EntityArrayResponseType = HttpResponse<IRespuestaOferta[]>;

@Injectable({ providedIn: 'root' })
export class RespuestaOfertaService {
    public resourceUrl = SERVER_API_URL + 'api/respuesta-ofertas';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/respuesta-ofertas';

    constructor(private http: HttpClient) {}

    create(respuestaOferta: IRespuestaOferta): Observable<EntityResponseType> {
        return this.http.post<IRespuestaOferta>(this.resourceUrl, respuestaOferta, { observe: 'response' });
    }

    update(respuestaOferta: IRespuestaOferta): Observable<EntityResponseType> {
        return this.http.put<IRespuestaOferta>(this.resourceUrl, respuestaOferta, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IRespuestaOferta>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IRespuestaOferta[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IRespuestaOferta[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
    }
}
