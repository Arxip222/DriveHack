package demo.app.troika_game;

public class QR_item {
    String code;
    int balls;

    public QR_item() {
    }

    public QR_item(String code, int balls) {
        this.code = code;
        this.balls = balls;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getBalls() {
        return balls;
    }

    public void setBalls(int balls) {
        this.balls = balls;
    }
}
