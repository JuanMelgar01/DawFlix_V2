import { Component, inject } from '@angular/core';
import { Hero } from './hero/hero';
import { MovieRow } from './movieRow/movie-row';
import { MovieService } from '../../servicios/movie';
import { toSignal } from '@angular/core/rxjs-interop';

@Component({
  selector: 'app-home',
  imports: [Hero, MovieRow],
  templateUrl: './home.html',
  styleUrl: './home.scss',
})
export class Home {
  private movieService = inject(MovieService);

  popularMovies = toSignal(this.movieService.getPopularMovies(), { initialValue: [] });

  featuredMovie = toSignal(this.movieService.getFeaturedMovie(),{initialValue:null})

}
