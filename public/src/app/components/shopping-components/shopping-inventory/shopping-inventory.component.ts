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

  // related to inventory
  showOrder=true;
  showInventory=false;
  newPro = new Product();
  listOfInventory:any[];
  messages: any;
  itemId: any;
  showEdit: boolean=false;
  showAddItem: boolean = false;
  //

  //related to orders
  unshipped:any=0;
  shipped:any=0;
  completed:any=0;

  unshippedList:any[] = [];
  shippedList:any[] = [];
  completedList:any[] = [];
  //

  constructor(
    private shopService: ShoppingService,
    private _router: Router
  ) { }


  ngOnInit() {
    this.messages = { success: "", firstName: "", lastName: "", email: "", password: "", confirm_password: "" };
    this.reset();
    this.getInventory();
    this.getOrders();
  }

  //   Inventory     //
  getInventory(){
    if(this.showInventory){
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
  }

  showingAddItem(){
    this.reset();
    if(this.showEdit){
      this.showEdit = false;
      this.showAddItem = true;
    }
    else{
      this.showAddItem = !this.showAddItem;
    }
  }

  showOrders(){
    this.showInventory=false;
    this.showOrder =true;
    this.ngOnInit();
  }
  showInventories(){
    this.showOrder =false;
    this.showInventory=true;
    this.ngOnInit();
  }

  showingEdit(itemId, name, price, stock, img, description){
    if(this.showAddItem){
      this.showAddItem = false;
      this.showEdit = true;
    }
    this.newPro.id = itemId;
    this.newPro.name = name;
    this.newPro.price = price;
    this.newPro.stock = stock;
    this.newPro.img = img;
    this.newPro.description = description;

    if(this.itemId == itemId && this.showEdit){
      this.showEdit= !this.showEdit;
      this.itemId = itemId;
      this.reset();
    }
    else{
      this.showEdit= true;
      this.itemId = itemId;
    }

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

  editProduct(){
    this.shopService.editProduct(this.newPro).subscribe(res => {
      if (res['status'] == false) {
        this.formatErrorMessage(res['data']);
      }
      else if (res['status'] == true) {
        console.log(res);
        this.messages.success = res['message'];
        setTimeout(() => { this.ngOnInit(); }, 2000)
      }
    });
  }

  deleteItem(itemId){
    this.shopService.deleteItem(itemId).subscribe(res => {
      if (res['status'] == false) {
        this.formatErrorMessage(res['data']);
      }
      else if (res['status'] == true) {
        this.messages.success = res['message'];
        setTimeout(() => { this.ngOnInit(); }, 2000)
      }
    });
  }

  reset(){
    this.itemId=null;
    this.newPro.id = "";
    this.newPro.name = "";
    this.newPro.price = "";
    this.newPro.stock = "";
    this.newPro.img = "";
    this.unshipped=0;
    this.shipped=0;
    this.completed=0;

    this.unshippedList = [];
    this.shippedList= [];
    this.completedList = [];
  }

  /////////////Orders//////////

  getOrders(){
    if(this.showOrder){
      this.shopService.getOrders().subscribe(res => {
        if (res['status'] == false) {
          console.log("Could not get inventory");
        }
        else if (res['status'] == true) {
          for(var i=0; i<res.data.length;i++){
            if(res.data[i].status=="unshipped"){
              this.unshippedList.push(res.data[i]);
              this.unshipped++;
            }
            else if(res.data[i].status=="shipped"){
              this.shippedList.push(res.data[i]);
              this.shipped++;
            }
            else if(res.data[i].status=="completed"){
              this.completedList.push(res.data[i]);
              this.completed++;
            }          
          }
        }
      });
    }
  }

  updateStatus(orderId, status){
    this.shopService.updateStatus(orderId, status).subscribe(res => {
      if (res['status'] == false) {
        this.formatErrorMessage(res['data']);
      }
      else if (res['status'] == true) {
        this.messages.success = res['message'];
        setTimeout(() => { this.ngOnInit(); }, 2000)
      }
    });
  }   
   /////end of order///
  formatErrorMessage(errors: any[]) {
    errors.forEach(data => {
      this.messages[data.field] = data.defaultMessage;
    });
  }
}
