import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRespuestaOferta } from 'app/shared/model/respuesta-oferta.model';
import { RespuestaOfertaService } from './respuesta-oferta.service';

@Component({
    selector: 'jhi-respuesta-oferta-delete-dialog',
    templateUrl: './respuesta-oferta-delete-dialog.component.html'
})
export class RespuestaOfertaDeleteDialogComponent {
    respuestaOferta: IRespuestaOferta;

    constructor(
        private respuestaOfertaService: RespuestaOfertaService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.respuestaOfertaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'respuestaOfertaListModification',
                content: 'Deleted an respuestaOferta'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-respuesta-oferta-delete-popup',
    template: ''
})
export class RespuestaOfertaDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ respuestaOferta }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RespuestaOfertaDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.respuestaOferta = respuestaOferta;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
