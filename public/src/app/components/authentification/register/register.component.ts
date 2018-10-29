import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { User } from '../../../models/user';
import { UsersService } from '../../../services/users.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  // variables
  confirm_password: string;
  messages: any;
  newUser = new User();

  constructor(
    private _userService: UsersService,
    private _router: Router) { }

  ngOnInit() {
    // initiall variables
    this.messages = { success: "", firstName: "", lastName: "", email: "", password: "", confirm_password: "" };
  }


  register() {
    this._userService.createUser(this.newUser).subscribe(res => {
      if (res['status'] == false) {
        this.formatErrorMessage(res['data']);
      }
      else if (res['status'] == true) {
        this.messages.success = res['message'];
        localStorage.setItem('access_token', res['data'][0]['id']);
        setTimeout(() => { this.goShopping(); }, 2000)
      }
    });
  }


  formatErrorMessage(errors: any[]) {
    errors.forEach(data => {
      this.messages[data.field] = data.defaultMessage;
    });
  }

  goShopping() {
    this._router.navigate(['/shopping']);
  }

}
