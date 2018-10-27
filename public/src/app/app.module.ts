import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CommonModule } from '@angular/common';
import { AppComponent } from './app.component';
import { HttpModule, JsonpModule } from '@angular/http'
import { AppRoutingModule } from './app-routing.module';
import { RouterModule, Routes } from '@angular/router';
import { appRoutes } from './app-routing.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatSidenavModule } from '@angular/material/sidenav';


import { HeaderComponent } from './components/movies-components/ui/header/header.component';
import { FooterComponent } from './components/movies-components/ui/footer/footer.component';
import { MovieCardComponent } from './components/movies-components/movie-card/movie-card.component';
import { GenresComponent } from './components/movies-components/genres/genres.component';
import { MoviesComponent } from './components/movies-components/movies/movies.component';
import { HomeComponent } from './components/movies-components/home/home.component';
import { MovieComponent } from './components/movies-components/movie/movie.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    MovieCardComponent,
    GenresComponent,
    MoviesComponent,
    HomeComponent,
    MovieComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    JsonpModule,
    NgbModule,
    CommonModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatSidenavModule,
    MatExpansionModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
