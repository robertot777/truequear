/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { TruequearTestModule } from '../../../test.module';
import { ObjetosUpdateComponent } from 'app/entities/objetos/objetos-update.component';
import { ObjetosService } from 'app/entities/objetos/objetos.service';
import { Objetos } from 'app/shared/model/objetos.model';

describe('Component Tests', () => {
    describe('Objetos Management Update Component', () => {
        let comp: ObjetosUpdateComponent;
        let fixture: ComponentFixture<ObjetosUpdateComponent>;
        let service: ObjetosService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [TruequearTestModule],
                declarations: [ObjetosUpdateComponent]
            })
                .overrideTemplate(ObjetosUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ObjetosUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ObjetosService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Objetos(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.objetos = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Objetos();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.objetos = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
