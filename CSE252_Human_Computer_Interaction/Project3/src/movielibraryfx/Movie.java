package movielibraryfx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Movie {

   private SimpleStringProperty name;
   private SimpleStringProperty year;
   private ObjectProperty poster;
   private SimpleStringProperty types;
   private SimpleStringProperty directors;
   private SimpleBooleanProperty watched;

   public Movie() {
      this.name = new SimpleStringProperty();
      this.year = new SimpleStringProperty();
      this.poster = new SimpleObjectProperty();
      this.types = new SimpleStringProperty();
      this.directors = new SimpleStringProperty();
      this.watched = new SimpleBooleanProperty();
   }

   public Movie(String name, String year, String iconPath, String types, String directors, boolean watched) {
      this();
      setName(name);
      setYear(year);
      setPoster(new MoviePoster(iconPath));
      setTypes(types);
      setDirectors(directors);
      setWatched(watched);
   }

   public void setName(String name) {
      this.name.set(name);
   }

   public String getName() {
      return name.get();
   }

   public SimpleStringProperty getNameProperty() {
      return name;
   }

   public void setYear(String year) {
      this.year.set(year);
   }

   public String getYear() {
      return year.get();
   }

   public SimpleStringProperty getYearProperty() {
      return year;
   }

   public void setTypes(String types) {
      this.types.set(types);
   }

   public String getTypes() {
      return types.get();
   }

   public SimpleStringProperty getTypesProperty() {
      return types;
   }

   public void setDirectors(String directors) {
      this.directors.set(directors);
   }

   public String getDirectors() {
      return directors.get();
   }

   public SimpleStringProperty getDirectorsProperty() {
      return directors;
   }

   public void setWatched(boolean watched) {
      this.watched.set(watched);
   }

   public boolean getWatched() {
      return watched.get();
   }

   public SimpleBooleanProperty getWatchedProperty() {
      return watched;
   }

   public void setPoster(MoviePoster poster) {
      this.poster.set(poster);
   }

   public Object getPoster() {
      return this.poster.get();
   }

   public ObjectProperty posterProperty() {
      return poster;
   }

}
