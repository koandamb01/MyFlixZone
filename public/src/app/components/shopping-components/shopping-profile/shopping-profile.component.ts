import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { User } from '../../../models/user';
import { Address } from '../../../models/address';
import StateList from '../../../models/state';
import { UsersService } from '../../../services/users.service';

@Component({
  selector: 'app-shopping-profile',
  templateUrl: './shopping-profile.component.html',
  styleUrls: ['./shopping-profile.component.css']
})
export class ShoppingProfileComponent implements OnInit {

  // variables
  panelOpenState = false;
  confirm_password: string;
  messages: any;
  user = new User();
  address = new Address();
  // States: any;

  constructor(private userService: UsersService) { }

  ngOnInit() {
    // initiall variables
    this.messages = { success: "", error: "", firstName: "", lastName: "", email: "", password: "", confirm_password: "" };
    this.getUserData();
  }

  // get the user data
  getUserData() {
    this.userService.getUserProfileData().subscribe(res => {
      if (res['status'] == false) {
        this.messages.error = res['message'];
      }
      else {
        this.user = res['data'][0];
      }
    });
  }

  // update personal information
  updatePersonal() {
    this.userService.updatePersonal(this.user).subscribe(res => {
      this.messages.success = res['message'];
      this.user = res['data'][0];
      setTimeout(() => { this.ngOnInit() }, 2000);
    });
  }


  // update user password
  updatedPassword() {
    this.userService.updatePassword(this.user).subscribe(res => {
      if (res['status'] == false) {
        this.messages.error = res['message'];
        setTimeout(() => { this.ngOnInit() }, 2000);
      }
      else {
        this.messages.success = res['message'];
        this.user = res['data'][0];
        setTimeout(() => { this.ngOnInit() }, 2000);
      }
    })
  }

  // create a new address
  newAddress() {
    console.log("address Form: ", this.address);
    this.userService.createAddress(this.address).subscribe(res => {
      console.log("addres res: ", res);
      if (res['status'] == false) {
        this.formatErrorMessage(res['data']);
        this.messages.error = res['messages'];
      }
      else {
        this.messages.success = res['message'];
        setTimeout(() => { this.ngOnInit() }, 2000);
      }
    })
  }


  formatErrorMessage(errors: any[]) {
    errors.forEach(data => {
      this.messages[data.field] = data.defaultMessage;
    });
  }

}
