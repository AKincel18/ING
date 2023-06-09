package ing.contest.onlinegame.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class Clan {

    @Min(1)
    @Max(1000)
    private int numberOfPlayers;

    @Min(1)
    @Max(1000000)
    private int points;
}
