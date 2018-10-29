import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Observable, Subject, asapScheduler, pipe, of, from, interval, merge, fromEvent } from 'rxjs';
import { map, filter, scan, catchError } from 'rxjs/operators';
import { User } from '../models/user';


@Injectable({
  providedIn: 'root'
})
export class UsersService {
  private baseUrl: string = 'http://localhost:8080';
  private headers = new Headers({ 'Content-Type': 'application/json' });
  private options = new RequestOptions({ headers: this.headers });
  private user = new User();

  constructor(private _http: Http) { }

  // check if user is Authenticated
  isAuthenticated() {
    let token = localStorage.getItem('access_token');
    if (token) { return true; } else { return false; }
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

  createUser(newUser: User) {
    return this._http.post(this.baseUrl + '/users/new', JSON.stringify(newUser), this.options)
      .pipe
      (
      map((response: Response) => response.json()),
      catchError(this.errorHandler)
      )
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
