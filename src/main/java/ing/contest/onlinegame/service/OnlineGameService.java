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
        List<Clan> clans = new ArrayList<>(players.getClans());
        int groupCount = players.getGroupCount();
        clans.sort(Comparator.comparing(Clan::getPoints).reversed().thenComparing(Clan::getNumberOfPlayers));
        List<List<Clan>> orderedGroups = new ArrayList<>();
        while (!clans.isEmpty()) {
            List<Clan> group = createGroup(clans, groupCount);
            orderedGroups.add(group);
            clans.removeAll(group);
        }
        return orderedGroups;
    }

    private List<Clan> createGroup(List<Clan> clans, int maxGroupCount) {
        List<Clan> group = new ArrayList<>();
        int groupCount = 0;
        for (Clan clan : clans) {
            if (maxGroupCount >= clan.getNumberOfPlayers() + groupCount) {
                group.add(clan);
                groupCount += clan.getNumberOfPlayers();
            }
            if (groupCount == maxGroupCount) {
                return group;
            }
        }
        return group;
    }
}
