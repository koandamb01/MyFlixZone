import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Observable, Subject, asapScheduler, pipe, of, from, interval, merge, fromEvent } from 'rxjs';
import { map, filter, scan, catchError } from 'rxjs/operators';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class ShoppingService {
  private baseUrl: string = 'http://localhost:8080';
  private headers = new Headers({ 'Content-Type': 'application/json' });
  private options = new RequestOptions({ headers: this.headers });

  constructor(private _http: Http) { }


  getCart() {
    return this._http.get(this.baseUrl + '/paypal/getCart', this.options)
      .pipe
      (
      map((response: Response) => response.json()),
      catchError(this.errorHandler)
      )
  }

  getOrderTotal() {
    return this._http.get(this.baseUrl + '/paypal/getCheckOut', this.options)
      .pipe
      (
      map((response: Response) => response.json()),
      catchError(this.errorHandler)
      )
  }

  submitOrder(){
    return this._http.get(this.baseUrl + '/paypal/submitOrder', this.options)
      .pipe
      (
      map((response: Response) => response.json()),
      catchError(this.errorHandler)
      )
  }

  errorHandler(error: Response) {
    return Observable.throw(error || "Server Error Service Side")
  }
}
