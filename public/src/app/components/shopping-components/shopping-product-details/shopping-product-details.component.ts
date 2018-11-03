import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { ActivatedRoute, Params, Router, Route } from '@angular/router';
import { User } from '../../../models/user';
import { ShoppingService } from '../../../services/shopping.service';

@Component({
  selector: 'app-shopping-product-details',
  templateUrl: './shopping-product-details.component.html',
  styleUrls: ['./shopping-product-details.component.css']
})
export class ShoppingProductDetailsComponent implements OnInit {
  @Output() goToCartEmitter = new EventEmitter();

  constructor(
    private shopService: ShoppingService,
    private _router: ActivatedRoute,
    private _route: Router
  ) { }

  productId: any;
  productInfo:any;
  total:any = 0;
  gotIt=false;
  quantity:any=0;

  ngOnInit() {
    this._router.params.subscribe((params: Params) => {
      this.productId = params['productId'];
    });
    this.getProduct();
  }

  getProduct() {
    this.shopService.getOneProduct(this.productId).subscribe(res => {
      if (res['status'] == false) {
        console.log("Could not get orderTotal");
      }
      else if (res['status'] == true) {
        this.productInfo = res.data[0];
        this.gotIt=true;
      }
    });
  }
  addToCart(){
    this.shopService.addToCart(this.productId, this.quantity).subscribe(res =>{
      if (res['status'] == false) {
        console.log("Could not add to cart");
      }
      else if (res['status'] == true) {
        console.log(res);
        this._route.navigate(['/shopping','cart']);
      }
    })
  }

}
