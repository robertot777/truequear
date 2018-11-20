import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IDireccion } from 'app/shared/model/direccion.model';
import { DireccionService } from './direccion.service';
import { IComuna } from 'app/shared/model/comuna.model';
import { ComunaService } from 'app/entities/comuna';
import { IUser, UserService } from 'app/core';

@Component({
    selector: 'jhi-direccion-update',
    templateUrl: './direccion-update.component.html'
})
export class DireccionUpdateComponent implements OnInit {
    direccion: IDireccion;
    isSaving: boolean;

    comunas: IComuna[];

    users: IUser[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private direccionService: DireccionService,
        private comunaService: ComunaService,
        private userService: UserService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ direccion }) => {
            this.direccion = direccion;
        });
        this.comunaService.query({ filter: 'direccion-is-null' }).subscribe(
            (res: HttpResponse<IComuna[]>) => {
                if (!this.direccion.comunaId) {
                    this.comunas = res.body;
                } else {
                    this.comunaService.find(this.direccion.comunaId).subscribe(
                        (subRes: HttpResponse<IComuna>) => {
                            this.comunas = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.userService.query().subscribe(
            (res: HttpResponse<IUser[]>) => {
                this.users = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.direccion.id !== undefined) {
            this.subscribeToSaveResponse(this.direccionService.update(this.direccion));
        } else {
            this.subscribeToSaveResponse(this.direccionService.create(this.direccion));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IDireccion>>) {
        result.subscribe((res: HttpResponse<IDireccion>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackComunaById(index: number, item: IComuna) {
        return item.id;
    }

    trackUserById(index: number, item: IUser) {
        return item.id;
    }
}
