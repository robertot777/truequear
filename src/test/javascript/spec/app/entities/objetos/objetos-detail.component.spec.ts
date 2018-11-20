/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TruequearTestModule } from '../../../test.module';
import { ObjetosDetailComponent } from 'app/entities/objetos/objetos-detail.component';
import { Objetos } from 'app/shared/model/objetos.model';

describe('Component Tests', () => {
    describe('Objetos Management Detail Component', () => {
        let comp: ObjetosDetailComponent;
        let fixture: ComponentFixture<ObjetosDetailComponent>;
        const route = ({ data: of({ objetos: new Objetos(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [TruequearTestModule],
                declarations: [ObjetosDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ObjetosDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ObjetosDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.objetos).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
