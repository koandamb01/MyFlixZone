import { Injectable } from "@angular/core";
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Observable, Subject, asapScheduler, pipe, of, from, interval, merge, fromEvent } from 'rxjs';
import { map, filter, scan, catchError } from 'rxjs/operators';
import Genres from "../static/GenresMap";
import { DzService } from "./Dz.service";

@Injectable({
  providedIn: 'root'
})
export class LikesService {
  private baseUrl: string = 'http://localhost:8080';
  private headers = new Headers({ 'Content-Type': 'application/json' });
  private options = new RequestOptions({ headers: this.headers });

  // likes: any = JSON.parse(localStorage.getItem('likes')) || [];
  likes: any[];
  feed: any = [];

  constructor(
    private _http: Http,
    private _dz: DzService
  ) {
    this.likes = [];

    // get all the song
    Genres.forEach(genre => {
      this._dz.playlist(genre.playlistId).subscribe(data => {
        this.feed.push(data);
      })
    });
  }

  likeSong(song) {
    this.likes.push(song);
    this._http.get(this.baseUrl + '/music/likes/' + song.id, this.options)
      .pipe(map((response: Response) => response.json()), catchError(this.errorHandler))
      .subscribe(res => {
        console.log("Java response: ", res);
      })
  }
  unlikeSong(song) {
    this.likes = this.likes.filter(s => s.id != song.id);
    localStorage.setItem('likes', JSON.stringify(this.likes));
  }


  // handle all errors
  errorHandler(error: Response) {
    return Observable.throw(error || "Server Error Service Side")
  }
}

