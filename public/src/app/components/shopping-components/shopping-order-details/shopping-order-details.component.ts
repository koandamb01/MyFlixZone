import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-shopping-order-details',
  templateUrl: './shopping-order-details.component.html',
  styleUrls: ['./shopping-order-details.component.css']
})
export class ShoppingOrderDetailsComponent implements OnInit {
  @Output() goToCartOrReviewEmitter = new EventEmitter();
  constructor() { }

  ngOnInit() {

  }


  goToOrderReview() {
    this.goToCartOrReviewEmitter.emit("orderReview")
  }

}
