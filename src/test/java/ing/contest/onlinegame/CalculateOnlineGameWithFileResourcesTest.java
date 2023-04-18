package ing.contest.onlinegame;

import ing.contest.common.FileResourceUtils;
import ing.contest.onlinegame.model.Clan;
import ing.contest.onlinegame.model.Players;
import ing.contest.onlinegame.service.OnlineGameService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CalculateOnlineGameWithFileResourcesTest {

    private static final String EXAMPLE_REQUEST_FILE = "example_request.json";
    private static final String EXAMPLE_RESPONSE_FILE = "example_response.json";

    @Autowired
    private OnlineGameService onlineGameService;

    @Test
    @DisplayName("should correctly calculate online game for example request")
    public void calculateOnlineGameExampleOneShouldReturnExpectedResult() {
        //given
        Players players = FileResourceUtils.getPlayers(EXAMPLE_REQUEST_FILE);

        //when
        List<List<Clan>> actual = onlineGameService.calculate(players);
        List<List<Clan>> expected = FileResourceUtils.getClans(EXAMPLE_RESPONSE_FILE);

        //then
        Assertions.assertEquals(expected, actual);
    }
}
