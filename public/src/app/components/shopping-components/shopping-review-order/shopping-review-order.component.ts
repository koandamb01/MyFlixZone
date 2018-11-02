import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { User } from '../../../models/user';
import { ShoppingService } from '../../../services/shopping.service';


@Component({
  selector: 'app-shopping-review-order',
  templateUrl: './shopping-review-order.component.html',
  styleUrls: ['./shopping-review-order.component.css']
})
export class ShoppingReviewOrderComponent implements OnInit {

  @Output() goToCartEmitter = new EventEmitter();

  orderId:any;
  total: any;
  tax: any;
  grandTotal: any;
  paymentInfo: any;
  shippingInfo: any;
  listOfItems: any[];
  

  constructor(
    private shopService: ShoppingService,
    private _router: Router
  ) { }

  ngOnInit() {
    this.getOrderTotal();
  }

  goToCart() {
    this.goToCartEmitter.emit("cart");
  }

  goToOrderDetails() {
    this.goToCartEmitter.emit("orderDetails");
  }
  goHome(){
    this.goToCartEmitter.emit("shopping")
  }

  getOrderTotal(){
    this.shopService.getOrderTotal().subscribe(res => {
      if (res['status'] == false) {
        console.log("Could not get orderTotal");
      }
      else if (res['status'] == true) {
        console.log(res);
        this.total = res.orderDetail.order.total;
        this.listOfItems = res.orderDetail.details;
        this.shippingInfo = res.orderDetail.shippingAddress;
        console.log(this.shippingInfo);
        this.paymentInfo = res.orderDetail.paymentInfo;
        this.tax = (this.total)*(0.1);
        this.grandTotal = this.tax + this.total;
        this.orderId = res.orderDetail.order.id;
      }
    });
  }

  submitOrder(){
    this.shopService.submitOrder().subscribe(res =>{
      if (res['status'] == false) {
        console.log("Could not submit order");
      }
      else if (res['status'] == true) {
        console.log(res);
        setTimeout(() => { this.goToOrderDetails(); }, 2000)
      }
    })
  }
}
