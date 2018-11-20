/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TruequearTestModule } from '../../../test.module';
import { RespuestaOfertaDeleteDialogComponent } from 'app/entities/respuesta-oferta/respuesta-oferta-delete-dialog.component';
import { RespuestaOfertaService } from 'app/entities/respuesta-oferta/respuesta-oferta.service';

describe('Component Tests', () => {
    describe('RespuestaOferta Management Delete Component', () => {
        let comp: RespuestaOfertaDeleteDialogComponent;
        let fixture: ComponentFixture<RespuestaOfertaDeleteDialogComponent>;
        let service: RespuestaOfertaService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [TruequearTestModule],
                declarations: [RespuestaOfertaDeleteDialogComponent]
            })
                .overrideTemplate(RespuestaOfertaDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RespuestaOfertaDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RespuestaOfertaService);
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
