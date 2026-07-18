import { Component, inject, OnInit, signal, WritableSignal } from '@angular/core';
import { Hero } from './hero/hero';
import { MovieRow } from './movieRow/movie-row';
import { MovieService } from '../../servicios/movie';
import { Movie } from '../../modelos/movie';
import { Observable } from 'rxjs/internal/Observable';
import { FavoriteService } from '../../servicios/favorite-service';

@Component({
  selector: 'app-home',
  imports: [Hero, MovieRow],
  templateUrl: './home.html',
  styleUrl: './home.scss',
})
export class Home implements OnInit {
  private movieService = inject(MovieService);
  private favoriteService = inject(FavoriteService);

  featuredMovie = signal<Movie | null>(null);
  popularMovies = signal<Movie[]>([]);
  topRatedMovies = signal<Movie[]>([]);
  nowPlayingMovies = signal<Movie[]>([]);
  upcomingMovies = signal<Movie[]>([]);

  private loadMovies( request$: Observable<Movie[]>, target: WritableSignal<Movie[]> ): void {

    request$.subscribe({
      next: movies => target.set(movies)
    });

  }

  ngOnInit() {
    this.movieService.getFeaturedMovie().subscribe({
      next: (movie) => {
        this.featuredMovie.set(movie);
      }
    });

    this.loadMovies(
      this.movieService.getPopularMovies(),
      this.popularMovies
    );

    this.loadMovies(
      this.movieService.getTopRatedMovies(),
      this.topRatedMovies
    );

    this.loadMovies(
      this.movieService.getNowPlayingMovies(),
      this.nowPlayingMovies
    );

    this.loadMovies(
      this.movieService.getUpcomingMovies(),
      this.upcomingMovies
    );

    this.favoriteService.loadFavorites();
    console.log(this.favoriteService.isFavorite(278));
  }

}
