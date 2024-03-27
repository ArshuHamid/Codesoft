import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Transaction {
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return type + ": " + amount;
    }
}

class BankAccount {
    private double balance;
    private List<Transaction> history;

    public BankAccount(double initialBalance) {
        balance = initialBalance;
        history = new ArrayList<>();
    }

    public void deposit(double amount) {
        balance += amount;
        history.add(new Transaction("Deposit", amount));
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            history.add(new Transaction("Withdrawal", amount));
            return true;
        }
        return false;
    }

    public double checkBalance() {
        return balance;
    }

    public List<Transaction> getHistory() {
        return history;
    }
}

class User {
    private String name;
    private String cardNumber;
    private String pin;
    private String bank;
    private BankAccount account;

    public User(String name, String cardNumber, String pin, String bank, double initialBalance) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.bank = bank;
        account = new BankAccount(initialBalance);
    }

    public String getName() {
        return name;
    }

    public boolean verifyPin(String enteredPin) {
        return pin.equals(enteredPin);
    }

    public void changePin(String newPin) {
        pin = newPin;
    }

    public BankAccount getAccount() {
        return account;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nCard Number: " + cardNumber + "\nBank: " + bank;
    }
}

public class ATM {
    private User user;

    public ATM() {
        user = new User("SAVING_ACCOUNT", "123456XXXX", "1234", "SBI", 1000.0);
    }

    public boolean login(String pin) {
        return user.verifyPin(pin);
    }

    public void menu(User user) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n****************************************");
            System.out.println("* Welcome, " + user.getName() + "!");
            System.out.println("* " + user);
            System.out.println("****************************************");
            System.out.println("* 1. Deposit");
            System.out.println("* 2. Withdraw");
            System.out.println("* 3. Check Balance");
            System.out.println("* 4. Mini Statement");
            System.out.println("* 5. Change PIN");
            System.out.println("* 6. Logout");
            System.out.println("****************************************");
            System.out.print("Please select an option: ");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    deposit(scanner, user);
                    break;
                case 2:
                    withdraw(scanner, user);
                    break;
                case 3:
                    System.out.println("Current balance: " + user.getAccount().checkBalance());
                    break;
                case 4:
                    miniStatement(user);
                    break;
                case 5:
                    changePin(scanner, user);
                    break;
                case 6:
                    System.out.println("Logging out.");
                    return;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

    private void deposit(Scanner scanner, User user) {
        System.out.print("Enter deposit amount: ");
        double depositAmount = scanner.nextDouble();
        user.getAccount().deposit(depositAmount);
        System.out.println("Deposit successful. Current balance: " + user.getAccount().checkBalance());
    }

    private void withdraw(Scanner scanner, User user) {
        System.out.print("Enter withdrawal amount: ");
        double withdrawAmount = scanner.nextDouble();
        if (user.getAccount().withdraw(withdrawAmount)) {
            System.out.println("Withdrawal successful. Current balance: " + user.getAccount().checkBalance());
        } else {
            System.out.println("Insufficient funds. Current balance: " + user.getAccount().checkBalance());
        }
    }

    private void miniStatement(User user) {
        System.out.println("Mini Statement:");
        List<Transaction> transactions = user.getAccount().getHistory();
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    private void changePin(Scanner scanner, User user) {
        System.out.print("Enter new PIN: ");
        String newPin = scanner.next();
        user.changePin(newPin);
        System.out.println("PIN changed successfully.");
    }

    public static void main(String[] args) {
        ATM atm = new ATM();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter PIN: ");
            String pin = scanner.next();
            if (atm.login(pin)) {
                atm.menu(atm.user);
            } else {
                System.out.println("Invalid PIN.");
            }
        }
    }
}
