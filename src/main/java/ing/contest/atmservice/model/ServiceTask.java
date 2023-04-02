package ing.contest.atmservice.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString
@Getter
@Setter
public class ServiceTask {
    @Min(1)
    @Max(9999)
    private int region;

    @Min(1)
    @Max(9999)
    private int atmId;

    private RequestType requestType;

}
