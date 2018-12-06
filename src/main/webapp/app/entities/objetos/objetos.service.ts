import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IObjetos } from 'app/shared/model/objetos.model';

type EntityResponseType = HttpResponse<IObjetos>;
type EntityArrayResponseType = HttpResponse<IObjetos[]>;

@Injectable({ providedIn: 'root' })
export class ObjetosService {
    public resourceUrl = SERVER_API_URL + 'api/objetos';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/objetos';

    constructor(private http: HttpClient) {}

    create(objetos: IObjetos): Observable<EntityResponseType> {
        return this.http.post<IObjetos>(this.resourceUrl, objetos, { observe: 'response' });
    }

    update(objetos: IObjetos): Observable<EntityResponseType> {
        return this.http.put<IObjetos>(this.resourceUrl, objetos, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IObjetos>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IObjetos[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IObjetos[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
    }
}
