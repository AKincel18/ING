package ing.contest.atmservice.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ServiceTask {
    //todo use Atm class instead of duplicating the same fields (or inheritance?)
    @Min(1)
    @Max(9999)
    private int region;

    @Min(1)
    @Max(9999)
    private int atmId;

    private RequestType requestType;

    public String getRegionAtmId() {
        return region + ":" + atmId;
    }
}
