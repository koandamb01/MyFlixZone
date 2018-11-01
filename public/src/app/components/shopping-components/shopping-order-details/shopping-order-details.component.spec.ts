import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShoppingOrderDetailsComponent } from './shopping-order-details.component';

describe('ShoppingOrderDetailsComponent', () => {
  let component: ShoppingOrderDetailsComponent;
  let fixture: ComponentFixture<ShoppingOrderDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShoppingOrderDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShoppingOrderDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
