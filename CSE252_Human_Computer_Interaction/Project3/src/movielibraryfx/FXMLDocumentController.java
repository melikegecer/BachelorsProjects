package movielibraryfx;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Set;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class FXMLDocumentController implements Initializable {

   @FXML
   private Button seeWatchListButton;
   @FXML
   private ComboBox movieMenuCombo;
   @FXML
   private TableView<Movie> movieShowingTableView;
   @FXML
   private ObservableList<String> movieTypeList;
   @FXML
   private ObservableList<Movie> movieShowingList;
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

   private Set<String> hs = new HashSet<>();
   private ArrayList<Movie> movieList = new ArrayList<>();
   //this array will go to secondFrame (watched list)
   private static ObservableList<Movie> secondList = FXCollections.observableArrayList();
   private static int nameWidth = 0;
   private static int typesWidth = 0;
   private static int directorsWidth = 0;

   @Override
   @SuppressWarnings("unchecked")
   public void initialize(URL url, ResourceBundle rb) {
      movieTypeList = FXCollections.observableArrayList();
      movieShowingList = FXCollections.observableArrayList();
      seeWatchListButton.setOnAction(this::seeWatchListButtonAction);

      readFile();

      setColumns();

      movieShowingTableView.setItems(movieShowingList);

      //for type combo
      ArrayList l = new ArrayList<>();
      l.addAll(hs);
      Collections.sort(l);
      movieTypeList.add("All");
      movieTypeList.addAll(l);
      movieMenuCombo.setItems(movieTypeList);
      movieMenuCombo.setOnAction((event) -> this.mediaMenuComboAction((ActionEvent) event));

      movieShowingTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
         @Override
         public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
            if (movieShowingTableView.getSelectionModel().getSelectedItem() != null) {
               TableViewSelectionModel selectionModel = movieShowingTableView.getSelectionModel();
               ObservableList selectedCells = selectionModel.getSelectedCells();
               TablePosition tablePosition = (TablePosition) selectedCells.get(0);
               Object val = tablePosition.getTableColumn().getCellData(newValue);
            }
         }
      });
   }

   //read the related file and gets movies
   private void readFile() {
      try {
         File f = new File("movies.txt");
         Scanner s = new Scanner(f);
         while (s.hasNextLine()) {
            String[] name = s.nextLine().split("-");
            String[] types = s.nextLine().split("-");
            String[] year = s.nextLine().split("-");
            String[] directors = s.nextLine().split("-");
            String[] iconid = s.nextLine().split("-");
            Movie m = new Movie(name[1], year[1], iconid[1], types[1], directors[1], false);
            movieShowingList.add(m);
            movieList.add(m);
            if (m.getName().length() >= nameWidth) {
               nameWidth = m.getName().length();
            }
            if (m.getDirectors().length() >= directorsWidth) {
               directorsWidth = m.getDirectors().length();
            }
            if (m.getTypes().length() >= typesWidth) {
               typesWidth = m.getTypes().length();
            }
            fillTypeList(m.getTypes());
            s.nextLine();
         }
      } catch (FileNotFoundException ex) {
         System.out.println("FileNotFoundException: " + ex.getMessage());
      }

      nameWidth *= 6;
      directorsWidth *= 7;
      typesWidth *= 7;
   }

   //columns are set in this method
   private void setColumns() {
      nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
      yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
      typesColumn.setCellValueFactory(new PropertyValueFactory<>("types"));
      directorColumn.setCellValueFactory(new PropertyValueFactory<>("directors"));

      watchedColumn.setCellValueFactory(new PropertyValueFactory<>("watched"));
      watchedColumn.setCellFactory(CheckBoxTableCell.forTableColumn(new Callback<Integer, ObservableValue<Boolean>>() {
         @Override
         public ObservableValue<Boolean> call(Integer param) {
            return movieShowingList.get(param).getWatchedProperty();
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
      nameColumn.setPrefWidth(nameWidth);
      directorColumn.setPrefWidth(directorsWidth);
      typesColumn.setPrefWidth(typesWidth);
   }

   //updates the list of showed movies
   @FXML
   private void mediaMenuComboAction(ActionEvent event) {
      movieShowingList.clear();
      String type = movieMenuCombo.getSelectionModel().getSelectedItem().toString().toLowerCase();
      if (type.contains("all")) {
         for (Movie m : movieList) {
            movieShowingList.add(m);
         }
      } else {
         for (Movie m : movieList) {
            String mm = m.getTypes().toLowerCase();
            if (mm.contains(type)) {
               movieShowingList.add(m);
            }
         }
      }
      movieShowingTableView.setItems(movieShowingList);
      setColumns();
   }

   //opens the second frame and shows watched movies
   @FXML
   private void seeWatchListButtonAction(ActionEvent event) {
      Parent root;
      secondList.clear();
      for (Movie m : movieList) {
         if (m.getWatched()) {
            secondList.add(m);
         }
      }
      try {
         root = FXMLLoader.load(getClass().getResource("SecondFrame.fxml"));
         Stage stage = new Stage();
         stage.setTitle("Media Library");
         stage.resizableProperty().set(false);
         stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
         int w = nameWidth + directorsWidth + typesWidth + 165;
         Scene sc = new Scene(root, w, 450);
         stage.setScene(sc);
         stage.show();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   //this method is just to fill the comboBox which contains the types of movies
   private void fillTypeList(String s) {
      String[] t = s.split(",");
      for (int i = 0; i < t.length; i++) {
         hs.add(t[i]);
      }
   }

   public static ObservableList<Movie> getSecondList() {
      return secondList;
   }

   public static int getNameWidth() {
      return nameWidth;
   }

   public static int getDirectorsWidth() {
      return directorsWidth;
   }

   public static int getTypesWidth() {
      return typesWidth;
   }

}
