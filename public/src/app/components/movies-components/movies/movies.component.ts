import { Component, OnInit } from '@angular/core';
import { MoviesService } from '../../../services/movies.service';

@Component({
  selector: 'app-movies',
  templateUrl: './movies.component.html',
  styleUrls: ['./movies.component.css']
})
export class MoviesComponent implements OnInit {

  // genres title by row
  rowOneGenres: Array<Object>;
  rowTwoGenres: Array<Object>;
  rowThreeGenres: Array<Object>;

  // variable for search
  searchRes: Array<Object>;
  searchStr: string;

  // get all the genres when the component load
  constructor(private _moviesService: MoviesService) {
    let obs = this._moviesService.getGenres();
    obs.subscribe(res => {
      this.rowOneGenres = res.genres.splice(0, 7);
      this.rowTwoGenres = res.genres.splice(0, 7);
      this.rowThreeGenres = res.genres.splice(0, 5);
    })
  }

  ngOnInit() {
  }

  // method to search movies
  searchMovies() {
    this._moviesService.searchMovies(this.searchStr).subscribe(res => {
      this.searchRes = res.results;
    })
  }
}
