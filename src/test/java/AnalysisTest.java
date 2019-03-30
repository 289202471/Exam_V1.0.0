import com.zong.analysis.Analysis;
import com.zong.common.FilePath;
import com.zong.utils.PdfUtils;
import com.zong.utils.TxtUtils;

import java.util.*;

public class AnalysisTest {
    public static void main(String[] args) {
        Vector v=null;
        try {
//            v=Analysis.getQuestionsAndAnswer(TxtUtils.getQuestions(FilePath.PATH));
//              v=Analysis.getQuestionsAndAnswerbyType(TxtUtils.getQuestions(FilePath.PATH));
            v=Analysis.getQuestionsAndAnswerbyType(PdfUtils.readPdfToTxt(FilePath.PATH));
        } catch (Exception e) {

            e.printStackTrace();
        }
        List<String> choices=(List<String>)v.get(0);
        List<String> trueorfalse=(List<String>)v.get(1);
        List<String> multiple=(List<String>)v.get(2);
        List<ArrayList<String>> as=(List<ArrayList<String>>)v.get(3);
        List<String> ra=(List<String>)v.get(4);

        for(int i=0;i< choices.size();i++){
            System.out.println(choices.get(i));
            for(int j=0;j<as.get(i).size();j++){
                System.out.println(as.get(i).get(j));

            }
            System.out.println(ra.get(i));
        }

        int choiceNum=choices.size();
        for(int i=0;i< trueorfalse.size();i++){
            System.out.println(trueorfalse.get(i));
            for(int j=0;j<as.get(i+choiceNum).size();j++){
                System.out.println(as.get(i+choiceNum).get(j));

            }
            System.out.println(ra.get(i+choiceNum));
        }

        int trueNum=trueorfalse.size();
        for(int i=0;i< multiple.size();i++){
            System.out.println(multiple.get(i));
            for(int j=0;j<as.get(i+trueNum+choiceNum).size();j++){
                System.out.println(as.get(i+trueNum+choiceNum).get(j));

            }
            System.out.println(ra.get(i+trueNum+choiceNum));
        }
    }
}
