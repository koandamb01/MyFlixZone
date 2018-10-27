import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { MoviesService } from '../../../services/movies.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { DomSanitizer } from '@angular/platform-browser';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  moviesUrl: any;
  demoVideo: HTMLVideoElement;
  intervalo: any; // var to set time interval for left/right scroll of movies

  popularList: Array<Object>;
  theatersList: Array<Object>;
  topRatedList: Array<Object>;
  show: boolean;
  targetMovie: Object;

  modelMovie: Object;
  video: Object;

  constructor(
    private _moviesService: MoviesService,
    private modalService: NgbModal,
    private _sanitizer: DomSanitizer) {

    this._moviesService.getPopular().subscribe(res => {
      this.popularList = res.results;
    });

    this._moviesService.getInTheaters().subscribe(res => {
      this.theatersList = res.results;
    });
    this._moviesService.getTopRatedMovies().subscribe(res => {
      this.topRatedList = res.results;
    });


    // dummies videos urls for mouse hover play
    this.moviesUrl = [
      'https://ak7.picdn.net/shutterstock/videos/19001497/preview/stock-footage-friends-playing-basketball-at-park-overhead-shot-of-tip-off.webm',
      'https://ak7.picdn.net/shutterstock/videos/27940027/preview/stock-footage-a-dark-zombie-with-red-eyes-entered-the-room-an-abandoned-house-with-a-monster-inside-in-black-and.webm',
      'https://ak5.picdn.net/shutterstock/videos/4926755/preview/stock-footage-bristol-september-break-dancing-b-boy-jam-competition-long-sequence-september-in.webm',
      'https://ak8.picdn.net/shutterstock/videos/28860478/preview/stock-footage-young-man-at-the-movie-theatre-reacts-with-a-large-gasp-while-watching-a-film.webm',
      'https://ak1.picdn.net/shutterstock/videos/19408801/preview/stock-footage-man-put-a-case-in-the-trunk-movie-style.webm',
      'https://ak6.picdn.net/shutterstock/videos/13465346/preview/stock-footage-silhouettes-of-carioca-brazilians-playing-altinho-beach-football-at-sunset-on-ipanema-beach-in-rio.webm',
      'https://ak5.picdn.net/shutterstock/videos/1007692705/preview/stock-footage-mr-tv-headcool-man-dancing-with-a-television-as-a-head-the-tv-is-has-video-static-and-noise.webm',
      'https://ak7.picdn.net/shutterstock/videos/16281067/preview/stock-footage-stunt-girl-in-a-fiery-explosion-slow-motion-beautiful-girl-stunt-runs-across-the-field-through.webm',
      'https://ak7.picdn.net/shutterstock/videos/16508437/preview/stock-footage--s-zombie-movie-concept-bmovie-or-horror-movie-see-my-other-zombie-clips.webm',
      'https://ak3.picdn.net/shutterstock/videos/5156663/preview/stock-footage-shadow-of-a-hand-striking-with-a-knife.webm',
      'https://ak3.picdn.net/shutterstock/videos/4750643/preview/stock-footage-horror-zombie-undead-scary-halloween-fast-paced-shot-combination-of-multiple-shots-and-cg-elements.webm',
      'https://ak8.picdn.net/shutterstock/videos/27267148/preview/stock-footage-horror-scene-screaming-man-face-scary-evil-many-faces-devil-spiritual-exorcism-close-up-top-view.webm',
      'https://ak8.picdn.net/shutterstock/videos/23299498/preview/stock-footage-uryupinsk-russia-january-gameplay-game-console-sega-genesis-mortal-kombat-retro.webm',
      'https://ak8.picdn.net/shutterstock/videos/8639878/preview/stock-footage-young-man-with-muscled-body-training-martial-arts-goju-ryu-karate-do-in-silhouette.webm',
      'https://ak2.picdn.net/shutterstock/videos/21549772/preview/stock-footage-gingerbread-man-dancers-d-animation-of-funny-hot-and-sweet-cookie-boy-dancing-for-holiday-and.webm',
    ]
  }

  ngOnInit() {
    this.show = false;
  }

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


  showMovieDetail(movie) {
    this.show = false;
    this.targetMovie = movie;
    this.show = true;
  }

  closeMovieDetails() {
    this.show = false;
  }


  // Play video on mouse hover
  playVideo(event) {
    this.demoVideo = event.target.children[0].children[0].children[0]
    this.demoVideo.play();
  }

  // Stop video on mouse hover
  stopVideo() { this.demoVideo.pause(); }

  // for the slide pagination
  scrollDireita() {
    let count = 0;
    this.intervalo = setInterval(myScroll, 1);
    function myScroll() {
      if (count == 1000) {
        clearInterval(this.intervalo);
      } else {
        count += 5;
        document.getElementById('scroller').scrollLeft += 5;
      }
    }
  }

  scrollEsquerda() {
    let count = 0;
    this.intervalo = setInterval(myScroll, 1);
    function myScroll() {
      if (count == 1000) {
        clearInterval(this.intervalo);
      } else {
        count += 5;
        document.getElementById('scroller').scrollLeft -= 5;
      }
    }
  };


}
