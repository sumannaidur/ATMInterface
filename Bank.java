package atm;
import java.util.*;

public class Bank {
    private Map<String, AccountHolder> users;

    public Bank() {
        users = new HashMap<>();

        // Dummy users
        users.put("user1", new AccountHolder("user1", "1234", 10000));
        users.put("user2", new AccountHolder("user2", "5678", 5000));
    }

    public AccountHolder login(String userId, String pin) {
        AccountHolder user = users.get(userId);
        if (user != null && user.checkPin(pin)) {
            return user;
        }
        return null;
    }

    public AccountHolder getUser(String userId) {
        return users.get(userId);
    }
}
