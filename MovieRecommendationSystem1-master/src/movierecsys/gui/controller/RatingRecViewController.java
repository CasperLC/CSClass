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
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import movierecsys.gui.model.MovieModel1;

/**
 * FXML Controller class
 *
 * @author Bruger
 */
public class RatingRecViewController implements Initializable
{

    @FXML
    private AnchorPane RatingBorderPane;
    @FXML
    private Button btnXRatingBack;
    @FXML
    private Button btnChangeRating;
    @FXML
    private Button btnDeleteRating;
    @FXML
    private Button btnRatingSuperBadOption;
    @FXML
    private Button btnRatingBadOption;
    @FXML
    private Button btnRatingNeutralOption;
    @FXML
    private Button btnRatingGoodOption;
    @FXML
    private Button btnRatingSuperGoodOption;
    @FXML
    private Label lblChangeRating;

    
    private MovieModel1 model;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void xRatingBack(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) RatingBorderPane.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/movierecsys/gui/view/MovieRecView.fxml"));
        Scene scene = new Scene (root);
        stage.setScene(scene);
        
    }
      
    @FXML
    private void btnChangeRating(ActionEvent event)
    {
    }

    @FXML
    private void btndeleteRating(ActionEvent event)
    {
    }

    @FXML
    private void superBadRatingOption(ActionEvent event)
    {
    }

    @FXML
    private void ratingBadOption(ActionEvent event)
    {
    }

    @FXML
    private void ratingNeutralOption(ActionEvent event)
    {
    }

    @FXML
    private void ratingGoodOption(ActionEvent event)
    {
    }

    @FXML
    private void ratingSuperGoodOption(ActionEvent event)
    {
    }

    void setMovie()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void setModel(MovieModel1 model)
    {
        this.model = model;
        lblChangeRating.setText(model.getSelected().getTitle());
    }
    
}
