import { Component, OnInit } from '@angular/core';
import { selectAntiHeroes } from '../../state/anti-hero.selectors';
import { Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { AppState } from '../../state/app.state';
import { AntiHeroActions } from '../../state/anti-hero.actions';
import { TableActions } from '../../enums/table-actions.enum';
import { AntiHero } from '../../models/anti-hero.interface';
import { CommandBarActions } from '../../enums/command-bar-actions.enum';

@Component({
  selector: 'app-list',
  imports: [],
  templateUrl: './list.component.html',
  styleUrl: './list.component.scss'
})
export class ListComponent implements OnInit {
  antiHeroes: ReadonlyArray<AntiHero> = [];
  antiHeroes$ = this.store.select(selectAntiHeroes());

  headers: {headerName: string, fieldName: keyof AntiHero}[] = [
    {headerName: 'First Name', fieldName: 'firstName'},
    {headerName: 'Last Name', fieldName: 'lastName'},
    {headerName: 'House', fieldName: 'house'},
    {headerName: 'Known As', fieldName: 'knownAs'},
  ]

  constructor(
    private router: Router,
    private store: Store<AppState>
  ) { }
  
  ngOnInit(): void {
    this.store.dispatch({type: AntiHeroActions.GET_ANTI_HERO_LIST});
    this.assignAntiHeroes();
  }

  assignAntiHeroes() {
    this.antiHeroes$.subscribe((data) => {
      this.antiHeroes = data;
      console.log(data);
    });
  }

  selectAntiHero(data: {antiHero: AntiHero, action: TableActions}) {
    this.router.navigate(['anti-hero', 'form', data.antiHero.id])
  }

  executeCommandBarAction(action: CommandBarActions) {
    switch (action) {
      case CommandBarActions.Create: {
        this.router.navigate(['anti-heroes', 'form']);
        return;
      }
      case CommandBarActions.DeleteAll: {
        return;
      }
      default: ""
    }
  }
}
