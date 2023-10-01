import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class AbstractGame implements Game {

    Integer sizeWord;
    Integer maxTry;
    String computerWord;

    GameStatus gameStatus = GameStatus.INIT;

    abstract ArrayList<String> generateCharList();

    @Override
    public void start(Integer sizeWord, Integer maxTry) {
        this.sizeWord = sizeWord;
        this.maxTry = maxTry;
        computerWord = generateWord();
        gameStatus = GameStatus.START;
        System.out.println("computerWord = " + computerWord);
    }

    private String generateWord() {
        List<String> alphabet = generateCharList();
        Random random = new Random();
        String res = "";
        for (int i = 0; i < sizeWord; i++) {
            int randomIndex = random.nextInt(alphabet.size());
            res += alphabet.get(randomIndex);
            alphabet.remove(randomIndex);
        }

        return res;

    }

    @Override
    public Answer inputValue(String value) {
        int bull = 0;
        int cow = 0;
        for (int i = 0; i < value.length(); i++) {
            if (value.charAt(i) == computerWord.charAt(i)) {
                bull++;
                cow++;
            } else if (computerWord.contains(String.valueOf(value.charAt(i)))) {
                cow++;
            }
        }
        if (bull == computerWord.length()) {
            gameStatus = GameStatus.WIN;
        }
        maxTry--;
        if (maxTry == 0 && gameStatus != GameStatus.WIN) {
            gameStatus = GameStatus.LOSE;
        }
        return new Answer(bull, cow, maxTry);
    }

    @Override
    public GameStatus getGameStatus() {
        return gameStatus;
    }
}
