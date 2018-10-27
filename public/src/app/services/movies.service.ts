import { Injectable } from '@angular/core';
import { Jsonp, URLSearchParams } from '@angular/http';
import 'rxjs/add/operator/map';

@Injectable({
  providedIn: 'root'
})
export class MoviesService {
  apikey: string;

  constructor(private _jsonp: Jsonp) {
    this.apikey = 'fed69657ba4cc6e1078d2a6a95f51c8c';
    console.log("Movies service is ready")
  }

  // get all the movies genres
  getGenres() {
    var search = new URLSearchParams();
    search.set('language', 'en-US');
    search.set('api_key', this.apikey);
    return this._jsonp.get('https://api.themoviedb.org/3/genre/movie/list?callback=JSONP_CALLBACK', { search })
      .map(res => {
        return res.json();
      })
  }

  // get movies by genres using id
  getMoviesByGenre(id: string) {
    var search = new URLSearchParams();
    search.set('api_key', this.apikey);
    return this._jsonp.get('https://api.themoviedb.org/3/genre/' + id + '/movies?callback=JSONP_CALLBACK', { search })
      .map(res => {
        return res.json();
      })
  }

  // get most popular movies
  getPopular() {
    var search = new URLSearchParams();
    search.set('sort_by', 'popularity.desc');
    search.set('api_key', this.apikey);
    return this._jsonp.get('https://api.themoviedb.org/3/discover/movie?callback=JSONP_CALLBACK', { search })
      .map(res => {
        return res.json();
      })
  }


  // get movies in theaters
  getInTheaters() {
    var search = new URLSearchParams();
    search.set('primary_release_date.gt', '2015-10-20');
    search.set('primary_release_date.lte', '2015-12-20');
    search.set('sort_by', 'popularity.desc');
    search.set('api_key', this.apikey);
    return this._jsonp.get('https://api.themoviedb.org/3/discover/movie?callback=JSONP_CALLBACK', { search })
      .map(res => {
        return res.json();
      })
  }

  // Get the top rated movies
  getTopRatedMovies() {
    var search = new URLSearchParams();
    search.set('api_key', this.apikey);
    return this._jsonp.get('https://api.themoviedb.org/3/movie/top_rated?callback=JSONP_CALLBACK', { search })
      .map(res => {
        return res.json();
      })
  }

  // Get the movie trailer
  getMovieVideos(id: string) {
    var search = new URLSearchParams();
    search.set('api_key', this.apikey);
    return this._jsonp.get('https://api.themoviedb.org/3/movie/' + id + '/videos?callback=JSONP_CALLBACK', { search })
      .map(res => {
        return res.json();
      })
  }

  // search for movies
  searchMovies(searchStr: string) {
    var search = new URLSearchParams();
    search.set('sort_by', 'popularity.desc');
    search.set('query', searchStr);
    search.set('api_key', this.apikey);
    return this._jsonp.get('https://api.themoviedb.org/3/search/movie?callback=JSONP_CALLBACK', { search })
      .map(res => {
        return res.json();
      })
  }
}
