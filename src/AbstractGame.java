import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public abstract class AbstractGame implements Game {

    Integer sizeWord;
    Integer maxTry;
    String computerWord;
    ArrayList<String> history;

    GameStatus gameStatus = GameStatus.INIT;

    abstract ArrayList<String> generateCharList();

    /**
     * start() - Функция инициализирует игру
     * @param sizeWord - РАЗМЕР слова
     * @param maxTry - КОЛИЧЕСТВО попыток
     */
    @Override
    public void start(Integer sizeWord, Integer maxTry) {
        this.sizeWord = sizeWord;
        this.maxTry = maxTry;
        computerWord = generateWord();
        gameStatus = GameStatus.START;
        history = new ArrayList<>();
        System.out.println("Загаданный компьютером набор символов - " + computerWord);
    }

    /**
     * generateWord() - Функция генерирует слово из набора символов или букв из
     * массива generateCharList()
     */
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

    /**
     * inputValue() - Функция принимает команды от пользователя из консоли и обрабатывает их
     * @param value - Значение набранное пользователем (считывается из консоли).
     * @return
     */
    @Override
    public Answer inputValue(String value) {
        switch (value){
            case "res": System.out.println("Вы решили перезапустить игру");
                gameStatus = GameStatus.STOP;
                history.clear();
                menu();
                break;
            case "his":
                System.out.println("Вы решили просмотреть историю сделанных ходов");
                for (String item : history) {
                    System.out.println(item);
                }
                break;
            case "exit":
                System.out.println("Вы решили сохранить нервные клетки и прервали игру! Всего доброго!");
                gameStatus = GameStatus.STOP;
                history.clear();
                break;
            default:
                DateFormat dateF = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                Calendar cal = Calendar.getInstance();
                String time = dateF.format(cal.getTime());

                history.add(time + " - " + value);
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
        return null;
    }

    /**
     * menu() - Функция вызывает меню позволяющее пользователю выбрать игру или выйти из нее
     */
    public static void menu() {

        Scanner cs = new Scanner(System.in);
        System.out.println("Добро пожаловать в ИГРУ БЫКИ/КОРОВЫ  \n" +
                "Выберите цифру с командой: \n" +
                "1 - Выбрать раскладку с цифрами \n" +
                "2 - Выбрать раскладку с РУССКИМ алфавитом \n" +
                "3 - Выбрать раскладку с АНГЛИЙСКИМ алфавитом \n" +
                "4 - Выход из игры");

        int num = cs.nextInt();
        switch (num) {
            case 1:
                System.out.println("Шикарно! Вы выбрали игру с цифрами!");
                System.out.println("Введите количество цифр в угадываемом слове:");
                int sizeWord = cs.nextInt();
                System.out.println("Введите количество попыток:");
                int maxTry = cs.nextInt();

                playing(sizeWord, maxTry, new NumberGame());
                break;
            case 2:
                System.out.println("Отлично! Вы выбрали игру с РУССКИМ алфавитом!");
                System.out.println("Введите количество букв в угадываемом слове:");
                sizeWord = cs.nextInt();
                System.out.println("Введите количество попыток:");
                maxTry = cs.nextInt();

                playing(sizeWord, maxTry, new RuAlphabetGame());
                break;

            case 3:
                System.out.println("Великолепно! Вы выбрали игру с АНГЛИЙСКИМ алфавитом!");
                System.out.println("Введите количество букв в угадываемом слове:");
                sizeWord = cs.nextInt();
                System.out.println("Введите количество попыток:");
                maxTry = cs.nextInt();

                playing(sizeWord, maxTry, new EnAlphabetGame());
                break;

            case 4:
                System.out.println("Спасибо что играли в нашу ИГРУ! Все должно быть в меру!");
                break;

            default:
                System.out.println("Ошибка! Введите цифру среди предложенных в меню!");
                break;
        }
        cs.close();
    }

    /**
     * playing() - Функция определяет статус игры и определяет ее дальнейший ход в зависимости от answer
     */
    public static void playing(Integer sizeWord, Integer maxTry, Game game) {
        game.start(sizeWord,maxTry);

        while (game.getGameStatus() != GameStatus.WIN && game.getGameStatus() != GameStatus.LOSE &&
                game.getGameStatus() != GameStatus.STOP) {
            Scanner scanner = new Scanner(System.in);
            Answer answer = game.inputValue(scanner.nextLine());
            if(answer!=null){
                System.out.println(answer + "\n" +
                        "(ДОСТУПНЫЕ КОМАНДЫ: res - перезапуск игры, his - просмотр истории ходов, exit - выход из игры)");
            }
        }
        System.out.println("СТАТУС ИГРЫ: " + game.getGameStatus());
        if(game.getGameStatus() == GameStatus.WIN || game.getGameStatus() == GameStatus.LOSE) {
            Scanner sc= new Scanner(System.in);
            System.out.println("Желаете сыграть еще? Наберите - yes");
            String text = sc.nextLine();
            if(text.equals("yes")){
                menu();
            } else {
                System.out.println("Всего Доброго!");
            }
        }
    }
    @Override
    public GameStatus getGameStatus() {
        return gameStatus;
    }
}
