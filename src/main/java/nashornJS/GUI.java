package nashornJS;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileReader;

public class GUI extends Application {

    private ImageView selectedImage = new ImageView();
    private FileChooser jsFileChooser = new FileChooser();
    private HBox jsHBox = new HBox();
    private Button jsSubmitFileButton = new Button("Set algoritm");
    private VBox vBox = new VBox();
    ComboBox<String> verGapComboBox = new ComboBox();

    private GridPane createPane() {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(20, 20, 20, 20));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(true);
        return pane;
    }

    private void setUIControls(VBox pane) throws Exception {

        Image imageToProcess = new Image(new FileInputStream("C:\\Users\\djankooo\\Desktop\\PWr\\PWJJ\\lab11_Nashorn\\nashorn.jpg"));
        selectedImage.setImage(imageToProcess);

        verGapComboBox.getItems().addAll("grayscale", "sepia");

        jsFileChooser.setTitle("Open Resource File");

        jsSubmitFileButton.setPrefWidth(imageToProcess.getWidth());
        verGapComboBox.setPrefWidth(imageToProcess.getWidth());

        jsSubmitFileButton.setOnAction(e ->
        {
            ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
            ScriptEngine engine = scriptEngineManager.getEngineByName("nashorn");

            try {
                engine.eval(new FileReader("src\\main\\java\\nashornJS\\ImageProcessorJS.js"));
                Invocable invocable = (Invocable) engine;

                BufferedImage buffImg = SwingFXUtils.fromFXImage(imageToProcess, null);

                buffImg = (BufferedImage) invocable.invokeFunction(verGapComboBox.getValue(), buffImg, buffImg.getWidth(), buffImg.getHeight());

                Image processedImage = SwingFXUtils.toFXImage(buffImg, null);

                selectedImage.setImage(processedImage);

            } catch (Exception ex) {
                ex.printStackTrace();
            }


        });


        pane.getChildren().addAll(selectedImage,jsHBox, verGapComboBox, jsSubmitFileButton);

    }

    @Override
    public void start(final Stage primaryStage) throws Exception {

        System.out.println( "Hello from GUI" );
        GridPane gridPane = createPane();
        gridPane.add(vBox,0,0);
        setUIControls(vBox);
        primaryStage.setScene(new Scene(gridPane, 1000, 500));
        primaryStage.show();
    }

    public static void main(String[] args) { launch(args); }

}
