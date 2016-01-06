package main;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Michal on 2016-01-06.
 */
public class TestDataClass {

    public TestDataClass(Model model) throws SQLException, ClassNotFoundException {
        model.createDB();
        addExampleQuest(model);
    }

    private void addExampleQuest(Model model) throws SQLException, ClassNotFoundException {

        ArrayList<String> al = new ArrayList<>();
        al.add("assasasa");
        al.add("assas3443asa");
        al.add("assaresasa");
        al.add("assaresasa");

        model.insertToDB("Pyt1", al, 1);

        ArrayList<String> al2 = new ArrayList<>();
        al2.add("dssd");
        al2.add("fgfg");
        al2.add("fgfg");
        al2.add("jhkh");
        al2.add("jhkh");

        model.insertToDB("Pyt2", al2, 2);


        ArrayList<String> al3 = new ArrayList<>();
        al3.add("nmmn");
        al3.add("nmmnmn");
        al3.add("jhjh");

        model.insertToDB("Pyt3", al3, 3);


        ArrayList<String> al4 = new ArrayList<>();
        al4.add("nmmn");
        al4.add("nmmnmn");

        model.insertToDB("Pyt4", al4, 1);
    }

}
