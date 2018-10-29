import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { UsersService } from '../services/users.service';

@Injectable()
export class AuthGuard implements CanActivate {

    constructor(
        private _router: Router,
        public _userService: UsersService
    ) { }

    canActivate(): boolean {
        if (!this._userService.isAuthenticated()) {
            this._router.navigate(['/signin']);
            return false;
        }
        return true;
    }
}