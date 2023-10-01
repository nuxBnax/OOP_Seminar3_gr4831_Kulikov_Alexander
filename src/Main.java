import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game game = new NumberGame();
        game.start(5,2);
        while (game.getGameStatus() != GameStatus.WIN && game.getGameStatus() != GameStatus.LOSE) {
            Scanner scanner = new Scanner(System.in);
            Answer answer = game.inputValue(scanner.nextLine());
            System.out.println("answer = " + answer);
        }
        System.out.println("game.getGameStatus = " + game.getGameStatus());
    }
}