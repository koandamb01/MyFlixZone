import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CommonModule } from '@angular/common';
import { AppComponent } from './app.component';
import { HttpModule, JsonpModule } from '@angular/http'
import { HttpClientModule, HttpClientJsonpModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { RouterModule, Routes } from '@angular/router';
import { appRoutes } from './app-routing.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatSidenavModule } from '@angular/material/sidenav';
import { AuthGuard } from './guards/auth.guard';
import { FilterPipeModule } from 'ngx-filter-pipe';



import { HeaderComponent } from './components/movies-components/ui/header/header.component';
import { FooterComponent } from './components/movies-components/ui/footer/footer.component';
import { MovieCardComponent } from './components/movies-components/movie-card/movie-card.component';
import { GenresComponent } from './components/movies-components/genres/genres.component';
import { MoviesComponent } from './components/movies-components/movies/movies.component';
import { HomeComponent } from './components/movies-components/home/home.component';
import { MovieComponent } from './components/movies-components/movie/movie.component';


// music components
import { DiscoverComponent } from './components/music-components/discover/discover.component';
import { SoonComponent } from './components/music-components/soon/soon.component';
import { SearchComponent } from './components/music-components/search/search.component';
import { LikesComponent } from './components/music-components/likes/likes.component';

import { MusicHomeComponent } from './components/music-components/music-home/music-home.component'
import { MenuComponent } from './components/music-components/menu/menu.component';
import { MainComponent } from './components/music-components/main/main.component';
import { CueComponent } from './components/music-components/cue/cue.component';
import { FeedComponent } from './components/music-components/feed/feed.component';
import { DzService } from './services/Dz.service';

import { EbusService } from './services/Ebus.service';
import { LikesService } from './services/Likes.service';
import { MusicsComponent } from './components/music-components/musics/musics.component';

// shopping components
import { ShoppingHomeComponent } from './components/shopping-components/shopping-home/shopping-home.component';
import { ShoppingProfileComponent } from './components/shopping-components/shopping-profile/shopping-profile.component';
import { ShoppingInventoryComponent } from './components/shopping-components/shopping-inventory/shopping-inventory.component';
import { ShoppingCartComponent } from './components/shopping-components/shopping-cart/shopping-cart.component';
import { ShoppingReviewOrderComponent } from './components/shopping-components/shopping-review-order/shopping-review-order.component';
import { ShoppingOrderDetailsComponent } from './components/shopping-components/shopping-order-details/shopping-order-details.component';
import { ShoppingOrderListComponent } from './components/shopping-components/shopping-order-list/shopping-order-list.component';
import { ShoppingProductDetailsComponent } from './components/shopping-components/shopping-product-details/shopping-product-details.component';

// authentification
import { RegisterComponent } from './components/authentification/register/register.component';
import { LoginComponent } from './components/authentification/login/login.component';
import { AuthHomeComponent } from './components/authentification/auth-home/auth-home.component';








@NgModule({
  declarations: [
    AppComponent,
    // movies
    HeaderComponent,
    FooterComponent,
    MovieCardComponent,
    GenresComponent,
    MoviesComponent,
    HomeComponent,
    MovieComponent,

    // music
    DiscoverComponent,
    SoonComponent,
    SearchComponent,
    LikesComponent,
    MusicHomeComponent,
    MenuComponent,
    MainComponent,
    CueComponent,
    FeedComponent,
    MusicsComponent,
    ShoppingHomeComponent,

    // auth
    AuthHomeComponent,
    RegisterComponent,
    LoginComponent,
    ShoppingProfileComponent,
    ShoppingInventoryComponent,
    ShoppingCartComponent,
    ShoppingReviewOrderComponent,
    ShoppingOrderDetailsComponent,
    ShoppingOrderListComponent,
    ShoppingProductDetailsComponent

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
    HttpClientModule,
    HttpClientJsonpModule,
    RouterModule.forRoot(appRoutes),
    FilterPipeModule
  ],
  providers: [DzService, EbusService, LikesService, AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
