public class Answer {
    int bull;
    int cow;
    int maxTry;

    /**
     *
     * @param bull  БЫКИ - количество совпадений по позиции и символу
     * @param cow  КОРОВЫ - количество совпадений по символу
     * @param maxTry  КОЛИЧЕСТВО попыток
     */
    public Answer(int bull, int cow,int maxTry) {
        this.bull = bull;
        this.cow = cow;
        this.maxTry = maxTry;
    }

    @Override
    public String toString() {
        return "ПОДСКАЗКА: " +
                "bull - " + bull +
                " , cow - " + cow +
                ", попыток осталось: " + maxTry;
    }
}