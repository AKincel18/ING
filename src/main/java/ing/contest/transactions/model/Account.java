package ing.contest.transactions.model;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Account {
    //todo add fields validation
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
        balance = (float) (Math.round(balance * 10.0) / 10.0);
    }
}
