import java.io.File;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;

public class oblig7 extends Application
{
    public static void main(String[] args)
    {
        launch(args);    
    }

    final double WIDTH = 800;
    final double HEIGHT = 600;

    final double LAB_WIDTH = 0.6 * WIDTH;
    final double LAB_HEIGHT = 0.6 * HEIGHT;

    @Override
    public void start(Stage teater)
    {
        BorderPane hoyreSide = new BorderPane();
        VBox hoyreSideTop = new VBox();
        hoyreSideTop.setAlignment(Pos.CENTER);
        hoyreSide.setTop(hoyreSideTop);
        VBox hoyreSideSenter = new VBox();
        hoyreSideSenter.setAlignment(Pos.CENTER);
        hoyreSide.setCenter(hoyreSideSenter);
        VBox hoyreSideBunn = new VBox();
        hoyreSideBunn.setAlignment(Pos.CENTER);
        hoyreSide.setBottom(hoyreSideBunn);
        hoyreSide.setStyle("-fx-padding: 10;" +
        "-fx-border-style: solid;" +
        "-fx-border-width: 1;" +
        "-fx-border-insets: 5;" +
        "-fx-border-radius: 5;" +
        "-fx-border-color: grey;");

        VBox senter = new VBox();
        senter.setAlignment(Pos.BOTTOM_LEFT);

        BorderPane kulisser = new BorderPane();
        kulisser.setRight(hoyreSide);
        kulisser.setCenter(senter);
        BorderPane.setAlignment(senter, Pos.TOP_LEFT);
        BorderPane.setAlignment(hoyreSide, Pos.CENTER_RIGHT);

        ScrollPane labHolder = new ScrollPane();
        labHolder.setStyle("-fx-background: grey;");
        labHolder.setPannable(true);
        VBox.setVgrow(labHolder, Priority.ALWAYS);
        senter.getChildren().add(labHolder);
        
        Rutenett grid = new Rutenett();
        labHolder.setContent(grid);

        
        BorderPane deleNede = new BorderPane();
        deleNede.setStyle("-fx-padding: 10;" +
        "-fx-padding-right: 5;" +
        "-fx-border-style: solid;" +
        "-fx-border-width: 1;" +
        "-fx-border-insets: 5;" +
        "-fx-border-radius: 5;" +
        "-fx-border-color: grey;");
        senter.getChildren().add(deleNede);

        VBox nede = new VBox();
        nede.setAlignment(Pos.CENTER);
        deleNede.setCenter(nede);

        Label statAntall = new Label();
        Label statKortesteLosning = new Label();
        Label labTest = new Label();

        LabelManager labManager = new LabelManager(labTest, statAntall, statKortesteLosning);
        ExitShowerController esc = new ExitShowerController(grid, labManager);

        FremOgTilbakeKnapper knapper = new FremOgTilbakeKnapper(esc);
        knapper.setStyle("-fx-padding: 10");
        nede.getChildren().add(knapper);

        labTest.setMinWidth(150);
        labTest.setAlignment(Pos.CENTER);
        labTest.setStyle("-fx-padding: 5;" +
        "-fx-border-style: solid;" +
        "-fx-border-color: green;" +
        "-fx-background-color: black;" +
        "-fx-text-fill: green;");
        labTest.setFont(new Font(20));
        nede.getChildren().add(labTest);

        ZoomButtons zm = new ZoomButtons(grid);
        deleNede.setRight(zm);
        
        Label filValgTekst = new Label("Velg labyrint");
        filValgTekst.setFont(new Font(30));
        filValgTekst.setAlignment(Pos.CENTER);
        hoyreSideTop.getChildren().add(filValgTekst);

        Button filVelger = new Button("Velg fil");
        filVelger.setFont(new Font(20));
        
        FileChooser fc = new FileChooser();
        filVelger.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                File f = fc.showOpenDialog(teater);
                if (f != null)
                {
                    Labyrint lab;
                    try
                    {
                        lab = Labyrint.lesFraFil(f, esc);
                    }
                    catch (Exception e)
                    {
                        Alert feilBeskjed = new Alert(AlertType.ERROR);
                        feilBeskjed.setTitle("Feilmelding");
                        feilBeskjed.setHeaderText("Ugyldig fil");
                        feilBeskjed.setContentText("Den valgte filen kunne ikke leses. Vennligst pr\u00F8v igjen");
                        feilBeskjed.showAndWait();
                        return;
                    }
                    grid.setLabyrint(lab);
                    zm.enableButtons();
                    knapper.enableButtons();
                }
            }
        });
        
        hoyreSideTop.getChildren().add(filVelger);
        hoyreSideTop.setAlignment(Pos.TOP_CENTER);

        Label labStats = new Label("Stats");
        labStats.setFont(new Font(30));
        labStats.setAlignment(Pos.CENTER);
        hoyreSideSenter.getChildren().add(labStats);
        Label infoAntall = new Label("Antall l\u00F8sninger");
        infoAntall.setFont(new Font(20));
        infoAntall.setAlignment(Pos.CENTER);
        hoyreSideSenter.getChildren().add(infoAntall);
        statAntall.setMinWidth(100);
        statAntall.setAlignment(Pos.CENTER);
        statAntall.setStyle("-fx-padding: 5;" +
        "-fx-border-style: solid;" +
        "-fx-border-color: green;" +
        "-fx-background-color: black;" +
        "-fx-text-fill: green;");
        statAntall.setFont(new Font(20));
        hoyreSideSenter.getChildren().add(statAntall);
        Label infoKort = new Label("Korteste l\u00F8sning");
        infoKort.setFont(new Font(20));
        infoKort.setAlignment(Pos.CENTER);
        hoyreSideSenter.getChildren().add(infoKort);
        statKortesteLosning.setMinWidth(100);
        statKortesteLosning.setAlignment(Pos.CENTER);
        statKortesteLosning.setStyle("-fx-padding: 5;" +
        "-fx-border-style: solid;" +
        "-fx-border-color: green;" +
        "-fx-background-color: black;" +
        "-fx-text-fill: green;");
        statKortesteLosning.setFont(new Font(20));
        hoyreSideSenter.getChildren().add(statKortesteLosning);

        Scene scene = new Scene(kulisser);
        
        RutenettMusZoom sceneGestures = new RutenettMusZoom(grid);
        scene.addEventFilter(ScrollEvent.ANY, sceneGestures.getOnScrollEventHandler());
        
        teater.setTitle("Labyrintl\u00F8ser");
        teater.setScene(scene);
        teater.setWidth(WIDTH); teater.setHeight(HEIGHT);
        teater.show();
    }
}