import java.util.InputMismatchException;
import java.util.Scanner;

public class TUI {
    public static void main(String[] args) {
        Player player1 = new Player("Greg");
        Player player2 = new Player("Joji");
        Game game = new Game(player1, player2);

        System.out.println("Player 1 Starting Deck: " + game.getPlayer1().getCards());
        System.out.println("Player 2 Starting Deck: " + game.getPlayer2().getCards() + "\n");


        boolean gameIsOver = false;
        while(!gameIsOver){
            System.out.println("Current Stack: " + game.getStack().peek() + "\n"); //initial card
            System.out.println(game.getCurrentPlayer().toString() + "'s Turn");

            //take user input for actions
            Scanner input = new Scanner(System.in);
            System.out.println("Select");
            String userInput = input.nextLine();

            // Split the user input into array of strings.
            String[] split = userInput.split("\\s+");
            String command = split[0].toLowerCase();
            int cardIndex = 0;


            switch (command){
                case "draw":
                    game.getCurrentPlayer().getCards().add(game.getRandomCard());
                    System.out.println(game.getCurrentPlayer().toString() + "'s deck: \n" + game.getCurrentPlayer().getCards() + "\n");
                    game.switchTurn();
                    break;
                case "deck":
                    System.out.println(game.toStringDeck());
                    break;
                case "exit":
                    gameIsOver = true;
                    break;
                case "pick":
                    try{
                        cardIndex = Integer.parseInt(split[1]);
                        Player currentPlayer = game.getCurrentPlayer();
                        Card chosenCard = game.getCurrentPlayer().getCards().get(cardIndex);
                        game.makeMove(chosenCard, currentPlayer);
                        System.out.println(currentPlayer.getCards().size());
                    } catch (IndexOutOfBoundsException |InputMismatchException e){
                        System.out.println("Invalid input");
                    }
                default:
                    System.out.println("Invalid input");
                    break;

            }
            if (game.getWinner() != null){
                System.out.println(game.getWinner().getName() + " wins!");;
            }

        }
    }

}
