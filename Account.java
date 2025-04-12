package atm;
import java.util.*;

public class Account {
    private double balance;
    private List<Transaction> transactions;

    public Account(double initialBalance) {
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add(new Transaction("Deposit", amount));
    }

    public boolean withdraw(double amount) {
        if (amount > balance) return false;
        balance -= amount;
        transactions.add(new Transaction("Withdraw", amount));
        return true;
    }

    public boolean transfer(Account toAccount, double amount) {
        if (amount > balance) return false;
        balance -= amount;
        toAccount.deposit(amount);
        transactions.add(new Transaction("Transfer", amount));
        return true;
    }

    public void printTransactionHistory() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (Transaction t : transactions) {
                System.out.println(t);
            }
        }
    }

    public double getBalance() {
        return balance;
    }
}
