/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import movierecsys.be.Movie;
import movierecsys.bll.exception.MovieRecSysException;
import movierecsys.gui.model.MovieModel;

/**
 *
 * @author pgn
 */
public class MovieRecController implements Initializable {

    /**
     * The TextField containing the URL of the targeted website.
     */
    private TextField txtMovieSearcjh;

    /**
     * The TextField containing the query word.
     */
    @FXML
    private ListView<Movie> lstMovies;

    private MovieModel movieModel;
    @FXML
    private Button btnSearch;
    @FXML
    private TextField txtMovieSearch;
    @FXML
    private BorderPane MainBorderPane;
    @FXML
    private Button btnSuperBadMain;
    @FXML
    private Button btnBadMain;
    @FXML
    private Button btnNeutralMain;
    @FXML
    private Button btnGoodMain;
    @FXML
    private Button btnSuperGoodMain;
    @FXML
    private Button btnRatingOptionScreen;
    @FXML
    private Button btnMovieOptionsScreen;

    public MovieRecController() {
        try {
            movieModel = new MovieModel();
        } catch (MovieRecSysException ex) {
            displayError(ex);
            System.exit(0);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lstMovies.setItems(movieModel.getMovies());

    }

    private void displayError(MovieRecSysException ex) {
        System.out.println(ex.getMessage());
        ex.printStackTrace();
    }

    @FXML
    private void btnSearchEnter(ActionEvent event) throws MovieRecSysException {
        String searchInput = txtMovieSearch.getText();
        lstMovies.setItems(movieModel.getSearch(searchInput));
    }

    @FXML
    private void txtEnterSearch(ActionEvent event) throws MovieRecSysException {
        String searchInput = txtMovieSearch.getText();
        lstMovies.setItems(movieModel.getSearch(searchInput));
    }

    @FXML
    private void mainSuperBad(ActionEvent event)
    {
    }

    @FXML
    private void badMain(ActionEvent event)
    {
    }

    @FXML
    private void neutralMain(ActionEvent event)
    {
    }

    @FXML
    private void goodMain(ActionEvent event)
    {
    }

    @FXML
    private void superGoodMain(ActionEvent event)
    {
    }

    @FXML
    private void ratingOptionScreenEnter(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) MainBorderPane.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/movierecsys/gui/view/RatingRecView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene (scene);
    }

    @FXML
    private void movieOptionScreenEnter(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) MainBorderPane.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/movierecsys/gui/view/MovieOptionRecView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene (scene);
    }

}
