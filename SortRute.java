import javafx.scene.paint.Color;

class SortRute extends Rute
{
    SortRute(int kolonne, int rad)
    {
        super(kolonne, rad);
        setFill(Color.BLACK);
    }

    @Override
    void gaa(Rute fra, String vei)
    {
        if (vei.length() == 0)
        {
            System.out.println("Kan ikke starte i sort rute");
        }
    }
    
    @Override
    char tilTegn()
    {
        return '#';
    }

    @Override
    public Color farge()
    {
        return Color.BLACK;
    }
}