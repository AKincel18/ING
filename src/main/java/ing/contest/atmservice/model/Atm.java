package ing.contest.atmservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Atm {

    @Min(1)
    @Max(9999)
    protected int region;

    @Min(1)
    @Max(9999)
    protected int atmId;

    @JsonIgnore
    public String getRegionAtmId() {
        return region + ":" + atmId;
    }
}
