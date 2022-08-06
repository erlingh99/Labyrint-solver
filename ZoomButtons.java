import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

class ZoomButtons extends VBox
{
    private Button plus;
    private Button minus;
    private Rutenett toZoom;

    public ZoomButtons(Rutenett skalZoomes)
    {
        setAlignment(Pos.CENTER);
        toZoom = skalZoomes;
        plus = new Button("+");
        plus.setFont(new Font(20));
        plus.setPrefSize(50, 40);
        plus.setOnMouseClicked(e -> toZoom.okeStorrelse());
        plus.setDisable(true);
        minus = new Button("-");
        minus.setFont(new Font(20));
        minus.setPrefSize(50, 40);
        minus.setOnMouseClicked(e -> toZoom.minkeStorrelse());
        minus.setDisable(true);
        getChildren().addAll(plus, minus);
    }

    public void enableButtons()
    {
        plus.setDisable(false);
        minus.setDisable(false);
    }
}
