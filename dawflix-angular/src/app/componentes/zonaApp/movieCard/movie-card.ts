import { Component, computed, inject, input } from '@angular/core';
import { Movie } from '../../../modelos/movie';
import { FavoriteService } from '../../../servicios/favorite-service';

@Component({
  selector: 'app-movie-card',
  imports: [],
  templateUrl: './movie-card.html',
  styleUrl: './movie-card.scss',
})
export class MovieCard {

  private favoriteService = inject(FavoriteService);

  movie = input.required<Movie>();

  isFavorite = computed(() => this.favoriteService.isFavorite(this.movie().id));

  toggleFavorite(event: MouseEvent): void {

    event.stopPropagation();

    this.favoriteService.toggleFavorite(
        this.movie().id
    );

}

}
