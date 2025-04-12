package atm;

public class AccountHolder {
    private String userId;
    private String pin;
    private Account account;

    public AccountHolder(String userId, String pin, double initialBalance) {
        this.userId = userId;
        this.pin = pin;
        this.account = new Account(initialBalance);
    }

    public String getUserId() {
        return userId;
    }

    public boolean checkPin(String inputPin) {
        return this.pin.equals(inputPin);
    }

    public Account getAccount() {
        return account;
    }
}
