import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IRespuestaOferta } from 'app/shared/model/respuesta-oferta.model';
import { RespuestaOfertaService } from './respuesta-oferta.service';
import { IOferta } from 'app/shared/model/oferta.model';
import { OfertaService } from 'app/entities/oferta';

@Component({
    selector: 'jhi-respuesta-oferta-update',
    templateUrl: './respuesta-oferta-update.component.html'
})
export class RespuestaOfertaUpdateComponent implements OnInit {
    respuestaOferta: IRespuestaOferta;
    isSaving: boolean;

    generarespuestas: IOferta[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private respuestaOfertaService: RespuestaOfertaService,
        private ofertaService: OfertaService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ respuestaOferta }) => {
            this.respuestaOferta = respuestaOferta;
        });
        this.ofertaService.query({ filter: 'respuestaoferta-is-null' }).subscribe(
            (res: HttpResponse<IOferta[]>) => {
                if (!this.respuestaOferta.generaRespuestasId) {
                    this.generarespuestas = res.body;
                } else {
                    this.ofertaService.find(this.respuestaOferta.generaRespuestasId).subscribe(
                        (subRes: HttpResponse<IOferta>) => {
                            this.generarespuestas = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.respuestaOferta.id !== undefined) {
            this.subscribeToSaveResponse(this.respuestaOfertaService.update(this.respuestaOferta));
        } else {
            this.subscribeToSaveResponse(this.respuestaOfertaService.create(this.respuestaOferta));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRespuestaOferta>>) {
        result.subscribe((res: HttpResponse<IRespuestaOferta>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackOfertaById(index: number, item: IOferta) {
        return item.id;
    }
}
