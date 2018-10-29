import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes } from '@angular/router';

// Root component
import { AppComponent } from './app.component';
import { PlaygroundComponent } from './components/playground/playground.component';

// movie components
import { MoviesComponent } from './components/movies-components/movies/movies.component';
import { GenresComponent } from './components/movies-components/genres/genres.component';
import { HomeComponent } from './components/movies-components/home/home.component';


// music components
import { DiscoverComponent } from './components/music-components/discover/discover.component';
import { SoonComponent } from './components/music-components/soon/soon.component';
import { SearchComponent } from './components/music-components/search/search.component';
import { LikesComponent } from './components/music-components/likes/likes.component';
import { MusicsComponent } from './components/music-components/musics/musics.component';

//shopping components
import { ShoppingHomeComponent } from './components/shopping-components/shopping-home/shopping-home.component';

// authentification
import { AuthHomeComponent } from './components/authentification/auth-home/auth-home.component';
import { LoginComponent } from './components/authentification/login/login.component';
import { RegisterComponent } from './components/authentification/register/register.component';


export const appRoutes: Routes = [
  { path: '', pathMatch: 'full', component: PlaygroundComponent },

  {
    path: 'auth', component: AuthHomeComponent, children: [
      { path: 'signin', component: LoginComponent },
      { path: 'signup', component: RegisterComponent },
    ]
  },
  {
    path: 'movies', component: MoviesComponent, children: [
      { path: '', pathMatch: 'full', component: HomeComponent },
      { path: 'genres/:id/:name', component: GenresComponent },
    ]
  },

  {
    path: 'music', component: MusicsComponent, children: [
      { path: '', pathMatch: 'full', component: DiscoverComponent },
      { path: 'search', component: SearchComponent },
      { path: 'likes', component: LikesComponent },
      { path: '404', component: SoonComponent }
    ]
  },

  {
    path: 'shopping', component: ShoppingHomeComponent
  }
]


@NgModule({
  imports: [
    CommonModule
  ],
  declarations: []
})
export class AppRoutingModule { }
