import { Component, OnInit } from '@angular/core';
import { MoviesService } from '../../../services/movies.service';
import { ActivatedRoute } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { DomSanitizer } from '@angular/platform-browser';


@Component({
  selector: 'app-genres',
  templateUrl: './genres.component.html',
  styleUrls: ['./genres.component.css']
})
export class GenresComponent implements OnInit {
  title: string;
  movies: Object;

  modelMovie: Object;
  video: Object;

  constructor(
    private _moviesService: MoviesService,
    private _router: ActivatedRoute,
    private modalService: NgbModal,
    private _sanitizer: DomSanitizer) { }

  ngOnInit() {
    this._router.params.subscribe((params) => {
      const id = params['id'];
      this.title = params['name'];
      this._moviesService.getMoviesByGenre(id).subscribe(res => {
        this.movies = res.results;
      })

    })
  }


  // get the target movie and open the model with it
  openLg(content, movie) {
    this.modelMovie = movie;
    // get the video from the Youtube using ID
    this._moviesService.getMovieVideos(this.modelMovie['id']).subscribe(res => {
      if (res.results && res.results.length) {
        this.video = res.results[0];
        this.video['url'] = this._sanitizer.bypassSecurityTrustResourceUrl('https://www.youtube.com/embed/' + this.video['key']);
      }
    });

    // show the modal now
    this.modalService.open(content, { windowClass: 'dark-modal', size: 'lg' });
  }

}
