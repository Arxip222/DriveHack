package demo.app.troika_game;

public class User {
    String email, pass;
    int balance;

    public User() {
    }

    public User(String email, String pass, int balance) {
        this.email = email;
        this.pass = pass;
        this.balance = balance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
