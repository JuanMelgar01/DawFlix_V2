import { Component, inject, input, signal } from '@angular/core';
import { Movie } from '../../../modelos/movie';
import { MovieService } from '../../../servicios/movie';

@Component({
  selector: 'app-hero',
  imports: [],
  templateUrl: './hero.html',
  styleUrl: './hero.scss',
})
export class Hero {

  movie = input.required<Movie>();


}
