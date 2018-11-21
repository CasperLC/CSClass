/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.gui.controller;

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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import movierecsys.be.Rating;
import movierecsys.gui.model.MovieModel1;

/**
 * FXML Controller class
 *
 * @author Bruger
 */
public class RatingRecViewController implements Initializable {

    @FXML
    private AnchorPane RatingBorderPane;
    @FXML
    private Button btnXRatingBack;
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
    private Label lblChangeRating;

    private MovieModel1 model;
    @FXML
    private Label lblSelectedMovie;
    @FXML
    private Label lblSuccessRated;
    @FXML
    private Label lblSuccessDeletedRating;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void xRatingBack(ActionEvent event) throws IOException {
        Stage stage = (Stage) RatingBorderPane.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/movierecsys/gui/view/MovieRecView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }


    @FXML
    private void btndeleteRating(ActionEvent event) {
        try {
            if (model.specificRating() != null) {
                model.deleteRating();
                lblSuccessDeletedRating.setText("Your rating of this movie was deleted");
            } else {
                lblSuccessDeletedRating.setText("You have not rated this movie yet");
            }
        } catch (IOException ex) {
            Logger.getLogger(RatingRecViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void superBadRatingOption(ActionEvent event) {
        try {
            if (model.specificRating() == null) {
                model.rateMovie(-5);
                lblSuccessRated.setText("You gave this movie af 1 out of 5!");
                lblSuccessDeletedRating.setText("");
            } else {
                lblSuccessRated.setText("You have already rated this movie");
            }
        } catch (IOException ex) {
            Logger.getLogger(RatingRecViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ratingBadOption(ActionEvent event) {
        try {
            if (model.specificRating() == null) {
                model.rateMovie(-3);
                lblSuccessRated.setText("You gave this movie af 2 out of 5!");
                lblSuccessDeletedRating.setText("");
            } else {
                lblSuccessRated.setText("You have already rated this movie");
            }
        } catch (IOException ex) {
            Logger.getLogger(RatingRecViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ratingNeutralOption(ActionEvent event) {
        try {
            if (model.specificRating() == null) {
                model.rateMovie(1);
                lblSuccessRated.setText("You gave this movie af 3 out of 5!");
                lblSuccessDeletedRating.setText("");
            } else {
                lblSuccessRated.setText("You have already rated this movie");
            }
        } catch (IOException ex) {
            Logger.getLogger(RatingRecViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ratingGoodOption(ActionEvent event) {
        try {
            if (model.specificRating() == null) {
                model.rateMovie(3);
                lblSuccessRated.setText("You gave this movie af 4 out of 5!");
                lblSuccessDeletedRating.setText("");
            } else {
                lblSuccessRated.setText("You have already rated this movie");
            }
        } catch (IOException ex) {
            Logger.getLogger(RatingRecViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ratingSuperGoodOption(ActionEvent event) {
        try {
            if (model.specificRating() == null) {
                model.rateMovie(5);
                lblSuccessRated.setText("You gave this movie af 5 out of 5!");
                lblSuccessDeletedRating.setText("");
            } else {
                lblSuccessRated.setText("You have already rated this movie");
            }
        } catch (IOException ex) {
            Logger.getLogger(RatingRecViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void setMovie() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void setModel(MovieModel1 model) {
        this.model = model;
        lblSelectedMovie.setText(model.getSelected().getTitle());
    }

}
