/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TruequearTestModule } from '../../../test.module';
import { OfertaDeleteDialogComponent } from 'app/entities/oferta/oferta-delete-dialog.component';
import { OfertaService } from 'app/entities/oferta/oferta.service';

describe('Component Tests', () => {
    describe('Oferta Management Delete Component', () => {
        let comp: OfertaDeleteDialogComponent;
        let fixture: ComponentFixture<OfertaDeleteDialogComponent>;
        let service: OfertaService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [TruequearTestModule],
                declarations: [OfertaDeleteDialogComponent]
            })
                .overrideTemplate(OfertaDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(OfertaDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OfertaService);
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
