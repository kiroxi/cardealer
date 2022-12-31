package art.kiroxi.cardealer.forms;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ServiceForm {

    private Long id;
    private String name;
    private BigDecimal price;

}
