package ing.contest.onlinegame.generator;

import com.fasterxml.jackson.databind.ObjectMapper;
import ing.contest.onlinegame.model.Clan;
import ing.contest.onlinegame.model.Players;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGenerator {
    public static void generateData() {
        int groupCount = 100;
        int clanSize = 20000;
        List<Clan> clans = new ArrayList<>();
        for (int i = 0; i < clanSize; i++) {
            int numberOfPlayers = new Random().nextInt(groupCount - 60) + 40;
            int points = new Random().nextInt(40) + 40;
            Clan clan = new Clan(numberOfPlayers, points);
            clans.add(clan);
        }
        Players players = new Players(groupCount, clans);

        ObjectMapper mapper = new ObjectMapper();
        try {
            //Convert object to JSON string and save into file directly
            mapper.writeValue(new File("C:/Users/akincel/Repos/ING/src/main/java/ing/contest/onlinegame/generator/players.json"), players);

            //Convert object to JSON string
            String jsonInString = mapper.writeValueAsString(players);
//            System.out.println(jsonInString);

            //Convert object to JSON string and pretty print
            jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(players);
//            System.out.println(jsonInString);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
