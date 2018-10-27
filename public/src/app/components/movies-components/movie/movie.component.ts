import { Component, OnInit, Input, OnChanges, SimpleChanges, Output, EventEmitter } from '@angular/core';
import { trigger, state, style, animate, transition } from '@angular/animations';
import { MoviesService } from '../../../services/movies.service';
import { DomSanitizer } from '@angular/platform-browser';


@Component({
  selector: 'app-movie',
  templateUrl: './movie.component.html',
  styleUrls: ['./movie.component.css'],
  animations: [
    trigger('changeState', [

      state('state1', style({
        backgroundColor: 'red',
        transform: 'scale(1.5)'
      })),

      transition('*=>showState', animate('300ms')),
      transition('*=>closeState', animate('2000ms'))
    ])
  ]
})
export class MovieComponent implements OnInit, OnChanges {
  @Input()
  targetMovie: Object;

  currentState = 'showState';

  @Output() closeMovieDetailsEmitter = new EventEmitter();

  movie: Object;
  video: Object;
  constructor(
    private _moviesService: MoviesService,
    private _sanitizer: DomSanitizer) { }

  ngOnInit() { }

  ngOnChanges(change: SimpleChanges) {
    this.movie = change.targetMovie.currentValue;

    // get the video from the Youtube using ID
    this._moviesService.getMovieVideos(this.movie['id']).subscribe(res => {
      if (res.results && res.results.length) {
        this.video = res.results[0];
        this.video['url'] = this._sanitizer.bypassSecurityTrustResourceUrl('https://www.youtube.com/embed/' + this.video['key']);
      }
    });
  }

  closeMovieDetails() {
    this.closeMovieDetailsEmitter.emit();
  }
}
