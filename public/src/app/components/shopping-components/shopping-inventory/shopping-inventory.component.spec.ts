import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShoppingInventoryComponent } from './shopping-inventory.component';

describe('ShoppingInventoryComponent', () => {
  let component: ShoppingInventoryComponent;
  let fixture: ComponentFixture<ShoppingInventoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShoppingInventoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShoppingInventoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
