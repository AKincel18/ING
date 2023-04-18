package ing.contest.transactions.controller;

import ing.contest.transactions.model.Account;
import ing.contest.transactions.model.Transaction;
import ing.contest.transactions.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transactions/report")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Account>> calculate(@RequestBody @Valid List<Transaction> transaction) {
        List<Account> result = transactionService.createReport(transaction);
        return ResponseEntity.ok(result);
    }
}
