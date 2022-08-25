package demo.app.troika_game;

public class Place_item {
    int id;
    String name, description, city, image;

    public Place_item(int id, String name, String description, String city, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.city = city;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

