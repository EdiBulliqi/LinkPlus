import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank("MyBank", 10.0, 5.0);
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Choose an option:");
                System.out.println("1. Create Account");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Transfer");
                System.out.println("5. Check Balance");
                System.out.println("6. List Accounts");
                System.out.println("7. List Transactions");
                System.out.println("8. Exit");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Enter Account ID:");
                        String accountId = scanner.next();
                        System.out.println("Enter User Name:");
                        String userName = scanner.next();
                        System.out.println("Enter Initial Balance:");
                        double initialBalance = scanner.nextDouble();
                        Account account = new Account(accountId, userName, initialBalance);
                        bank.addAccount(account);
                        break;

                    case 2:
                        System.out.println("Enter Account ID:");
                        accountId = scanner.next();
                        System.out.println("Enter Amount to Deposit:");
                        double depositAmount = scanner.nextDouble();
                        bank.deposit(accountId, depositAmount);
                        break;

                    case 3:
                        System.out.println("Enter Account ID:");
                        accountId = scanner.next();
                        System.out.println("Enter Amount to Withdraw:");
                        double withdrawAmount = scanner.nextDouble();
                        bank.withdraw(accountId, withdrawAmount);
                        break;

                    case 4:
                        System.out.println("Enter Originating Account ID:");
                        String fromAccountId = scanner.next();
                        System.out.println("Enter Resulting Account ID:");
                        String toAccountId = scanner.next();
                        System.out.println("Enter Amount to Transfer:");
                        double transferAmount = scanner.nextDouble();
                        System.out.println("Use Flat Fee? (true/false):");
                        boolean isFlatFee = scanner.nextBoolean();
                        bank.transfer(fromAccountId, toAccountId, transferAmount, isFlatFee);
                        break;

                    case 5:
                        System.out.println("Enter Account ID:");
                        accountId = scanner.next();
                        Account accountDetails = bank.getAccount(accountId);
                        if (accountDetails != null) {
                            System.out.println("Balance: " + accountDetails.getBalance());
                        } else {
                            System.out.println("Account not found");
                        }
                        break;

                    case 6:
                        bank.printAccounts();
                        break;

                    case 7:
                        bank.printTransactions();
                        break;

                    case 8:
                        System.out.println("Exiting...");
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }
}
