import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IRespuestaOferta } from 'app/shared/model/respuesta-oferta.model';
import { Principal } from 'app/core';
import { RespuestaOfertaService } from './respuesta-oferta.service';

@Component({
    selector: 'jhi-respuesta-oferta',
    templateUrl: './respuesta-oferta.component.html'
})
export class RespuestaOfertaComponent implements OnInit, OnDestroy {
    respuestaOfertas: IRespuestaOferta[];
    currentAccount: any;
    eventSubscriber: Subscription;
    currentSearch: string;

    constructor(
        private respuestaOfertaService: RespuestaOfertaService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private activatedRoute: ActivatedRoute,
        private principal: Principal
    ) {
        this.currentSearch =
            this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['search']
                ? this.activatedRoute.snapshot.params['search']
                : '';
    }

    loadAll() {
        if (this.currentSearch) {
            this.respuestaOfertaService
                .search({
                    query: this.currentSearch
                })
                .subscribe(
                    (res: HttpResponse<IRespuestaOferta[]>) => (this.respuestaOfertas = res.body),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
            return;
        }
        this.respuestaOfertaService.query().subscribe(
            (res: HttpResponse<IRespuestaOferta[]>) => {
                this.respuestaOfertas = res.body;
                this.currentSearch = '';
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    search(query) {
        if (!query) {
            return this.clear();
        }
        this.currentSearch = query;
        this.loadAll();
    }

    clear() {
        this.currentSearch = '';
        this.loadAll();
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInRespuestaOfertas();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IRespuestaOferta) {
        return item.id;
    }

    registerChangeInRespuestaOfertas() {
        this.eventSubscriber = this.eventManager.subscribe('respuestaOfertaListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
