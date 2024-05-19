public class Transaction {
    private String originatingAccountId;
    private String resultingAccountId;
    private double amount;
    private String reason;
    
    public Transaction(String originatingAccountId, String resultingAccountId, double amount, String reason) {
        this.originatingAccountId = originatingAccountId;
        this.resultingAccountId = resultingAccountId;
        this.amount = amount;
        this.reason = reason;
    }

}
