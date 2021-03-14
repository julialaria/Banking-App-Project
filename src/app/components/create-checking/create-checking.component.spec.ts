import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateCheckingComponent } from './create-checking.component';

describe('CreateCheckingComponent', () => {
  let component: CreateCheckingComponent;
  let fixture: ComponentFixture<CreateCheckingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateCheckingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateCheckingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
