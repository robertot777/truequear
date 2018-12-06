/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { TruequearTestModule } from '../../../test.module';
import { DireccionComponent } from 'app/entities/direccion/direccion.component';
import { DireccionService } from 'app/entities/direccion/direccion.service';
import { Direccion } from 'app/shared/model/direccion.model';

describe('Component Tests', () => {
    describe('Direccion Management Component', () => {
        let comp: DireccionComponent;
        let fixture: ComponentFixture<DireccionComponent>;
        let service: DireccionService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [TruequearTestModule],
                declarations: [DireccionComponent],
                providers: []
            })
                .overrideTemplate(DireccionComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DireccionComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DireccionService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Direccion(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.direccions[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
