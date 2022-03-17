package get_http_request.day15;

public class Country {
    private int id;
    private String name;
    private String states;
    /*
    "country": {
    "id": 3,
    "name": "USA",
    "states": null
}
     */

    public Country() {
    }

    public Country(int id, String name, String states) {
        this.id = id;
        this.name = name;
        this.states = states;
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

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", states='" + states + '\'' +
                '}';
    }
}
