import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BackendUnreachableComponent } from './backend-unreachable.component';

describe('BackendUnreachableComponent', () => {
  let component: BackendUnreachableComponent;
  let fixture: ComponentFixture<BackendUnreachableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BackendUnreachableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BackendUnreachableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
