package dawflix_api.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCont;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String genero;

    @Column(nullable = false)
    private int duracion;

    private Float puntuacion;
    private int numValoraciones;
    
    @Column(length = 500, nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private LocalDate fechaCreacion;
    
    @Column(nullable = false)
    private LocalDate fechaAltaSistema;
}
