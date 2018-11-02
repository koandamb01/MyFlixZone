import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { User } from '../../../models/user';
import { ShoppingService } from '../../../services/shopping.service';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {

  showCart: boolean;
  showReviewOrder: boolean;
  showOrderDetail: boolean;
  constructor(
    private shopService: ShoppingService,
    private _router: Router
  ) { }

  total: any;
  listOfItems: any[];

  ngOnInit() {
    this.showCart = true;
    this.showReviewOrder = false;
    this.showOrderDetail = false;
    this.getCart();
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


  getCart(){
    this.shopService.getCart().subscribe(res => {
      if (res['status'] == false) {
        console.log("Could not get cart");
      }
      else if (res['status'] == true) {
        console.log(res);
        this.total = res.orderDetail.order.total;
        console.log(this.total);
        this.listOfItems = res.orderDetail.details;
      }
    });
  }
}
