package ing.contest.transactions.service;

import ing.contest.transactions.model.Account;
import ing.contest.transactions.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransactionService {
    private final Map<String, Account> report = new HashMap<>();

    public List<Account> createReport(List<Transaction> transactions) {

        for (Transaction transaction : transactions) {
            Account debitAccount = getAccountFromReport(transaction.getDebitAccount());
            Account creditAccount = getAccountFromReport(transaction.getCreditAccount());

            debitAccount.incrementDebitCount();
            creditAccount.incrementCreditCount();

            debitAccount.decreaseBalance(transaction.getAmount());
            creditAccount.increaseBalance(transaction.getAmount());

            debitAccount.roundBalance();
            creditAccount.roundBalance();
        }

        return report.values()
                .stream()
                .sorted(Comparator.comparing(Account::getAccount))
                .toList();
    }

    private Account getAccountFromReport(String accountString) {
        Account account = report.putIfAbsent(accountString, new Account(accountString));
        return account != null ? account : report.get(accountString);
    }
}
