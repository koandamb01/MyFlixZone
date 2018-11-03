import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './guards/auth.guard';

// Root component
import { AppComponent } from './app.component';

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
import { ShoppingProfileComponent } from './components/shopping-components/shopping-profile/shopping-profile.component';
import { ShoppingInventoryComponent } from './components/shopping-components/shopping-inventory/shopping-inventory.component';
import { ShoppingCartComponent } from './components/shopping-components/shopping-cart/shopping-cart.component';
import { ShoppingOrderListComponent } from './components/shopping-components/shopping-order-list/shopping-order-list.component';
import { ShoppingOrderDetailsComponent } from './components/shopping-components/shopping-order-details/shopping-order-details.component';
import { ShoppingProductDetailsComponent } from './components/shopping-components/shopping-product-details/shopping-product-details.component';

// authentification
import { AuthHomeComponent } from './components/authentification/auth-home/auth-home.component';
import { LoginComponent } from './components/authentification/login/login.component';
import { RegisterComponent } from './components/authentification/register/register.component';


export const appRoutes: Routes = [

  // Shopping Routes
  { path: 'shopping', component: ShoppingHomeComponent },
  { path: 'shopping/profile', component: ShoppingProfileComponent },
  { path: 'shopping/inventory', component: ShoppingInventoryComponent },
  { path: 'shopping/cart', component: ShoppingCartComponent },
  { path: 'shopping/orders', component: ShoppingOrderListComponent },
  { path: 'shopping/order/:orderId', component: ShoppingOrderDetailsComponent },
  { path: 'shopping/product/:productId', component: ShoppingProductDetailsComponent },

  // Auth Routes
  {
    path: 'auth', component: AuthHomeComponent, children: [
      { path: 'signin', component: LoginComponent },
      { path: 'signup', component: RegisterComponent },
    ]
  },

  // Movie Routes
  {
    path: 'movies', component: MoviesComponent, children: [
      { path: '', pathMatch: 'full', component: HomeComponent },
      { path: 'genres/:id/:name', component: GenresComponent },
    ]
  },

  // Music Routes
  {
    path: 'music', component: MusicsComponent, children: [
      { path: '', pathMatch: 'full', component: DiscoverComponent },
      { path: 'search', component: SearchComponent },
      { path: 'likes', component: LikesComponent },
      { path: '404', component: SoonComponent }
    ]
  },
]


@NgModule({
  imports: [
    CommonModule
  ],
  providers: [AuthGuard],
  declarations: []
})
export class AppRoutingModule { }
