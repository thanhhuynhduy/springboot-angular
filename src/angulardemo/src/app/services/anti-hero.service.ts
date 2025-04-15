import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AntiHero} from '../models/anti-hero.interface';
import {catchError, Observable, tap, throwError} from 'rxjs';
import {environment} from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AntiHeroService {

  constructor(private http: HttpClient) { }

  getAllAntiHero(): Observable<AntiHero[]> {
    return this.http.get<AntiHero[]>(`${environment.apiURL}`).pipe(tap((data: AntiHero[])=> data),
      catchError(err => throwError(() => err)))
  }

  getAntiHeroById(id: string): Observable<AntiHero> {
    return this.http.get<AntiHero>(`${environment.apiURL}/${id}`).pipe(tap((data: AntiHero) => data),
      catchError(err => throwError(() => err)))
  }

  addAntiHero(antiHero: AntiHero): Observable<AntiHero> {
    return this.http.post<AntiHero>(`${environment.apiURL}`, antiHero).pipe(tap((data: AntiHero) => data),
      catchError(err => throwError(() => err)))
  }

  updateAntiHero(id: string, antiHero: AntiHero): Observable<AntiHero> {
    return this.http.put<AntiHero>(`${environment.apiURL}/${id}`, antiHero).pipe(
      catchError(err => throwError(() => err))
    )
  }

  deleteAntiHero(id: string): Observable<AntiHero> {
    return this.http.delete<AntiHero>(`${environment.apiURL}/${id}`).pipe(
      catchError(err => throwError(() => err))
    )
  }
}
