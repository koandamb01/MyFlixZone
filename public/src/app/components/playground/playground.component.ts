import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user';
import { UsersService } from '../../services/users.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-playground',
  templateUrl: './playground.component.html',
  styleUrls: ['./playground.component.css']
})
export class PlaygroundComponent implements OnInit {
  private users: User[];
  cities= [];
  constructor(private _userService: UsersService) { }

  ngOnInit() {
    this._userService.getCities().subscribe((cities) => {
      console.log(cities);
      this.cities = cities;
    }, (error) => {
      console.log(error);
    })
  }

}
