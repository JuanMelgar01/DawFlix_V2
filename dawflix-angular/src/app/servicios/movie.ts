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

  private readonly popularMovies$ = this.http
                                        .get<Movie[]>(
                                            `${environment.apiUrl}/movies/popular`
                                        )
                                        .pipe(
                                            shareReplay(1)
                                        );

  getPopularMovies():Observable<Movie[]> {
    return this.popularMovies$;
  }

  getFeaturedMovie():Observable<Movie> {
    return this.popularMovies$.pipe(
      map(movies => {
        const randomIndex = Math.floor(Math.random() * movies.length);
        return movies[randomIndex]
      })
    )
  }

}
