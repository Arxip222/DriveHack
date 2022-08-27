package demo.app.troika_game;

public class Place_item {
    int id;
    String name, place, image, wr1, wr2;

    public Place_item(int id, String name, String place, String image, String wr1, String wr2) {
        this.id = id;
        this.name = name;
        this.place = place;
        this.image = image;
        this.wr1 = wr1;
        this.wr2 = wr2;
    }

    public Place_item() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getWr1() {
        return wr1;
    }

    public void setWr1(String wr1) {
        this.wr1 = wr1;
    }

    public String getWr2() {
        return wr2;
    }

    public void setWr2(String wr2) {
        this.wr2 = wr2;
    }
}

