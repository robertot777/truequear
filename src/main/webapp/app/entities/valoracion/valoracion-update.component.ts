import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IValoracion } from 'app/shared/model/valoracion.model';
import { ValoracionService } from './valoracion.service';
import { ICliente } from 'app/shared/model/cliente.model';
import { ClienteService } from 'app/entities/cliente';

@Component({
    selector: 'jhi-valoracion-update',
    templateUrl: './valoracion-update.component.html'
})
export class ValoracionUpdateComponent implements OnInit {
    valoracion: IValoracion;
    isSaving: boolean;

    clientes: ICliente[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private valoracionService: ValoracionService,
        private clienteService: ClienteService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ valoracion }) => {
            this.valoracion = valoracion;
        });
        this.clienteService.query().subscribe(
            (res: HttpResponse<ICliente[]>) => {
                this.clientes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.valoracion.id !== undefined) {
            this.subscribeToSaveResponse(this.valoracionService.update(this.valoracion));
        } else {
            this.subscribeToSaveResponse(this.valoracionService.create(this.valoracion));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IValoracion>>) {
        result.subscribe((res: HttpResponse<IValoracion>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackClienteById(index: number, item: ICliente) {
        return item.id;
    }
}
