import java.util.ArrayList;
import java.util.Arrays;

public class EnAlphabetGame extends AbstractGame{
    @Override
    ArrayList<String> generateCharList() {
        return new ArrayList<>(Arrays.asList("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s"
                                            ,"t","v","w","x","y","z","u"));
    }
    //Урок 3. Некоторые стандартные интерфейсы Java и примеры их использования
    //Дз:
    //Дописать игру быки-коровы
    //- на русском и английском алфавите
    //- сделать логирование действий и по запросу пользователя посмотреть всю историю игры
    //- доработать интерфейс взаимодействия с пользователем
    //- * реализовать перезапуск игры
    //
    //Формат сдачи: ссылка на гитхаб проект
}

