package ing.contest.onlinegame.service;

import ing.contest.onlinegame.model.Clan;
import ing.contest.onlinegame.model.Players;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class OnlineGameService {
    public List<List<Clan>> calculate(Players players) {
        players.getClans().sort(Comparator.comparing(Clan::getPoints).reversed().thenComparing(Clan::getNumberOfPlayers));
        List<List<Clan>> result = new ArrayList<>();
        do {
            List<Clan> group = createGroup(players);
            result.add(group);
            players.getClans().removeAll(group);
        } while (players.getClans().size() > 0);
        return result;
    }

    private List<Clan> createGroup(Players players) {
        List<Clan> group = new ArrayList<>();
        int groupSize = 0;
        for (Clan clan : players.getClans()) {
            if (players.getGroupCount() >= clan.getNumberOfPlayers() + groupSize) {
                group.add(clan);
                groupSize += clan.getNumberOfPlayers();
            }
            if (groupSize == players.getGroupCount()) {
                return group;
            }
        }
        return group;
    }
}
