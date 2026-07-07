import { Component, input } from '@angular/core';
import { MovieCard } from '../movieCard/movie-card';
import { Movie } from '../../../modelos/movie';

@Component({
  selector: 'app-movie-row',
  imports: [MovieCard],
  templateUrl: './movie-row.html',
  styleUrl: './movie-row.scss',
})
export class MovieRow {
  title = input.required<string>();

  movies = input.required<Movie[]>();
}
