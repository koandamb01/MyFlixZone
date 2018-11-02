import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Product } from '../../../models/product';
import { ShoppingService } from '../../../services/shopping.service';

@Component({
  selector: 'app-shopping-inventory',
  templateUrl: './shopping-inventory.component.html',
  styleUrls: ['./shopping-inventory.component.css']
})
export class ShoppingInventoryComponent implements OnInit {
  // @Output() eventEmitter = new EventEmitter();
  showOrder=false;
  showInventory=true;
  newPro = new Product();
  listOfInventory:any[];
  messages: any;

  constructor(
    private shopService: ShoppingService,
    private _router: Router
  ) { }


  ngOnInit() {
    this.messages = { success: "", firstName: "", lastName: "", email: "", password: "", confirm_password: "" };

    this.getInventory();
  }
  getInventory(){
    this.shopService.getInventory().subscribe(res => {
      if (res['status'] == false) {
        console.log("Could not get inventory");
      }
      else if (res['status'] == true) {
        console.log(res);
        this.listOfInventory = res.data;
      
      }
    });
  }
  addProduct() {
    this.shopService.addProduct(this.newPro).subscribe(res => {
      if (res['status'] == false) {
        this.formatErrorMessage(res['data']);
      }
      else if (res['status'] == true) {
        this.messages.success = res['message'];
        setTimeout(() => { this.ngOnInit(); }, 2000)
      }
    });
  }
  formatErrorMessage(errors: any[]) {
    errors.forEach(data => {
      this.messages[data.field] = data.defaultMessage;
    });
  }
}
