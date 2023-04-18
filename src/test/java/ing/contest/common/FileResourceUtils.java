package ing.contest.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ing.contest.atmservice.model.Atm;
import ing.contest.atmservice.model.ServiceTask;
import ing.contest.onlinegame.model.Clan;
import ing.contest.onlinegame.model.Players;
import ing.contest.transactions.model.Account;
import ing.contest.transactions.model.Transaction;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class FileResourceUtils {

    public static final String ATM_SERVICE_FOLDER_NAME = "atmservice/";
    public static final String ONLINE_GAME_FOLDER_NAME = "onlinegame/";
    public static final String TRANSACTION_FOLDER_NAME = "transaction/";

    public static List<ServiceTask> getServiceTasks(String fileName) {
        return getResource(ATM_SERVICE_FOLDER_NAME, fileName, new TypeReference<>() {});
    }

    public static List<Atm> getAtms(String fileName) {
        return getResource(ATM_SERVICE_FOLDER_NAME, fileName, new TypeReference<>() {});
    }

    public static Players getPlayers(String fileName) {
        return getResource(ONLINE_GAME_FOLDER_NAME, fileName, new TypeReference<>() {});
    }

    public static List<List<Clan>> getClans(String fileName) {
        return getResource(ONLINE_GAME_FOLDER_NAME, fileName, new TypeReference<>() {});
    }

    public static List<Transaction> getTransactions(String fileName) {
        return getResource(TRANSACTION_FOLDER_NAME, fileName, new TypeReference<>() {});
    }

    public static List<Account> getAccounts(String fileName) {
        return getResource(TRANSACTION_FOLDER_NAME, fileName, new TypeReference<>() {});
    }

    private static <T> T getResource(String folderName, String fileName, TypeReference<T> typeReference) {
        String resourcePath = getResourcePath(folderName, fileName);
        try {
            return readValue(new File(resourcePath), typeReference);
        } catch (IOException e) {
            throw new RuntimeException("Error reading file " + fileName, e);
        }
    }

    private static String getResourcePath(String folderName, String fileName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resourceUrl = classLoader.getResource(folderName + fileName);
        if (resourceUrl == null) {
            throw new IllegalArgumentException("File not found: " + fileName);
        }
        return resourceUrl.getFile();
    }

    private static <T> T readValue(File file, TypeReference<T> typeReference) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(file, typeReference);
    }

}
