import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShoppingReviewOrderComponent } from './shopping-review-order.component';

describe('ShoppingReviewOrderComponent', () => {
  let component: ShoppingReviewOrderComponent;
  let fixture: ComponentFixture<ShoppingReviewOrderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShoppingReviewOrderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShoppingReviewOrderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
