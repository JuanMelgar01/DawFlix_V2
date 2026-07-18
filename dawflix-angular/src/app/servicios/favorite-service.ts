import { HttpClient } from '@angular/common/http';
import { inject, Injectable, signal } from '@angular/core';
import { environment } from '../../environment/environment';
import { Movie } from '../modelos/movie';

@Injectable({
  providedIn: 'root',
})
export class FavoriteService {

  private http = inject(HttpClient);

  private favoriteIds = signal<Set<number>>(new Set());
  readonly favorites = this.favoriteIds.asReadonly();
  private readonly apiUrl = `${environment.apiUrl}/favorites`;

  isFavorite(movieId:number): boolean{
    return this.favoriteIds().has(movieId);
  }

  loadFavorites(): void {

    this.http
            .get<Movie[]>(this.apiUrl)
            .subscribe({

              next: movies => {

                const ids = new Set(
                  movies.map(movie => movie.id)
                );

                this.favoriteIds.set(ids);
              }
            });
  }

  toggleFavorite(movieId:number): void {

    if (this.isFavorite(movieId)) {
      this.removeFavorite(movieId);
    } else {
      this.addFavorite(movieId);
    }
  }

private addFavorite(movieId: number): void {

  this.addFavoriteLocally(movieId);

  this.http
      .post(`${this.apiUrl}/${movieId}`, {})
      .subscribe({
          error: () => {
              this.removeFavoriteLocally(movieId);
          }
      });
}

private removeFavorite(movieId: number): void {

  this.removeFavoriteLocally(movieId);

  this.http
      .delete(`${this.apiUrl}/${movieId}`)
      .subscribe({
          error: () => {
              this.addFavoriteLocally(movieId);
          }
      });
}

  private addFavoriteLocally(movieId: number): void {

    this.favoriteIds.update(ids => {

      const updatedIds = new Set(ids);

      updatedIds.add(movieId);

      return updatedIds;

    });

  }

  private removeFavoriteLocally(movieId: number): void {

    this.favoriteIds.update(ids => {

      const updatedIds = new Set(ids);

      updatedIds.delete(movieId);

      return updatedIds;

    });

  }
}
