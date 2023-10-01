import java.util.ArrayList;
import java.util.Arrays;

public class RuAlphabetGame extends AbstractGame {
    @Override
    ArrayList<String> generateCharList() {
        return new ArrayList<>(Arrays.asList("а","б","в","г","д","е","ё","ж","з","и","к","л","м","н","й","о","п","р","с",
                "т","у","ф","х","ц","ч","ш","щ","ь","ъ","э","ю","я","ы"));
    }
}
