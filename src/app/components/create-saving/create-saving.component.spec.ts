import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateSavingComponent } from './create-saving.component';

describe('CreateSavingComponent', () => {
  let component: CreateSavingComponent;
  let fixture: ComponentFixture<CreateSavingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateSavingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateSavingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
