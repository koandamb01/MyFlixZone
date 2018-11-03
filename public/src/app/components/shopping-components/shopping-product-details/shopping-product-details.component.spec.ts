import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShoppingProductDetailsComponent } from './shopping-product-details.component';

describe('ShoppingProductDetailsComponent', () => {
  let component: ShoppingProductDetailsComponent;
  let fixture: ComponentFixture<ShoppingProductDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShoppingProductDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShoppingProductDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
