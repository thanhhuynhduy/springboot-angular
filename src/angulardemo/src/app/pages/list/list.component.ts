import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { AppState } from '../../state/app.state';
import { CommonModule } from '@angular/common';
import { selectAntiHeroes } from '../../state/anti-hero.selectors';
import { AntiHeroActions } from '../../state/anti-hero.actions';

@Component({
  selector: 'app-list',
  imports: [CommonModule],
  templateUrl: './list.component.html',
  styleUrl: './list.component.scss'
})
export class ListComponent implements OnInit {

  constructor(
    private store: Store<AppState>
  ) {}

  ngOnInit(): void {
    this.store.dispatch({type: AntiHeroActions.GET_ANTI_HERO_LIST});
    this.store.select(selectAntiHeroes()).subscribe((data) => {
      console.log(data);
    })
  }
}
