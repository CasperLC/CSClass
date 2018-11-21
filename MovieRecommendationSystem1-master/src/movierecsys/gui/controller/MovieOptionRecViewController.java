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
import movierecsys.dal.MovieDAO;
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
    private TextField txtMovieYear;
    @FXML
    private Label lblMovieOption;

    private MovieModel1 modelOption;

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
    private void btnaddMovie(ActionEvent event) throws IOException
    {
        String movieTitle = txtMovieAdd.getText();
        int movieYear = Integer.parseInt(txtMovieYear.getText());
        MovieDAO moviedao = new MovieDAO();
        moviedao.createMovie(movieYear, movieTitle);

    }

    @FXML
    private void btnRemoveMovie(ActionEvent event)
    {

    }

    @FXML
    private void movieAddText(ActionEvent event)
    {

    }

    @FXML
    private void movieYearText(ActionEvent event)
    {

    }

    void setModelOption(MovieModel1 model)
    {
        this.modelOption = model;
        lblMovieOption.setText(model.getSelected().getTitle());
    }

}
