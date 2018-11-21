/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.gui.controller;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import movierecsys.be.Movie;
import movierecsys.bll.exception.MovieRecSysException;
import movierecsys.gui.model.MovieModel;
import movierecsys.gui.model.MovieModel1;

/**
 *
 * @author pgn
 */
public class MovieRecController implements Initializable
{

    /**
     * The TextField containing the URL of the targeted website.
     */
    private TextField txtMovieSearcjh;

    /**
     * The TextField containing the query word.
     */
    @FXML
    private ListView<Movie> lstMovies;


    @FXML
    private Button btnSearch;
    @FXML
    private TextField txtMovieSearch;
    @FXML
    private BorderPane MainBorderPane;
    @FXML
    private Button btnRatingOptionScreen;
    @FXML
    private Button btnMovieOptionsScreen;

    MovieModel1 model;
    @FXML
    private Label lblLoggedInUser;
    
    public MovieRecController() {
        try {
            model = new MovieModel1();
        } catch (MovieRecSysException ex) {
            displayError(ex);
            System.exit(0);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lstMovies.setItems(model.getMovies());
        lblLoggedInUser.setText("Logged in as:"+model.getUsername());
        try {
            model = new MovieModel1();
        } catch (MovieRecSysException ex) {
            Logger.getLogger(MovieRecController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void displayError(MovieRecSysException ex)
    {
        System.out.println(ex.getMessage());
        ex.printStackTrace();
    }

    @FXML
    private void btnSearchEnter(ActionEvent event) throws MovieRecSysException
    {
        String searchInput = txtMovieSearch.getText();
        lstMovies.setItems(model.getSearch(searchInput));
    }

    @FXML
    private void txtEnterSearch(ActionEvent event) throws MovieRecSysException
    {
        String searchInput = txtMovieSearch.getText();
        lstMovies.setItems(model.getSearch(searchInput));
    }


    @FXML
    private void ratingOptionScreenEnter(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) MainBorderPane.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/movierecsys/gui/view/RatingRecView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        RatingRecViewController ratingRecViewController = loader.getController();
        model.setSelected(lstMovies.getSelectionModel().getSelectedItem());
        ratingRecViewController.setModel(model);
    }

    @FXML
    private void movieOptionScreenEnter(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) MainBorderPane.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/movierecsys/gui/view/MovieOptionRecView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        MovieOptionRecViewController optionRecViewController = loader.getController();
        model.setSelected(lstMovies.getSelectionModel().getSelectedItem());
        optionRecViewController.setModelOption(model);
    }

}
