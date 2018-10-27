import { Component, OnInit } from '@angular/core';
import { MoviesService } from '../../../../services/movies.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  rowOneGenres: Array<Object>;
  rowTwoGenres: Array<Object>;
  rowThreeGenres: Array<Object>;

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
}
