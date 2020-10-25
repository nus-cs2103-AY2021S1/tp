package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.recipe.RecipeImage;

/**
 * Parse user inputted image path.
 */
public class ImageParser {
    /**
     * Parses a String of image path
     * check validity
     * convert them to Image object to be returned
     * @param imagePath String of 1 or more instructions
     * @return ArrayList of String objects of the ingredients in the parameter
     * @throws ParseException
     */
    public RecipeImage parse(String imagePath) throws IOException, URISyntaxException {
        requireNonNull(imagePath);
        String filename = "";
        if (imagePath.length() > 4 && imagePath.substring(0, 4).equals("http")) {
            for (int i = imagePath.length() - 1; i >= 0; i--) {
                if (imagePath.charAt(i) == '/') {
                    filename = imagePath.substring(i + 1);
                    break;
                }
            }
            URL url = new URL(imagePath);
            InputStream in = new BufferedInputStream(url.openStream());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int n = 0;
            while (-1 != (n = in.read(buf))) {
                out.write(buf, 0, n);
            }
            out.close();
            in.close();
            byte[] response = out.toByteArray();

            //imagePath = AddRecipeCommandParser.class.getResource("/images").getPath() + "/" + filename;
            imagePath = "data/" + filename;

            //imagePath = getPathsFromResourceJAR("data") + "/" + filename;
            FileOutputStream fos = new FileOutputStream(imagePath);
            //URL jarLocation = this.getClass().getProtectionDomain().getCodeSource().getLocation();
            //URL data = new URL(jarLocation, "data/" + filename);
            //imagePath = data.toURI().getPath();
            fos.write(response);
            fos.close();
        }
        //return new RecipeImage("images/" + filename);
        return new RecipeImage(imagePath);
    }

    // Get all paths from a folder that inside the JAR file
    private List<Path> getPathsFromResourceJar(String folder)
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
