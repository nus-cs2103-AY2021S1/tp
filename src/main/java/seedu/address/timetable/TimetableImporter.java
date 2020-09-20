package seedu.address.timetable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class TimetableImporter {
    private static final String TIMETABLE = "https://nusmods.com/timetable/sem-1/share?CS2100=TUT:01,LAB:11,LEC:1"
            + "&CS2101=&CS2103T=LEC:G16&CS2105=TUT:14,LEC:1&EC1301=TUT:S28,LEC:1&IS1103=";
    private static final String TEST = "https://api.nusmods.com/v2/2020-2021/modules/CS2100.json";

    public static void main(String[] args) throws IOException {
        URL url = new URL(TEST);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        int responseCode = conn.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        }
        String inline = "";
        Scanner sc = new Scanner(url.openStream());
        while(sc.hasNext()) {
            inline += sc.nextLine();
        }
        System.out.println(inline);
        sc.close();
    }
}
