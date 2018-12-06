import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IComuna } from 'app/shared/model/comuna.model';
import { ComunaService } from './comuna.service';
import { IRegion } from 'app/shared/model/region.model';
import { RegionService } from 'app/entities/region';

@Component({
    selector: 'jhi-comuna-update',
    templateUrl: './comuna-update.component.html'
})
export class ComunaUpdateComponent implements OnInit {
    comuna: IComuna;
    isSaving: boolean;

    regions: IRegion[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private comunaService: ComunaService,
        private regionService: RegionService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ comuna }) => {
            this.comuna = comuna;
        });
        this.regionService.query().subscribe(
            (res: HttpResponse<IRegion[]>) => {
                this.regions = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.comuna.id !== undefined) {
            this.subscribeToSaveResponse(this.comunaService.update(this.comuna));
        } else {
            this.subscribeToSaveResponse(this.comunaService.create(this.comuna));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IComuna>>) {
        result.subscribe((res: HttpResponse<IComuna>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackRegionById(index: number, item: IRegion) {
        return item.id;
    }
}
