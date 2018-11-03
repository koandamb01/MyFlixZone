import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Observable, Subject, asapScheduler, pipe, of, from, interval, merge, fromEvent } from 'rxjs';
import { map, filter, scan, catchError } from 'rxjs/operators';
import { User } from '../models/user';
import { Address } from '../models/address';


@Injectable({
  providedIn: 'root'
})
export class UsersService {
  private baseUrl: string = 'http://localhost:8080';
  private headers = new Headers({ 'Content-Type': 'application/json' });
  private options = new RequestOptions({ headers: this.headers });
  private user = new User();
  private loggedInStatus = false;

  constructor(private _http: Http) { }

  setLoggedIn(value: boolean) {
    this.loggedInStatus = value;
  }

  // check if user is logged in
  get isLoggedIn() {
    return this.loggedInStatus;
  }

  // check if user is Authenticated
  isAuthenticated() {
    let token = localStorage.getItem('access_token');
    if (token) { return true; } else { return false; }
  }

  // get user profile data
  getUserProfileData() {
    return this._http.get(this.baseUrl + '/users/profile', this.options)
      .pipe(map((response: Response) => response.json()), catchError(this.errorHandler));
  }


  // register a user
  createUser(newUser: User) {
    return this._http.post(this.baseUrl + '/users/new', JSON.stringify(newUser), this.options)
      .pipe
      (
      map((response: Response) => response.json()),
      catchError(this.errorHandler)
      )
  }
  // add a new address
  createAddress(newAddress: Address) {
    return this._http.post(this.baseUrl + '/users/newAddress', JSON.stringify(newAddress), this.options)
      .pipe
      (
      map((response: Response) => response.json()),
      catchError(this.errorHandler)
      )
  }

  // Set address to default shipping 
  setDeafaultShippingAddress(addressId) {
    return this._http.get(this.baseUrl + '/paypal/changeAddress/' + addressId, this.options)
      .pipe
      (
      map((response: Response) => response.json()),
      catchError(this.errorHandler)
      )
  }

  // logged a user
  login(user) {
    return this._http.post(this.baseUrl + '/users/login', JSON.stringify(user), this.options)
      .pipe(map((response: Response) => response.json()), catchError(this.errorHandler))
  }


  getUsers() {
    return this._http.get(this.baseUrl + '/users', this.options)
      .pipe
      (
      map((response: Response) => response.json()),
      catchError(this.errorHandler)
      )
  }

  getCities() {
    return this._http.get(this.baseUrl + '/cities', this.options)
      .pipe
      (
      map((response: Response) => response.json()),
      catchError(this.errorHandler)
      )
  }

  getUser(id: Number) {
    return this._http.get(this.baseUrl + '/users', this.options)
      .pipe
      (
      map((response: Response) => response.json()),
      catchError(this.errorHandler)
      )
  }
  deleteUser(id: Number) {
    return this._http.delete(this.baseUrl + '/user/' + id, this.options)
      .pipe
      (
      map((response: Response) => response.json()),
      catchError(this.errorHandler)
      )
  }

  updatePersonal(user) {
    return this._http.put(this.baseUrl + '/users/updatePersonal', JSON.stringify(user), this.options)
      .pipe(map((response: Response) => response.json()), catchError(this.errorHandler));
  }
  updatePassword(user) {
    return this._http.put(this.baseUrl + '/users/updatePassword', JSON.stringify(user), this.options)
      .pipe(map((response: Response) => response.json()), catchError(this.errorHandler));
  }



  // updateUser(user: User) {
  //   return this._http.put(this.baseUrl + '/user', JSON.stringify(user), this.options)
  //     .pipe
  //     (
  //     map((response: Response) => response.json()),
  //     catchError(this.errorHandler)
  //     )
  // }

  errorHandler(error: Response) {
    return Observable.throw(error || "Server Error Service Side")
  }

  setter(user: User) {
    this.user = user;
  }

  getter() {
    return this.user;
  }
}
