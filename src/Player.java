import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> cards;

    public String getName() {
        return name;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }


    public Player(String name){
        this.name = name;
        this.cards = new ArrayList<>();
    }

    public String toString(){
        return name;
    }

}
