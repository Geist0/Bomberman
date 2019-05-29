package Hero;

import javafx.scene.image.Image;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ImageLoader {
    private Map<String, Image> map = new HashMap<>();
    private static ImageLoader me ;
    private static final String ROOT_FOLDER="/sprite/" ;

    public static ImageLoader get() {

        if ( me == null) { me = new ImageLoader() ; }
        return me ;
    }

    public Image load(String lien) {
        if (map.get(lien) == null ) {
            System.out.println(lien);
            InputStream is= getClass().getResourceAsStream("/"+lien) ;
            map.put(lien,new Image(is));
        }
        return map.get(lien) ;
    }
}
