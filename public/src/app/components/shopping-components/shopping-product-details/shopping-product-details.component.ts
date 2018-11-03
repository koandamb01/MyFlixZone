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

  constructor(
    private shopService: ShoppingService,
    private _router: ActivatedRoute
  ) { }

  productId: any;

  ngOnInit() {
    this._router.params.subscribe((params: Params) => {
      this.productId = params['productId'];
      console.log("id: ", this.productId);
    });
  }

}
