package ing.contest.onlinegame.generator;

import com.fasterxml.jackson.databind.ObjectMapper;
import ing.contest.onlinegame.model.Clan;
import ing.contest.onlinegame.model.Players;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGenerator {
    public static void generateData() {
        int groupCount = 1000;
        int clanSize = 20000;
        List<Clan> clans = new ArrayList<>();
        for (int i = 0; i < clanSize; i++) {
            int numberOfPlayers = new Random().nextInt(groupCount) + 1;
            int points = new Random().nextInt(groupCount) + 1;
            Clan clan = new Clan(numberOfPlayers, points);
            clans.add(clan);
        }
        Players players = new Players(groupCount, clans);

        ObjectMapper mapper = new ObjectMapper();
        try {
            String pathName = Path.of("").toAbsolutePath() + "\\src\\main\\java\\ing\\contest\\onlinegame\\generator\\players.json";
            File file = new File(pathName);
            mapper.writeValue((file), players);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
