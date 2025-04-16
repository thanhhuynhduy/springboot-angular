import { Injectable } from "@angular/core";
import {AntiHeroActions, setAntiHeroList} from "./anti-hero.actions";
import { Actions, createEffect, ofType } from "@ngrx/effects";
import { AntiHeroService } from "../services/anti-hero.service";
import { map, mergeMap } from "rxjs";

@Injectable()
export class AntiHeroEffects {

  constructor(
    private actions$: Actions,
    private antiHeroService: AntiHeroService,
  ) {}

  getAntiHeroes$ = createEffect(() =>
    this.actions$.pipe(
      ofType(AntiHeroActions.GET_ANTI_HERO_LIST),
      mergeMap(() => {
        return this.antiHeroService.getAllAntiHero().pipe(
          map((antiHeroes) => setAntiHeroList({antiHeroes})),
        );
      })
    )
  );
}
