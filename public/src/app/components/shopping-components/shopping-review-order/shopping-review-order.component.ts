import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-shopping-review-order',
  templateUrl: './shopping-review-order.component.html',
  styleUrls: ['./shopping-review-order.component.css']
})
export class ShoppingReviewOrderComponent implements OnInit {

  @Output() goToCartEmitter = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }

  goToCart() {
    this.goToCartEmitter.emit("cart");
  }

  goToOrderDetails() {
    this.goToCartEmitter.emit("orderDetails");
  }
}
