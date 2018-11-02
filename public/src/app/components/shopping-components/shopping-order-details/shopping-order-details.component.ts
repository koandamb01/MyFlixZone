import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { User } from '../../../models/user';
import { ShoppingService } from '../../../services/shopping.service';

@Component({
  selector: 'app-shopping-order-details',
  templateUrl: './shopping-order-details.component.html',
  styleUrls: ['./shopping-order-details.component.css']
})
export class ShoppingOrderDetailsComponent implements OnInit {
  @Output() goToCartOrReviewEmitter = new EventEmitter();
  constructor(
    private shopService: ShoppingService,
    private _router: Router
  ) { }

  orderId:any;
  total: any;
  tax: any;
  grandTotal: any;
  paymentInfo: any;
  shippingInfo: any;
  listOfItems: any[];

  ngOnInit() {

  }
  getOrderDetail(){
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

  goToOrderReview() {
    this.goToCartOrReviewEmitter.emit("orderReview")
  }

}
