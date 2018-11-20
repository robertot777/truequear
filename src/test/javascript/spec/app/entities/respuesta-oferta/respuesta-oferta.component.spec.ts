/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { TruequearTestModule } from '../../../test.module';
import { RespuestaOfertaComponent } from 'app/entities/respuesta-oferta/respuesta-oferta.component';
import { RespuestaOfertaService } from 'app/entities/respuesta-oferta/respuesta-oferta.service';
import { RespuestaOferta } from 'app/shared/model/respuesta-oferta.model';

describe('Component Tests', () => {
    describe('RespuestaOferta Management Component', () => {
        let comp: RespuestaOfertaComponent;
        let fixture: ComponentFixture<RespuestaOfertaComponent>;
        let service: RespuestaOfertaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [TruequearTestModule],
                declarations: [RespuestaOfertaComponent],
                providers: []
            })
                .overrideTemplate(RespuestaOfertaComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RespuestaOfertaComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RespuestaOfertaService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new RespuestaOferta(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.respuestaOfertas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
