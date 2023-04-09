package ing.contest.atmservice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ing.contest.atmservice.model.Atm;
import ing.contest.atmservice.model.ServiceTask;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class FileResourceUtils {

    public static List<ServiceTask> getServiceTasks(String fileName) {
        String resourcePath = getResourcePath(fileName);
        try {
            return readValue(new File(resourcePath), new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException("Error reading file " + fileName, e);
        }
    }

    public static List<Atm> getAtms(String fileName) {
        String resourcePath = getResourcePath(fileName);
        try {
            return readValue(new File(resourcePath), new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException("Error reading file " + fileName, e);
        }
    }

    private static String getResourcePath(String fileName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resourceUrl = classLoader.getResource("atmservice/" + fileName);
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
