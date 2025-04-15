import { Injectable } from "@angular/core";
import { AntiHeroActions } from "./anti-hero.actions";
import { Actions, createEffect, ofType } from "@ngrx/effects";
import { AntiHeroService } from "../services/anti-hero.service";
import { catchError, map, mergeMap, EMPTY } from "rxjs";

@Injectable()
export class AntiHeroEffects {

    constructor(
        private actions$: Actions,
        private antiHeroService: AntiHeroService,
    ) {}

    getAntiHeroes$ = createEffect(() => {
        console.log("effect");
        return this.actions$.pipe(
            ofType(AntiHeroActions.GET_ANTI_HERO_LIST),
            mergeMap(() => this.antiHeroService.getAllAntiHero()
        .pipe(
            map(antiHeroes => ({type: AntiHeroActions.SET_ANTI_HERO_LIST, antiHeroes})),
            catchError(() => EMPTY)
        ))
        )
    }, {dispatch: false})
}
