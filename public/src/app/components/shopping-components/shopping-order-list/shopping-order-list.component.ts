import { Component, OnInit } from '@angular/core';
import { ShoppingService } from '../../../services/shopping.service';
import { ActivatedRoute, Params, Router } from '@angular/router';

@Component({
  selector: 'app-shopping-order-list',
  templateUrl: './shopping-order-list.component.html',
  styleUrls: ['./shopping-order-list.component.css']
})
export class ShoppingOrderListComponent implements OnInit {

  constructor(
    private shopService: ShoppingService,
    private _router: Router
  ) { }

  listOfOrders : any[];

  ngOnInit() {
    this.getAllOrders();
  }

  getAllOrders(){
    this.shopService.getAllOrders().subscribe(res => {
      if (res['status'] == false) {
        console.log("Could not get orderTotal");
      }
      else if (res['status'] == true) {
        console.log(res);
        this.listOfOrders = res.data;
      }
    });
  }
}
