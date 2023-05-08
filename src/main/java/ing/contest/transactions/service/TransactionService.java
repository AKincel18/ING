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

    public List<Account> createReport(List<Transaction> transactions) {
        final Map<String, Account> report = new HashMap<>();

        transactions.forEach(transaction -> {
            Account debitAccount = getOrCreateAccount(report, transaction.getDebitAccount());
            Account creditAccount = getOrCreateAccount(report, transaction.getCreditAccount());

            debitAccount.incrementDebitCount();
            creditAccount.incrementCreditCount();

            debitAccount.decreaseBalance(transaction.getAmount());
            creditAccount.increaseBalance(transaction.getAmount());

            debitAccount.roundBalance();
            creditAccount.roundBalance();
        });

        return report.values()
                .stream()
                .sorted(Comparator.comparing(Account::getAccount))
                .toList();
    }

    private Account getOrCreateAccount(Map<String, Account> report, String accountString) {
        return report.computeIfAbsent(accountString, Account::new);
    }
}
