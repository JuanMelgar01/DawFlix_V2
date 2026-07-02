package dawflix_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
    private int codigo;
    private String mensaje;
    private T datos;
}
