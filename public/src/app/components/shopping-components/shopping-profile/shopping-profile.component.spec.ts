import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShoppingProfileComponent } from './shopping-profile.component';

describe('ShoppingProfileComponent', () => {
  let component: ShoppingProfileComponent;
  let fixture: ComponentFixture<ShoppingProfileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShoppingProfileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShoppingProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
