import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {

  showCart: boolean;
  showReviewOrder: boolean;
  showOrderDetail: boolean;
  constructor() { }

  ngOnInit() {
    this.showCart = true;
    this.showReviewOrder = false;
    this.showOrderDetail = false;
  }


  ShowCartOrDetails(value: string) {

    if (value == "cart") {
      this.gotToCart();
    }
    else if (value == "orderReview") {
      this.goToOrderReview();
    }
    else {
      this.goToOrderDetails();
    }

  }

  gotToCart() {
    this.showCart = true;
    this.showReviewOrder = false;
    this.showOrderDetail = false;
  }

  goToOrderReview() {
    this.showCart = false;
    this.showReviewOrder = true;
    this.showOrderDetail = false;
  }

  goToOrderDetails() {
    this.showCart = false;
    this.showReviewOrder = false;
    this.showOrderDetail = true;
  }
}
