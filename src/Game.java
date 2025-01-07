import java.util.ArrayList;
import java.util.LinkedList;

// TODO: Stack should use a linked list. draw and initial card should use the .pop function
public class Game {
    private Player player1;
    private Player player2;
    private ArrayList<Card> deck;
    private LinkedList<Card> stack;


    private Card card;
    private Player currentPlayer;
    private final int cardPerColour = 3;
    int numberOfCardsPerPlayer = 5;// 19


    public LinkedList<Card> getStack() {
        return stack;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public String toStringDeck(){
        return "\n" + getPlayer1().toString() + "'s deck: \n" + getPlayer1().getCards() + "\n"
        + getPlayer2().toString() + "'s deck: \n" + getPlayer2().getCards() + "\n";
    }
    /**
     * Constructor.
     * Initialise the whole deck.
     * Provide both players with 5 cards
     * Initialise the stack. e.g. get a random card from the deck
     * @param player1
     * @param player2
     */
    public Game(Player player1, Player player2){
        this.deck = new ArrayList<>();
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.stack = new LinkedList<>();

        initialiseDeck();
        initialCards(player1);
        initialCards(player2);
        initialiseStack();
    }
    /**
     * Get random card from the deck.
     * Get a random value between 0 and the deck size.
     */
    public Card getRandomCard(){
        try{
            int randomIndex = (int) (Math.random() * deck.size());
            Card card = deck.get(randomIndex);
            deck.remove(card);
            return card;

        } catch (ArrayIndexOutOfBoundsException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Initial stack from getRandom.
     * Card cannot be from the three: SKIP, REVERSE, DRAW_TWO,
     */
    public void initialiseStack(){
        Card currentCard = getRandomCard();
        while (currentCard.getType() == Card.CardType.SKIP || currentCard.getType() == Card.CardType.REVERSE || currentCard.getType() == Card.CardType.DRAW_TWO){
            currentCard = getRandomCard();
        }
        stack.push(currentCard);
    }

    /**
     * Draw 5 cards from the deck for each player.
     * @param player
     */
    public void initialCards(Player player){
        for(int i=0; i<numberOfCardsPerPlayer; i++){
            Card currentCard = getRandomCard();
            player.getCards().add(currentCard);
        }
    }


    /**
     * initialise the whole deck of uno.
     * This will act as a central point for every action in the game, e.g. get cards for user, remove user cards, etc.
     * Store every color and types on a separate array.
     * Loop through every
     */
    public void initialiseDeck(){
        Card.CardColour[] colors = Card.CardColour.values();
        Card.CardType[] types= Card.CardType.values();

        for(Card.CardType type: types){
            for(int i=0; i< colors.length; i++){
                this.deck.add(new Card(type, colors[i]));
            }
        }
    }

    /**
     * True, if the card that is chosen is from the player's deck.
     * @param card
     * @param player
     * @return
     */
    public boolean cardInDeck(Card card, Player player){
        if (player.getCards().contains(card)){
            return true;
        } else{
            return false;
        }
    }

    /**
     * Checks if the color of the chosen card has the same color as the last card of the stack.
     */
    public boolean checkIfSameColor(Card card){
        assert getStack().peek() != null;
        if(getStack().peek().getColour() == card.getColour()){

            return true;
        } else {
            return false;
        }
    }

    /**
     * Move effects.
     * DRAW_TWO, switch the turn, then add two cards to the opponent.
     * SKIP, skip the turn
     * REVERSE, reverse the turn
     */
    public void moveEffect(Card card){
        Card.CardType chosenCardType = card.getType();

       if (chosenCardType == Card.CardType.DRAW_TWO) {
           for (int i = 0; i < 2; i++) {
               getOpponent().getCards().add(getRandomCard());
           }
       }

       if (!(chosenCardType == Card.CardType.REVERSE)) {
           switchTurn();
       }
    }
    /**
     * Make move.
     * Remove the chosen card from the player's deck
     * Add the chosen card to the stack
     * @param card
     * @param player
     */
    public void makeMove(Card card, Player player){
        if (cardInDeck(card, player)){
            if (checkIfSameColor(card)){
                player.getCards().remove(card);
                stack.push(card);
                moveEffect(card);
            } else{
                System.out.println("Color mismatch!");
            }
        }

    }

    /**
     * Check if game has winner.
     */
    public Player getWinner(){
        if(player1.getCards().isEmpty()){
            return player1;
        } else if (player2.getCards().isEmpty()){
            return player2;
        } else{
            return null;
        }
    }

    /**
     * Switch turns.
     */
    public void switchTurn(){
        if (currentPlayer == player1){
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    /**
     * Get the opponent object.
     */
    public Player getOpponent(){
        return (currentPlayer == player1) ? player2: player1;
    }


}
