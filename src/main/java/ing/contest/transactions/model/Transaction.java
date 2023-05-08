package ing.contest.transactions.model;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Transaction {

    @Size(min = 26, max = 26)
    private String debitAccount;

    @Size(min = 26, max = 26)
    private String creditAccount;

    private float amount;
}
