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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import movierecsys.be.Movie;
import movierecsys.bll.exception.MovieRecSysException;
import movierecsys.gui.model.MovieModel1;

/**
 * FXML Controller class
 *
 * @author Bruger
 */
public class MovieOptionRecViewController implements Initializable
{

    @FXML
    private AnchorPane MovieBorderPane;
    @FXML
    private Button btnXMovieBack;
    @FXML
    private Button btnAddMovieOption;
    @FXML
    private Button btnRemoveMovieOption;
    @FXML
    private TextField txtMovieAdd;
    @FXML
    private Label lblMovieOption;

    private MovieModel1 modelOption;
    @FXML
    private TextField txtAddMovieYear;
    @FXML
    private Label lblAddSuccess;
    @FXML
    private Label lblUpdateSuccess;
    @FXML
    private Label lblDeleteSuccess;
    @FXML
    private Button btnUpdateMovie;
    @FXML
    private TextField txtUpdateTitle;
    @FXML
    private TextField txtUpdateYear;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    @FXML
    private void xMovieBack(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) MovieBorderPane.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/movierecsys/gui/view/MovieRecView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    private void btnaddMovie(ActionEvent event) throws IOException, MovieRecSysException
    {
        String movieTitle = txtMovieAdd.getText();
        int movieYear = Integer.parseInt(txtAddMovieYear.getText());
        modelOption.createMovie(movieYear, movieTitle);
        lblAddSuccess.setText("The movie has been successfully added");
    }

    @FXML
    private void btnRemoveMovie(ActionEvent event)
    {
        modelOption.deleteMovie();
        lblDeleteSuccess.setText("The movie has been successfully removed");
        
    }


    void setModelOption(MovieModel1 model)
    {
        this.modelOption = model;
        lblMovieOption.setText(model.getSelected().getTitle());
    }

    @FXML
    private void handleUpdate(ActionEvent event) {
        modelOption.updateMovie(Integer.parseInt(txtUpdateYear.getText()), txtUpdateTitle.getText());
        lblUpdateSuccess.setText("The movie has been successfully updated");
    }

}
