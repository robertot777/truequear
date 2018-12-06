import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IValoracion } from 'app/shared/model/valoracion.model';
import { Principal } from 'app/core';
import { ValoracionService } from './valoracion.service';

@Component({
    selector: 'jhi-valoracion',
    templateUrl: './valoracion.component.html'
})
export class ValoracionComponent implements OnInit, OnDestroy {
    valoracions: IValoracion[];
    currentAccount: any;
    eventSubscriber: Subscription;
    currentSearch: string;

    constructor(
        private valoracionService: ValoracionService,
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
            this.valoracionService
                .search({
                    query: this.currentSearch
                })
                .subscribe(
                    (res: HttpResponse<IValoracion[]>) => (this.valoracions = res.body),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
            return;
        }
        this.valoracionService.query().subscribe(
            (res: HttpResponse<IValoracion[]>) => {
                this.valoracions = res.body;
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
        this.registerChangeInValoracions();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IValoracion) {
        return item.id;
    }

    registerChangeInValoracions() {
        this.eventSubscriber = this.eventManager.subscribe('valoracionListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
