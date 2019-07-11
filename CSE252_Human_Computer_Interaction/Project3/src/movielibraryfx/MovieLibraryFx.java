package movielibraryfx;

import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MovieLibraryFx extends Application {

   @Override
   public void start(Stage stage) throws Exception {
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      double width = screenSize.getWidth();
      double height = screenSize.getHeight();
      stage.setWidth(width-50);
      stage.setHeight(height-50);
      
      Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

      Scene scene = new Scene(root);
      stage.setTitle("Media Library");
      stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
      stage.setScene(scene);
      stage.show();
   }

   public static void main(String[] args) {
      launch(args);
   }

}
