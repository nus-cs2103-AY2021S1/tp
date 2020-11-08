package seedu.address.logic.parser.data;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.recipe.RecipeImage;

/**
 * Parse user inputted image path.
 */
public class ImageParser {
    private static final String DIRECTORY_NAME = "data/";

    /**
     * Parses a String of image path to
     * check validity and
     * convert them to RecipeImage object to be returned.
     *
     * @param imagePath String of image path (local or url).
     * @return RecipeImage of String objects of the ingredients in the parameter.
     * @throws ParseException
     */
    public RecipeImage parse(String imagePath) throws ParseException, IOException, URISyntaxException {
        String fileName = "";
        String imageLocalPath = imagePath;
        try {
            if (imagePath.length() > 4 && imagePath.substring(0, 4).equals("http")) {
                for (int i = imagePath.length() - 1; i >= 0; i--) {
                    if (imagePath.charAt(i) == '/') {
                        fileName = imagePath.substring(i + 1);
                        break;
                    }
                }
                URL url = new URL(imagePath);
                imagePath = DIRECTORY_NAME + fileName;
                File directory = new File(DIRECTORY_NAME);
                if (!directory.exists()) {
                    directory.mkdir();
                }
                FileOutputStream fos = new FileOutputStream(imagePath);
                imageLocalPath = getImageFromUrl(url, fileName, fos);
            }
        } catch (Exception e) {
            e.printStackTrace();
            imageLocalPath = "images/default.jpg";
        }
        if (imagePath.length() == 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RecipeImage.MESSAGE_CONSTRAINTS));
        }
        return new RecipeImage(imageLocalPath);
    }

    private String getImageFromUrl(URL url, String fileName, FileOutputStream fos)
            throws ExecutionException, InterruptedException {
        String imagePathDefault = "images/default.jpg";
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setConnectTimeout(5000);
                con.setReadTimeout(5000);
                InputStream responseStream = con.getInputStream();
                URL jarLocation = this.getClass().getProtectionDomain().getCodeSource().getLocation();
                URL data = new URL(jarLocation, DIRECTORY_NAME + fileName);

                ByteArrayOutputStream out = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                int n = 0;
                while (-1 != (n = responseStream.read(buf))) {
                    out.write(buf, 0, n);
                }
                out.close();
                responseStream.close();
                fos.write(out.toByteArray());
                fos.close();

                return ("file://" + data.toURI().getPath());
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
        String imagePath = completableFuture.get();
        if (imagePath != null) {
            return imagePath;
        }
        return imagePathDefault;
    }

    /**
     * Get all paths from a folder that inside the JAR file.
     *
     * @param folder Name of folder.
     * @return List of paths from a folder.
     */
    public List<Path> getPathsFromResourceJar(String folder)
            throws URISyntaxException, IOException {

        List<Path> result;

        // get path of the current running JAR
        String jarPath = getClass().getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .toURI()
                .getPath();
        System.out.println("JAR Path :" + jarPath);

        // file walks JAR
        URI uri = URI.create("jar:file:" + jarPath);
        try (FileSystem fs = FileSystems.newFileSystem(uri, Collections.emptyMap())) {
            result = Files.walk(fs.getPath(folder))
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        }
        return result;
    }
}
