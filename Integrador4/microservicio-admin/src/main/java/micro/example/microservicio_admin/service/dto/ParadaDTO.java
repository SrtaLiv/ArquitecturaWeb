package micro.example.microservicio_admin.service.dto;

import lombok.Data;
import java.util.List;

@Data
public class ParadaDTO {
    private Long x;
    private Long y;
    private List<Long> monopatinIds;
}
