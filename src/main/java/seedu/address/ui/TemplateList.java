package seedu.address.ui;

import java.util.ArrayList;
import java.util.List;

public class TemplateList {
    public static Template pushUp = new Template("push up", "half an hour", 100);
    public static Template running = new Template("running", "half an hour", 180);
    public static Template sitUp = new Template("sit up", "half an hour", 100);

    public static List<Template> getList() {
        ArrayList<Template> list = new ArrayList<Template>();
        list.add(pushUp);
        list.add(running);
        list.add(sitUp);
        return list;
    }

    public static Template getTemp(String name) {
        switch (name){
            case "PUSH_UP" : return pushUp;
            case "RUNNING" : return running;
            case "SIT_UP" : return sitUp;
        }
        return pushUp;
    }
}
