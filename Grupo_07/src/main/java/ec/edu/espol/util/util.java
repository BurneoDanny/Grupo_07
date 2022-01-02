
package ec.edu.espol.util;

import ec.edu.espol.grupo_07.App;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class util {
    
    private static final Image circle = new Image(App.class.getResourceAsStream("images/circulo.png"));
    private static final Image equis = new Image(App.class.getResourceAsStream("images/x.png"));

    public static Image getCircle() {
        return circle;
    }

    public static Image getEquis() {
        return equis;
    }
    
    
    
    public static boolean isImageEqual(Image firstImage, Image secondImage){
    // Prevent `NullPointerException`
    if(firstImage != null && secondImage == null) return false;
    if(firstImage == null) return secondImage == null;

    // Compare images size
    if(firstImage.getWidth() != secondImage.getWidth()) return false;
    if(firstImage.getHeight() != secondImage.getHeight()) return false;

    // Compare images color
    for(int x = 0; x < firstImage.getWidth(); x++){
        for(int y = 0; y < firstImage.getHeight(); y++){
            int firstArgb = firstImage.getPixelReader().getArgb(x, y);
            int secondArgb = secondImage.getPixelReader().getArgb(x, y);

            if(firstArgb != secondArgb) return false;
        }
    }

        return true;
    }  
    
    
    public static boolean isThereImage(Image image){
        return image != null;
    }
    
    public static boolean isImageViewEqual(ImageView firstImage, ImageView secondImage){
    // Prevent `NullPointerException`
    if(firstImage != null && secondImage == null) return false;
    if(firstImage == null) return secondImage == null;

    // Compare images size
    if(firstImage.getImage().getWidth() != secondImage.getImage().getWidth()) return false;
    if(firstImage.getImage().getHeight() != secondImage.getImage().getHeight()) return false;

    // Compare images color
    for(int x = 0; x < firstImage.getImage().getWidth(); x++){
        for(int y = 0; y < firstImage.getImage().getHeight(); y++){
            int firstArgb = firstImage.getImage().getPixelReader().getArgb(x, y);
            int secondArgb = secondImage.getImage().getPixelReader().getArgb(x, y);

            if(firstArgb != secondArgb) return false;
        }
    }
        return true;
    }
            
}
