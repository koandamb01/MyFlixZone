import { Component, OnInit, Input } from '@angular/core';
import { MoviesService } from '../../../services/movies.service';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-movie-card',
  templateUrl: './movie-card.component.html',
  styleUrls: ['./movie-card.component.css']
})
export class MovieCardComponent implements OnInit {
  @Input()
  movieUrl: string;

  constructor(
    private _moviesService: MoviesService,
    private _router: ActivatedRoute,
    private _sanitizer: DomSanitizer) { }

  ngOnInit() { }

}
