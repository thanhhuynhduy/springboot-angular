import { createReducer, on } from "@ngrx/store";
import { setAntiHeroList } from "./anti-hero.actions";
import { AntiHero } from "../models/anti-hero.interface";

export interface AntiHeroState {
    antiHeroes: ReadonlyArray<AntiHero>;
}

export const initialState: AntiHeroState = {
    antiHeroes: []
}

export const antiHeroReducer = createReducer (
    initialState,
    on(setAntiHeroList, (state, {antiHeroes}) =>
    {return {...state, antiHeroes}}),
);