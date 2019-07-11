package movielibraryfx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class SecondFrameController implements Initializable {

   @FXML
   private ObservableList<Movie> list;
   @FXML
   private TableView<Movie> movieTableView;
   @FXML
   private TableColumn posterColumn;
   @FXML
   private TableColumn<Movie, String> yearColumn;
   @FXML
   private TableColumn<Movie, String> typesColumn;
   @FXML
   private TableColumn<Movie, String> directorColumn;
   @FXML
   private TableColumn<Movie, String> nameColumn;
   @FXML
   private TableColumn<Movie, Boolean> watchedColumn;

   @Override
   public void initialize(URL url, ResourceBundle rb) {
      list = FXMLDocumentController.getSecondList();
      movieTableView.setItems(list);
      setColumns();
   }

   private void setColumns() {
      nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
      yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
      typesColumn.setCellValueFactory(new PropertyValueFactory<>("types"));
      directorColumn.setCellValueFactory(new PropertyValueFactory<>("directors"));

      watchedColumn.setCellValueFactory(new PropertyValueFactory<>("watched"));
      watchedColumn.setCellFactory(CheckBoxTableCell.forTableColumn(new Callback<Integer, ObservableValue<Boolean>>() {
         @Override
         public ObservableValue<Boolean> call(Integer param) {
            return list.get(param).getWatchedProperty();
         }
      }));

      posterColumn.setCellValueFactory(new PropertyValueFactory("poster"));

      posterColumn.setCellFactory(new Callback<TableColumn<Movie, MoviePoster>, TableCell<Movie, MoviePoster>>() {
         @Override
         public TableCell<Movie, MoviePoster> call(TableColumn<Movie, MoviePoster> param) {
            TableCell<Movie, MoviePoster> cell = new TableCell<Movie, MoviePoster>() {
               ImageView imageview = new ImageView();

               @Override
               public void updateItem(MoviePoster item, boolean empty) {
                  if (item != null) {
                     HBox box = new HBox();
                     box.setSpacing(10);
                     VBox vbox = new VBox();
                     try {
                        imageview.setFitHeight(100);
                        imageview.setFitWidth(100);
                        imageview.setImage(new Image(MovieLibraryFx.class.getResource("/resources/" + item.getIconPath() + ".jpg").toString()));
                        box.getChildren().addAll(imageview, vbox);
                        setGraphic(box);
                     } catch (Exception ex) {
                        System.out.println("ex: " + ex.getMessage());
                     }
                  }
               }
            };
            return cell;
         }
      });
      nameColumn.setPrefWidth(FXMLDocumentController.getNameWidth());
      directorColumn.setPrefWidth(FXMLDocumentController.getDirectorsWidth());
      typesColumn.setPrefWidth(FXMLDocumentController.getTypesWidth());
   }

}
