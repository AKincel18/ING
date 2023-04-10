package ing.contest.onlinegame.service;

import ing.contest.onlinegame.model.Clan;
import ing.contest.onlinegame.model.Players;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OnlineGameService {
    public List<List<Clan>> calculate(Players players) {
        return new ArrayList<>();
    }
}
