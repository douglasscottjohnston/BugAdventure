package View;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.IOException;

public abstract class ResourceManager {
    private static final String RESOURCE_PATH = "View/Resources/";
    private static final String CHARACTERS_PATH = RESOURCE_PATH + "Characters/";
    private static final String MONSTERS_PATH = RESOURCE_PATH + "Monsters/";
    private static final String ITEMS_PATH = RESOURCE_PATH + "Items/";
    private enum IMAGE_FORMATS {
        PNG(".png"),
        JPEG(".jpeg"),
        JPG(".jpg"),
        GIF(".gif");

        private String myFormat;
        IMAGE_FORMATS(final String theFormat) {
            myFormat = theFormat;
        }

        @Override
        public String toString() {
            return myFormat;
        }
    }

    public static Image getCharacterImage(final String theName) {
        System.out.println(System.getProperty("user.dir"));
        return getImageWithoutExtention(CHARACTERS_PATH + theName);
    }

    public static Image getMonsterImage(final String theName) {
        return getImageWithoutExtention(MONSTERS_PATH + theName);
    }

    public static Image getItemImage(final String theName) {
        return getImageWithoutExtention(ITEMS_PATH + theName.replaceAll("\\s+",""));
    }

    private static Image getImage(final String thePath) throws IOException {
        Image image = null;

        FileInputStream fis = new FileInputStream(thePath);
        image = new Image(fis);
        fis.close();


        return image;
    }

    private static Image getImageWithoutExtention(final String thePath) {

//        for(IMAGE_FORMATS format : IMAGE_FORMATS.values()) {
//            try {
//                System.out.println(thePath + format);
//                image = getImage(thePath + format);
//                return image;
//            } catch (IOException e) {
//                continue;
//            }
//        }

        Image image = new Image(thePath + ".png");
        return image;
    }
}