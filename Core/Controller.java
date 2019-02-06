package Core;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXToggleButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import java.net.URL;
import java.util.ResourceBundle;

/*The Controller class is connected with the faceEmotions.fxml file,
it handles slider events and face changes. Also it implements the method model.start()
* and also sends paramets to the ModelGenerator class. */

public class Controller implements Initializable {
    private static Model model;
    private final int PORT = 1594;

    @FXML
    Label portNumber;
    @FXML
    JFXToggleButton toggle;

    @FXML
    JFXSlider agreement;
    @FXML
    JFXSlider disagreement;
    @FXML
    JFXSlider frustate;
    @FXML
    JFXSlider concentrate;
    @FXML
    JFXSlider thinking;
    @FXML
    JFXSlider unsure;

    @FXML
    QuadCurve smile;

    @FXML
    Circle rightEye;

    @FXML
    Circle leftEye;

    @FXML
    Line nose;

    @FXML
    Ellipse face;

    @FXML void defaultSettings(){
        smile.setControlY(+30.0);
        nose.setEndY(-4.0);
        leftEye.setRadius(16.0);
        rightEye.setRadius(16.0);
        face.setFill(Color.DODGERBLUE);

    }

    @FXML void sliderAgreementDragged(){
            System.out.println(agreement.getValue());
            if(agreement.getValue() >= 5) {
                smile.setControlY(+60.0);
            }
            else{
                smile.setControlY(+30.0);
            }
            DataGenerator dataGen = new DataGenerator();
            dataGen.updateParam("agreement", (double)agreement.getValue());
        }
    @FXML void sliderDisagreementDragged(){
        System.out.println(disagreement.getValue());
        if(disagreement.getValue() >= 5) {
            smile.setControlY(-20.0);
        }
        else{
            smile.setControlY(+30.0);
        }
        DataGenerator dataGen = new DataGenerator();
        dataGen.updateParam("disagreement", (double)disagreement.getValue());
    }
    @FXML void sliderFrustateDragged(){
        System.out.println(frustate.getValue());
        if(frustate.getValue() >= 5) {
            smile.setControlY(0.0);
            face.setFill(Color.RED);

        }
        else{
            smile.setControlY(+30.0);
            face.setFill(Color.DODGERBLUE);
        }
        DataGenerator dataGen = new DataGenerator();
        dataGen.updateParam("frustrate", (double)frustate.getValue());
    }
    @FXML void sliderConcentrateDragged(){
        System.out.println(concentrate.getValue());
        if(concentrate.getValue() >= 5) {
            leftEye.setRadius(20.0);
            rightEye.setRadius(20.0);
        }
        else{
            leftEye.setRadius(16.0);
            rightEye.setRadius(16.0);
        }
        DataGenerator dataGen = new DataGenerator();
        dataGen.updateParam("concentrate", (double)concentrate.getValue());
    }
    @FXML void sliderThinkingDragged(){
        System.out.println(thinking.getValue());
        if(thinking.getValue() >= 5) {
            nose.setEndY(0.0);
        }
        else{
            nose.setEndY(-4.0);
        }
        DataGenerator dataGen = new DataGenerator();
        dataGen.updateParam("thinking", (double)thinking.getValue());
    }
    @FXML void sliderUnsureDragged(){
        System.out.println(unsure.getValue());
        if(unsure.getValue() >= 5) {
            nose.setEndY(0.0);
            smile.setControlY(0.0);
        }
        else{
            nose.setEndY(-4.0);
            smile.setControlY(+30.0);
        }
        DataGenerator dataGen = new DataGenerator();
        dataGen.updateParam("unsure", (double)unsure.getValue());
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        agreement.setIndicatorPosition(JFXSlider.IndicatorPosition.LEFT);
        toggle.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(toggle.isSelected() == true){
                    model = new Model(new DataGenerator(), new Publisher(PORT));
                    model.start();
                    toggle.setText("STOP");
                }
                else{
                    model.stop();
                    toggle.setText("RUN");
                }
            }
        });

    }



}
