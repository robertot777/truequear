import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IOferta } from 'app/shared/model/oferta.model';

@Component({
    selector: 'jhi-oferta-detail',
    templateUrl: './oferta-detail.component.html'
})
export class OfertaDetailComponent implements OnInit {
    oferta: IOferta;

    constructor(private dataUtils: JhiDataUtils, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ oferta }) => {
            this.oferta = oferta;
        });
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }
}
