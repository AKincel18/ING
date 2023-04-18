package ing.contest.transactions.service;

import ing.contest.transactions.model.Account;
import ing.contest.transactions.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {
    public List<Account> createReport(List<Transaction> transactions) {
        return new ArrayList<>();
    }
}
