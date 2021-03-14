import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckingCardComponent } from './checking-card.component';

describe('CheckingCardComponent', () => {
  let component: CheckingCardComponent;
  let fixture: ComponentFixture<CheckingCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CheckingCardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CheckingCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
