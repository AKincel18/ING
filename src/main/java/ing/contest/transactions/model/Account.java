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
    private String debitCount;
    private String creditCount;
    private float balance;
}
