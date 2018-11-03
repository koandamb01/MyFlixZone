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

//shopping related
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

  getOrderDetail(orderId){
    return this._http.get(this.baseUrl + '/paypal/getOneOrder/'+orderId, this.options)
      .pipe
      (
      map((response: Response) => response.json()),
      catchError(this.errorHandler)
      )
  }

  getAllOrders(){
    return this._http.get(this.baseUrl + '/paypal/getAllOrders', this.options)
      .pipe
      (
      map((response: Response) => response.json()),
      catchError(this.errorHandler)
      )
  }

//end of shopping


  //inventory
  getInventory(){
    return this._http.get(this.baseUrl + '/admin/product', this.options)
      .pipe
      (
      map((response: Response) => response.json()),
      catchError(this.errorHandler)
      )
  }

  addProduct(newProduct){
    return this._http.post(this.baseUrl + '/admin/product', JSON.stringify(newProduct), this.options)
      .pipe
      (
      map((response: Response) => response.json()),
      catchError(this.errorHandler)
      )
  }

  deleteItem(itemId){
    return this._http.delete(this.baseUrl + '/admin/product/'+itemId+'/delete', this.options)
      .pipe
      (
      map((response: Response) => response.json()),
      catchError(this.errorHandler)
      )
  }

  editProduct(newProduct){
    return this._http.put(this.baseUrl + '/admin/product', JSON.stringify(newProduct), this.options)
      .pipe
      (
      map((response: Response) => response.json()),
      catchError(this.errorHandler)
      )
  }
//end of inventory related


/// start of orders//

getOrders() {
  return this._http.get(this.baseUrl + '/admin/orders', this.options)
    .pipe
    (
    map((response: Response) => response.json()),
    catchError(this.errorHandler)
    )
}

updateStatus(orderId, status) {
  return this._http.get(this.baseUrl + '/admin//orders/'+ orderId +'/status/' + status, this.options)
    .pipe
    (
    map((response: Response) => response.json()),
    catchError(this.errorHandler)
    )
}

//end of orders//
  errorHandler(error: Response) {
    return Observable.throw(error || "Server Error Service Side")
  }
}
