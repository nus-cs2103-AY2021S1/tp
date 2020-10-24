package seedu.address.ui;

import java.util.ArrayList;
import java.util.List;

public class TemplateList {
    public static Template pushUp = new Template("push up", "half an hour", 100);
    public static Template running = new Template("running", "half an hour", 180);
    public static Template sitUp = new Template("sit up", "half an hour", 100);
    public static  ArrayList<Template> list = new ArrayList<Template>();

    public static List<Template> getList() {
        return list;
    }

    public static Template getTemp(String name) {
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
}
