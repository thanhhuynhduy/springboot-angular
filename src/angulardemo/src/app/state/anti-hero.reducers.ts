import { createReducer, on } from "@ngrx/store";
import {AntiHeroActions, getAntiHeroList, setAntiHeroList} from "./anti-hero.actions";
import { AntiHero } from "../models/anti-hero.interface";

export interface AntiHeroState {
    antiHeroes: AntiHero[];
}

export const initialState: AntiHeroState = {
    antiHeroes: []
}

export const antiHeroReducer = createReducer (
    initialState,
    on(getAntiHeroList, (state) => ({ ...state})),
    on(setAntiHeroList, (state, action) =>
      ({...state, antiHeroes: action.antiHeroes})),
);
