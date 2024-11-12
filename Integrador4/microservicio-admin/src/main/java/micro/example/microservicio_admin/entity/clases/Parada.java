package micro.example.microservicio_admin.entity.clases;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Parada implements Serializable {

    private long id;
    private Long x;
    private Long y;
    private List<Monopatin> monopatinesEnLaParada;

}

