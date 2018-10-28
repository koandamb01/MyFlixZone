import { Component, OnInit, HostBinding } from '@angular/core';

@Component({
  selector: '[appMenu]',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {
  @HostBinding('class') menuClass: string = 'menu';


  constructor() { }

  ngOnInit() {
  }

}
