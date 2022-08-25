package demo.app.troika_game;

public class Place_item {
    int id;
    String name, description, place, image;

    public Place_item(int id, String name, String description, String place, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.place = place;
        this.image = image;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}

