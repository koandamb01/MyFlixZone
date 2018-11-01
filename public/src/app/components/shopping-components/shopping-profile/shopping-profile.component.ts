import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { User } from '../../../models/user';
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
  newUser = new User();

  constructor() { }

  ngOnInit() {
    // initiall variables
    this.messages = { success: "", firstName: "", lastName: "", email: "", password: "", confirm_password: "" };
  }

}
