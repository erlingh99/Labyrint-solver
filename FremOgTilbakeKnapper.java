import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

class FremOgTilbakeKnapper extends HBox
{
    private Button tilStart;
    private Button bakEn;
    private Button fremEn;
    private Button tilSlutt;
    private ExitShowerController esc;

    public FremOgTilbakeKnapper(ExitShowerController kontroll)
    {
        esc = kontroll;
        tilStart = new Button("<<");
        tilStart.setFont(new Font(20));
        tilStart.setPrefSize(60, 40);
        tilStart.setOnMouseClicked(e -> esc.setNrVises(0));
        tilStart.setDisable(true);

        bakEn = new Button("<");
        bakEn.setFont(new Font(20));
        bakEn.setPrefSize(60, 40);
        bakEn.setOnMouseClicked(e -> esc.setNrVises(esc.getNrVises()-1));
        bakEn.setDisable(true);

        fremEn = new Button(">");
        fremEn.setFont(new Font(20));
        fremEn.setPrefSize(60, 40);
        fremEn.setOnMouseClicked(e -> esc.setNrVises(esc.getNrVises()+1));
        fremEn.setDisable(true);

        tilSlutt = new Button(">>");
        tilSlutt.setFont(new Font(20));
        tilSlutt.setPrefSize(60, 40);
        tilSlutt.setOnMouseClicked(e -> esc.setNrVises(esc.getAntLosninger()-1));
        tilSlutt.setDisable(true);

        getChildren().addAll(tilStart, bakEn, fremEn, tilSlutt);
        setAlignment(Pos.CENTER);
    }

    public void enableButtons()
    {
        tilStart.setDisable(false);
        bakEn.setDisable(false);
        fremEn.setDisable(false);
        tilSlutt.setDisable(false);
    }
}