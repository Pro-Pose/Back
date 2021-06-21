package Team.Propose.model;

import lombok.Data;

@Data
public class Player {
    private String name;
    private String imageURL;

    public Player() {};

    public Player(String name, String imageURL) {
        this.name = name;
        this.imageURL = imageURL;
    }
}
