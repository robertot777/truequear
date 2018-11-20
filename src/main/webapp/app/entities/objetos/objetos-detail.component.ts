import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IObjetos } from 'app/shared/model/objetos.model';

@Component({
    selector: 'jhi-objetos-detail',
    templateUrl: './objetos-detail.component.html'
})
export class ObjetosDetailComponent implements OnInit {
    objetos: IObjetos;

    constructor(private dataUtils: JhiDataUtils, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ objetos }) => {
            this.objetos = objetos;
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
