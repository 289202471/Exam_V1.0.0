import com.zong.common.FilePath;
import com.zong.utils.WordUtils;

public class WordUtilsTest {
    public static void main(String[] args) {
        String value=null;
        try {
           value = WordUtils.readWord(FilePath.PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(value);
    }
}
