package dawflix_api.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
    name= "favorites",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id" , "movie_id"})
    }
)
@Getter
@Setter
@NoArgsConstructor
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "movie_id", nullable = false)
    private Long movieId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public Favorite(User user, Long movieId) {

        this.user = user;

        this.movieId = movieId;

        this.createdAt = LocalDateTime.now();

    }
}