import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRespuestaOferta } from 'app/shared/model/respuesta-oferta.model';

@Component({
    selector: 'jhi-respuesta-oferta-detail',
    templateUrl: './respuesta-oferta-detail.component.html'
})
export class RespuestaOfertaDetailComponent implements OnInit {
    respuestaOferta: IRespuestaOferta;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ respuestaOferta }) => {
            this.respuestaOferta = respuestaOferta;
        });
    }

    previousState() {
        window.history.back();
    }
}
