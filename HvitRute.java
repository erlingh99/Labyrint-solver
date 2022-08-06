import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

class HvitRute extends Rute
{
    HvitRute(int kolonne, int rad)
    {
        super(kolonne, rad);
        setFill(Color.WHITE);
        setCursor(Cursor.HAND);
        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                getLabyrint().finnUtveiFra(rad, kolonne, false);
                getLabyrint().getEsc().go();
            }
        });
    }

    @Override
    char tilTegn()
    {
        return '.';
    }

    @Override
    public Color farge()
    {
        return Color.WHITE;
    }
}