/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TruequearTestModule } from '../../../test.module';
import { ObjetosDeleteDialogComponent } from 'app/entities/objetos/objetos-delete-dialog.component';
import { ObjetosService } from 'app/entities/objetos/objetos.service';

describe('Component Tests', () => {
    describe('Objetos Management Delete Component', () => {
        let comp: ObjetosDeleteDialogComponent;
        let fixture: ComponentFixture<ObjetosDeleteDialogComponent>;
        let service: ObjetosService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [TruequearTestModule],
                declarations: [ObjetosDeleteDialogComponent]
            })
                .overrideTemplate(ObjetosDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ObjetosDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ObjetosService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
