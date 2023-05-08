package ing.contest.transactions.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import static ing.contest.transactions.utils.Utils.roundTo2DecimalPlaces;

@NoArgsConstructor
@Data
public class Account {

    private String account;

    private int debitCount;

    private int creditCount;

    private float balance;

    public Account(String account) {
        this.account = account;
    }

    public void incrementDebitCount() {
        debitCount += 1;
    }

    public void incrementCreditCount() {
        creditCount += 1;
    }

    public void increaseBalance(float amount) {
        balance += amount;
    }

    public void decreaseBalance(float amount) {
        balance -= amount;
    }

    public void roundBalance() {
        balance = roundTo2DecimalPlaces(balance);
    }
}
