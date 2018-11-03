import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { User } from '../../../models/user';
import { ShoppingService } from '../../../services/shopping.service';
import { FilterPipe } from 'ngx-filter-pipe';

@Component({
  selector: 'app-shopping-home',
  templateUrl: './shopping-home.component.html',
  styleUrls: ['./shopping-home.component.css']
})
export class ShoppingHomeComponent implements OnInit {

  constructor(
    private shopService: ShoppingService,
    private _router: Router,
    private filterPipe: FilterPipe,
  ) { }

  productList:any[];
  filter: any;
  gotIt=false;

  ngOnInit() {
    this.filter = { name: '' };
    this.getProducts();
  }
  getProducts(){
    this.shopService.getInventory().subscribe(res => {
      if (res['status'] == false) {
        console.log("Could not get orderTotal");
      }
      else if (res['status'] == true) {
        this.productList=res.data;
        console.log(this.productList);
        this.gotIt = true;
      }
    });
  }
}
