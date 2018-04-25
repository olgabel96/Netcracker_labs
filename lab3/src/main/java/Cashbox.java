public class Cashbox {
    private static Cashbox instance;
    private int balance;

    private Cashbox() {
    }

    public static Cashbox getInstance() {
        if (instance == null) {
            instance = new Cashbox();
            instance.setBalance(500);
        }
        return instance;
    }

    public synchronized int getBalance() {
        return balance;
    }

    private void setBalance(int balance) {
        this.balance = balance;
    }

    public synchronized void putMoney(int cash) {
        this.balance += cash;
    }

    public synchronized void withdrawMoney(int cash) {
        this.balance -= cash;
    }
}
