package ing.contest.atmservice;

import ing.contest.atmservice.model.Atm;
import ing.contest.atmservice.model.ServiceTask;
import ing.contest.atmservice.service.AtmService;
import ing.contest.common.FileResourceUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CalculateOrderTestWithFileResourcesTest {

    private static final String EXAMPLE_1_REQUEST_FILE = "example_1_request.json";
    private static final String EXAMPLE_1_RESPONSE_FILE = "example_1_response.json";
    private static final String EXAMPLE_2_REQUEST_FILE = "example_2_request.json";
    private static final String EXAMPLE_2_RESPONSE_FILE = "example_2_response.json";

    @Autowired
    private AtmService atmService;

    @Test
    @DisplayName("should correctly calculate order for example 1 request")
    public void calculateOrderExampleOneShouldReturnExpectedResult() {
        //given
        List<ServiceTask> serviceTasks = readServiceTasksFromFile(EXAMPLE_1_REQUEST_FILE);

        //when
        List<Atm> actual = atmService.calculateOrder(serviceTasks);
        List<Atm> expected = readAtmsFromFile(EXAMPLE_1_RESPONSE_FILE);

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should correctly calculate order for example 2 request")
    public void calculateOrderExampleTwoShouldReturnExpectedResult() {
        //given
        List<ServiceTask> serviceTasks = readServiceTasksFromFile(EXAMPLE_2_REQUEST_FILE);

        //when
        List<Atm> actual = atmService.calculateOrder(serviceTasks);
        List<Atm> expected = readAtmsFromFile(EXAMPLE_2_RESPONSE_FILE);

        //then
        Assertions.assertEquals(expected, actual);
    }

    private List<ServiceTask> readServiceTasksFromFile(String fileName) {
        return FileResourceUtils.getServiceTasks(fileName);
    }

    private List<Atm> readAtmsFromFile(String fileName) {
        return FileResourceUtils.getAtms(fileName);
    }

}
