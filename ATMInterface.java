package atm;
import java.util.*;

public class ATMInterface {
    static Scanner scanner = new Scanner(System.in);
    static String language = "en"; // default
    static Bank bank = new Bank();
    static AccountHolder currentUser = null;

    static Map<String, Map<String, String>> messages = new HashMap<>();

    public static void main(String[] args) {
        loadMessages();
        printWelcome();
        selectLanguage();
        login();

        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            switch (choice) {
                case 1:
                    currentUser.getAccount().printTransactionHistory();
                    break;
                case 2:
                    System.out.print(getMessage("withdraw") + " ₹");
                    double wAmount = scanner.nextDouble();
                    if (currentUser.getAccount().withdraw(wAmount)) {
                        System.out.println("✅ Withdrawn: ₹" + wAmount);
                    } else {
                        System.out.println("❌ Insufficient balance.");
                    }
                    break;
                case 3:
                    System.out.print(getMessage("deposit") + " ₹");
                    double dAmount = scanner.nextDouble();
                    currentUser.getAccount().deposit(dAmount);
                    System.out.println("✅ Deposited: ₹" + dAmount);
                    break;
                case 4:
                    scanner.nextLine();
                    System.out.print("Enter recipient user ID: ");
                    String recipientId = scanner.nextLine();
                    AccountHolder recipient = bank.getUser(recipientId);
                    if (recipient == null) {
                        System.out.println("❌ Invalid recipient.");
                    } else {
                        System.out.print(getMessage("transfer") + " ₹");
                        double tAmount = scanner.nextDouble();
                        if (currentUser.getAccount().transfer(recipient.getAccount(), tAmount)) {
                            System.out.println("✅ Transferred: ₹" + tAmount);
                        } else {
                            System.out.println("❌ Transfer failed. Insufficient balance.");
                        }
                    }
                    break;
                case 5:
                    System.out.println(getMessage("bye"));
                    return;
                default:
                    System.out.println(getMessage("invalid"));
            }
        }
    }

    static void loadMessages() {
        Map<String, String> en = new HashMap<>();
        en.put("welcome", "State Bank of India - ATM Interface");
        en.put("select_lang", "Please select language:");
        en.put("enter_id", "Enter User ID:");
        en.put("enter_pin", "Enter PIN:");
        en.put("login_success", "Login successful!");
        en.put("menu", "1. Transaction History  2. Withdraw  3. Deposit  4. Transfer  5. Exit");
        en.put("history", "Showing transaction history...");
        en.put("withdraw", "Enter withdrawal amount:");
        en.put("deposit", "Enter deposit amount:");
        en.put("transfer", "Enter account to transfer and amount:");
        en.put("bye", "Thank you for using SBI ATM. Goodbye!");
        en.put("invalid", "Invalid option. Try again.");

        Map<String, String> hi = new HashMap<>();
        hi.put("welcome", "भारतीय स्टेट बैंक - एटीएम इंटरफेस");
        hi.put("select_lang", "कृपया भाषा चुनें:");
        hi.put("enter_id", "यूज़र आईडी दर्ज करें:");
        hi.put("enter_pin", "पिन दर्ज करें:");
        hi.put("login_success", "लॉगिन सफल!");
        hi.put("menu", "1. लेन-देन इतिहास  2. निकासी  3. जमा  4. ट्रांसफर  5. बाहर निकलें");
        hi.put("history", "लेन-देन इतिहास दिखा रहे हैं...");
        hi.put("withdraw", "निकासी राशि दर्ज करें:");
        hi.put("deposit", "जमा राशि दर्ज करें:");
        hi.put("transfer", "खाते और राशि दर्ज करें:");
        hi.put("bye", "SBI एटीएम उपयोग करने के लिए धन्यवाद। अलविदा!");
        hi.put("invalid", "अमान्य विकल्प। कृपया पुन: प्रयास करें।");

        Map<String, String> kn = new HashMap<>();
        kn.put("welcome", "ಸ್ಟೇಟ್ ಬ್ಯಾಂಕ್ ಆಫ್ ಇಂಡಿಯಾ - ಎಟಿಎಂ ಇಂಟರ್ಫೇಸ್");
        kn.put("select_lang", "ದಯವಿಟ್ಟು ಭಾಷೆ ಆಯ್ಕೆಮಾಡಿ:");
        kn.put("enter_id", "ಬಳಕೆದಾರ ಐಡಿ ನಮೂದಿಸಿ:");
        kn.put("enter_pin", "ಪಿನ್ ನಮೂದಿಸಿ:");
        kn.put("login_success", "ಲಾಗಿನ್ ಯಶಸ್ವಿಯಾಗಿದೆ!");
        kn.put("menu", "1. ವ್ಯವಹಾರ ಇತಿಹಾಸ  2. ಹಣ ತೆಗೆಯುವುದು  3. ಠೇವಣಿ  4. ವರ್ಗಾವಣೆ  5. ನಿರ್ಗಮಿಸಿ");
        kn.put("history", "ವ್ಯವಹಾರ ಇತಿಹಾಸ ತೋರಿಸಲಾಗುತ್ತಿದೆ...");
        kn.put("withdraw", "ತೆಗೆದುಕೊಳ್ಳುವ ಮೊತ್ತ ನಮೂದಿಸಿ:");
        kn.put("deposit", "ಠೇವಣಿ ಮೊತ್ತ ನಮೂದಿಸಿ:");
        kn.put("transfer", "ಖಾತೆ ಸಂಖ್ಯೆ ಮತ್ತು ಮೊತ್ತ ನಮೂದಿಸಿ:");
        kn.put("bye", "SBI ಎಟಿಎಂ ಬಳಕೆ ಮಾಡಿದಕ್ಕಾಗಿ ಧನ್ಯವಾದಗಳು. ವಿದಾಯ!");
        kn.put("invalid", "ಅಮಾನ್ಯ ಆಯ್ಕೆ. ದಯವಿಟ್ಟು ಪುನಃ ಪ್ರಯತ್ನಿಸಿ.");

        messages.put("en", en);
        messages.put("hi", hi);
        messages.put("kn", kn);
    }

    static void printWelcome() {
        System.out.println("***********************************************");
        System.out.println("            (Suman's Bank of India)            ");
        System.out.println("***********************************************");
    }

    static void selectLanguage() {
        System.out.println("1. English\n2. हिन्दी\n3. ಕನ್ನಡ");
        System.out.print("> ");
        int langChoice = scanner.nextInt();
        scanner.nextLine(); // consume newline
        switch (langChoice) {
            case 2: language = "hi"; break;
            case 3: language = "kn"; break;
            default: language = "en"; break;
        }
    }

    static void login() {
        System.out.print(getMessage("enter_id") + " ");
        String userId = scanner.nextLine();

        System.out.print(getMessage("enter_pin") + " ");
        String pin = scanner.nextLine();

        currentUser = bank.login(userId, pin);
        if (currentUser != null) {
            System.out.println(getMessage("login_success"));
        } else {
            System.out.println("❌ " + getMessage("invalid"));
            login(); // Retry
        }
    }

    static void showMenu() {
        System.out.println("--------------------------------------------------");
        System.out.println(getMessage("menu"));
        System.out.print("> ");
    }

    static String getMessage(String key) {
        return messages.getOrDefault(language, messages.get("en")).getOrDefault(key, key);
    }
}
