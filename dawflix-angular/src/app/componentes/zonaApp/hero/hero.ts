import { Component, input } from '@angular/core';
import { Movie } from '../../../modelos/movie';

@Component({
  selector: 'app-hero',
  imports: [],
  templateUrl: './hero.html',
  styleUrl: './hero.scss',
})
export class Hero {

  movie = input.required<Movie>();

}
