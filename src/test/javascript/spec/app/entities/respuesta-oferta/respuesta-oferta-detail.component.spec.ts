/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TruequearTestModule } from '../../../test.module';
import { RespuestaOfertaDetailComponent } from 'app/entities/respuesta-oferta/respuesta-oferta-detail.component';
import { RespuestaOferta } from 'app/shared/model/respuesta-oferta.model';

describe('Component Tests', () => {
    describe('RespuestaOferta Management Detail Component', () => {
        let comp: RespuestaOfertaDetailComponent;
        let fixture: ComponentFixture<RespuestaOfertaDetailComponent>;
        const route = ({ data: of({ respuestaOferta: new RespuestaOferta(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [TruequearTestModule],
                declarations: [RespuestaOfertaDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RespuestaOfertaDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RespuestaOfertaDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.respuestaOferta).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
