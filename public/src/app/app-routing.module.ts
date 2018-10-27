import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes } from '@angular/router';

import { MoviesComponent } from './components/movies-components/movies/movies.component';
import { GenresComponent } from './components/movies-components/genres/genres.component';
import { HomeComponent } from './components/movies-components/home/home.component';
import { AppComponent } from './app.component';
import { PlaygroundComponent } from './components/playground/playground.component';

export const appRoutes: Routes = [
  { path: '', pathMatch: 'full', component: PlaygroundComponent },
  {
    path: 'movies', component: MoviesComponent, children: [
      { path: '', pathMatch: 'full', component: HomeComponent },
      { path: 'genres/:id/:name', component: GenresComponent },
    ]
  }
]


@NgModule({
  imports: [
    CommonModule
  ],
  declarations: []
})
export class AppRoutingModule { }
