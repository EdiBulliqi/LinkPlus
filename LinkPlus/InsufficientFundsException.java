public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

public boolean withdraw(double amount, int balance) throws InsufficientFundsException {
    if (amount > balance) {
        throw new InsufficientFundsException("Insufficient funds");
    }
    this.balance -= amount;
    return true;
}

public boolean transfer(String fromAccountId, String toAccountId, double amount, boolean FlatFee) {
    try {
        Account fromAccount = getAccount(fromAccountId);
        Account toAccount = getAccount(toAccountId);
        if (fromAccount == null || toAccount == null) {
            System.out.println("Account not found");
            return false;
        }

        double fee = isFlatFee ? flatFee : (amount * percentFee / 100);
        double totalAmount = amount + fee;
        fromAccount.withdraw(totalAmount);
        toAccount.deposit(amount);
        totalTransactionFee += fee;
        totalTransferAmount += amount;
        transactions.add(new Transaction(fromAccountId, toAccountId, amount, "Transfer"));
        return true;
    } catch (InsufficientFundsException e) {
        System.out.println(e.getMessage());
        return false;
    }
}
