import javafx.event.EventHandler;
import javafx.scene.input.ScrollEvent;

class RutenettMusZoom
{
    private Rutenett canvas;

    public RutenettMusZoom(Rutenett canvas)
    {
        this.canvas = canvas;
    }

    public EventHandler<ScrollEvent> getOnScrollEventHandler()
    {
        return onScrollEventHandler;
    }

    private EventHandler<ScrollEvent> onScrollEventHandler = new EventHandler<ScrollEvent>()
    {
        @Override
        public void handle(ScrollEvent event)
        {
            if (event.getDeltaY() < 0)
            {
                canvas.minkeStorrelse();
            }
            else
            {
                canvas.okeStorrelse();
            }
            event.consume();
        }
    };
}