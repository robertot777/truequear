/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { TruequearTestModule } from '../../../test.module';
import { ObjetosComponent } from 'app/entities/objetos/objetos.component';
import { ObjetosService } from 'app/entities/objetos/objetos.service';
import { Objetos } from 'app/shared/model/objetos.model';

describe('Component Tests', () => {
    describe('Objetos Management Component', () => {
        let comp: ObjetosComponent;
        let fixture: ComponentFixture<ObjetosComponent>;
        let service: ObjetosService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [TruequearTestModule],
                declarations: [ObjetosComponent],
                providers: []
            })
                .overrideTemplate(ObjetosComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ObjetosComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ObjetosService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Objetos(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.objetos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
