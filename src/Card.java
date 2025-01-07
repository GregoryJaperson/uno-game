import java.util.ArrayList;

public class Card{
    public enum CardType {
        ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE,
        SKIP, REVERSE, DRAW_TWO,
    }
    public enum CardColour {
        RED, GREEN, BLUE, YELLOW
    }

    private CardType type;
    private CardColour colour;

    public Card(CardType type, CardColour colour){
        this.type = type;
        this.colour = colour;
    }

    public CardType getType() {
        return type;
    }

    public CardColour getColour() {
        return colour;
    }

    public String toString(){
        return type + "-" + colour;
    }

}
