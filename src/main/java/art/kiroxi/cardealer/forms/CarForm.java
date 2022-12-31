package art.kiroxi.cardealer.forms;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CarForm {

    private Long id;
    private Long dealerId;
    private Long makeId;
    private Long modelId;
    private String vin;
    private Integer year;
    private String color;
    private BigDecimal price;
    private Long statusId;

}
