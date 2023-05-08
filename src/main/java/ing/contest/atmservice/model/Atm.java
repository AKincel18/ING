package ing.contest.atmservice.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Atm {

    @Min(1)
    @Max(9999)
    private int region;

    @Min(1)
    @Max(9999)
    private int atmId;

    public Atm(ServiceTask serviceTask) {
        region = serviceTask.getRegion();
        atmId = serviceTask.getAtmId();
    }
}
