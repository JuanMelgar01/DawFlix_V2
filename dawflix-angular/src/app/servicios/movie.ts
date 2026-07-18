import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../environment/environment';
import { Observable, map, shareReplay } from 'rxjs';
import { Movie } from '../modelos/movie';

@Injectable({
  providedIn: 'root',
})
export class MovieService {
  private http = inject(HttpClient);
  private readonly popularMovies$ = this.getMovies('popular');
  private readonly topRatedMovies$ = this.getMovies('top-rated');
  private readonly nowPlayingMovies$ = this.getMovies('now-playing');
  private readonly upcomingMovies$ = this.getMovies('upcoming');

  private getMovies(endpoint: string): Observable<Movie[]> {

    return this.http
      .get<Movie[]>(`${environment.apiUrl}/movies/${endpoint}`)
      .pipe(
        shareReplay(1)
      );

  }

  getPopularMovies() {
    return this.popularMovies$;
  }

  getTopRatedMovies() {
    return this.topRatedMovies$;
  }

  getNowPlayingMovies() {
    return this.nowPlayingMovies$;
  }

  getUpcomingMovies() {
    return this.upcomingMovies$;
  }

  getFeaturedMovie():Observable<Movie> {
    return this.http.get<Movie>(`${environment.apiUrl}/movies/featured`);
  }

}
