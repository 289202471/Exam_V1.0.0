package com.zong.UI.view;

import com.zong.domain.Paper;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class PaperCatalog {
    private String[] headers;
    public static  Object[][] values;

    public static DefaultTableModel getPaperCatalogView(Paper paper){
        Vector vData = new Vector();
        Vector vName = new Vector();
        vName.add(PaperCatalogItem.NO);
        vName.add(PaperCatalogItem.GOAL);
        vName.add(PaperCatalogItem.ACTUAL);
        vName.add(PaperCatalogItem.STATE);

        for(int i=0;i<paper.getAllQuestions().size();i++){
            Vector vRow = new Vector();
            vRow.add(i+1);
            vRow.add(paper.getAllQuestions().get(i).getScore());
            vRow.add(0);
            vRow.add(PaperCatalogItem.UNANSWERED);
            vData.add(vRow.clone());
        }

        DefaultTableModel model = new DefaultTableModel(vData, vName);
        return model;
    }

}
