import java.util.ArrayList;
import java.util.List;

public class Bank {
    private String name;
    private List<Account> accounts;
    private double totalTransactionFee;
    private double totalTransferAmount;
    private double flatFee;
    private double percentFee;
    private List<Transaction> transactions;

    public Bank(String name, double flatFee, double percentFee) {
        this.name = name;
        this.accounts = new ArrayList<>();
        this.totalTransactionFee = 0;
        this.totalTransferAmount = 0;
        this.flatFee = flatFee;
        this.percentFee = percentFee;
        this.transactions = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public Account getAccount(String accountId) {
        for (Account account : accounts) {
            if (account.getAccountId().equals(accountId)) {
                return account;
            }
        }
        return null;
    }

    public boolean transfer(String fromAccountId, String toAccountId, double amount, boolean isFlatFee) {
        Account fromAccount = getAccount(fromAccountId);
        Account toAccount = getAccount(toAccountId);
        if (fromAccount == null || toAccount == null) {
            System.out.println("Account not found");
            return false;
        }

        double fee = isFlatFee ? flatFee : (amount * percentFee / 100);
        double totalAmount = amount + fee;
        if (fromAccount.withdraw(totalAmount)) {
            toAccount.deposit(amount);
            totalTransactionFee += fee;
            totalTransferAmount += amount;
            transactions.add(new Transaction(fromAccountId, toAccountId, amount, "Transfer"));
            return true;
        } else {
            System.out.println("Not enough funds");
            return false;
        }
    }

    public void deposit(String accountId, double amount) {
        Account account = getAccount(accountId);
        if (account != null) {
            account.deposit(amount);
            transactions.add(new Transaction(null, accountId, amount, "Deposit"));
        } else {
            System.out.println("Account not found");
        }
    }

    public void withdraw(String accountId, double amount) {
        Account account = getAccount(accountId);
        if (account != null) {
            if (account.withdraw(amount)) {
                transactions.add(new Transaction(accountId, null, amount, "Withdrawal"));
            } else {
                System.out.println("Not enough funds");
            }
        } else {
            System.out.println("Account not found");
        }
    }

    public void printAccounts() {
        for (Account account : accounts) {
            System.out.println(account.getUserName() + " : " + account.getBalance());
        }
    }

    public void printTransactions() {
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

}
