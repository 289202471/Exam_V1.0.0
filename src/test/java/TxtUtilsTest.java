import com.zong.common.FilePath;
import com.zong.utils.TxtUtils;

import java.util.*;

public class TxtUtilsTest {
    public static void main(String[] args) {
        List<String> result=new ArrayList<String>();

        try {
            result=TxtUtils.getQuestions(FilePath.PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(int i=0;i<result.size();i++){
            System.out.println(result.get(i));
        }
    }
}
