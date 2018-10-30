import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { UsersService } from '../../../services/users.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  logUser: any;
  messages: any;
  rememberMe: boolean;
  constructor(
    private _userService: UsersService,
    private _router: Router
  ) { }

  ngOnInit() {
    this.logUser = { email: "", password: "" };
    this.messages = { success: "", login: "" };
  }

  login() {
    this._userService.login(this.logUser)
      .subscribe(response => {
        if (response['status'] == false) {
          this.messages.login = response['message'];
        }
        else {
          // set token
          this.messages.success = response['message'];
          localStorage.setItem('access_token', response['userId']);
          setTimeout(() => { this.goShopping(); }, 1000);
          this._userService.setLoggedIn(true);
        }
      });
  }

  // go to shopping
  goShopping() {
    this._router.navigate(['/shopping']);
  }

}
