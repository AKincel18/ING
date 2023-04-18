package ing.contest.transactions.model;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Transaction {
    //todo add fields validation
    private String debitAccount;
    private String creditAccount;
    private float amount;
}
