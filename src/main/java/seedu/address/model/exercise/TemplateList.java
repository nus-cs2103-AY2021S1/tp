package seedu.address.model.exercise;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import seedu.address.logic.parser.AddTemplateCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;

public class TemplateList {
    public static Template pushUp = new Template("push up", "half an hour", 100);
    public static Template running = new Template("running", "half an hour", 180);
    public static Template sitUp = new Template("sit up", "half an hour", 100);
    public static  ArrayList<Template> list = new ArrayList<Template>();

    public static List<Template> getList() {
        return list;
    }

    public static Template getTemp(String name) {
        load();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(name)) {
                return list.get(i);
            }
        }
        return null;
    }

    public static void addTemplate(Template template) {
        list.add(template);
    }


    public static void load() {
        String filePath = "data/template.txt";
        File f = new File(filePath);
        if (f.exists()) {
            try {
                Scanner s = new Scanner(f);
                readTask(f);
            } catch (FileNotFoundException e) {
                new File("data").mkdir();
            }
        } else {
            new File("data").mkdir();
        }
    }

    public static void readTask(File f) {
        list = new ArrayList<>();
        AddTemplateCommandParser parser = new AddTemplateCommandParser();
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String string = s.nextLine();
                list.add(parser.parseTemp("create " + string));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkEqual(Template template) {
        for (int i = 0; i < list.size(); i++) {
            if (template.equals(list.get(i))) {
                return true;
            }
        }

        return false;
    }
}
