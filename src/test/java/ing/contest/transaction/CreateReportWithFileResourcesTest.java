package ing.contest.transaction;

import ing.contest.common.FileResourceUtils;
import ing.contest.transactions.model.Account;
import ing.contest.transactions.model.Transaction;
import ing.contest.transactions.service.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CreateReportWithFileResourcesTest {
    private static final String EXAMPLE_REQUEST_FILE = "example_request.json";
    private static final String EXAMPLE_RESPONSE_FILE = "example_response.json";

    @Autowired
    private TransactionService transactionService;

    @Test
    @DisplayName("should correctly create report for example request")
    public void createReportExampleOneShouldReturnExpectedResult() {
        //given
        List<Transaction> transaction = FileResourceUtils.getTransactions(EXAMPLE_REQUEST_FILE);

        //when
        List<Account> actual = transactionService.createReport(transaction);
        List<Account> expected = FileResourceUtils.getAccounts(EXAMPLE_RESPONSE_FILE);

        //then
        Assertions.assertEquals(expected, actual);
    }
}
