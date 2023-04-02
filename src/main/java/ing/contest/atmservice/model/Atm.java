package ing.contest.atmservice.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class Atm {
    @Min(1)
    @Max(9999)
    private final int region;

    @Min(1)
    @Max(9999)
    private final int atmId;

    public Atm(ServiceTask serviceTask) {
        region = serviceTask.getRegion();
        atmId = serviceTask.getAtmId();
    }
}
