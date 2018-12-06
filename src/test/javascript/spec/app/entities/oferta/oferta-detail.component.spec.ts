/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TruequearTestModule } from '../../../test.module';
import { OfertaDetailComponent } from 'app/entities/oferta/oferta-detail.component';
import { Oferta } from 'app/shared/model/oferta.model';

describe('Component Tests', () => {
    describe('Oferta Management Detail Component', () => {
        let comp: OfertaDetailComponent;
        let fixture: ComponentFixture<OfertaDetailComponent>;
        const route = ({ data: of({ oferta: new Oferta(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [TruequearTestModule],
                declarations: [OfertaDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(OfertaDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(OfertaDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.oferta).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
