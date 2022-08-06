import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

abstract class Rute extends Rectangle
{
    private int rad;
    private int kolonne;
    private Labyrint labyrint;
    private Rute naboNord;
    private Rute naboOst;
    private Rute naboVest;
    private Rute naboSor;
    private boolean besokt = false;
    private boolean utskrift = false;

    public Rute(int rad, int kolonne)
    {
        this.kolonne = kolonne;
        this.rad = rad;
        setStroke(Color.BLACK);
    }

    abstract public Color farge();

    public void finnUtvei(boolean utskrift)
    {
        this.utskrift = utskrift;
        gaa(null, "");
    }

    void gaa(Rute fra, String vei)
    {
        besokt = true;
        Rute[] naboer = {naboNord, naboOst, naboSor, naboVest};
        for(Rute n : naboer)
        {
            if (n != fra && !n.getBesokt())
            {
                if (utskrift)
                {
                    System.out.print(getKolonne() + ", " + getRad() + "|");
                }
                String s = "(" + getKolonne() + ", " + getRad() + ")";
                if (vei.length() - s.length() < 0)
                {
                    vei += s;
                }
                else if (!vei.substring(vei.length()-s.length()).equals(s)) //sorger for at koordinatene til samme rute ikke kommer
                {                                                           //flere ganger paa rad, feks ved besok av sort rute mellom
                    vei += " --> " + s;
                }
                n.gaa(this, vei);
            }
        }
        besokt = false;
    }

    abstract char tilTegn();

    public void settLabyrint(Labyrint l)
    {
        labyrint = l;
    }

    public Labyrint getLabyrint()
    {
        return labyrint;
    }

    public int getKolonne()
    {
        return kolonne;
    }

    public int getRad()
    {
        return rad;
    }

    public void toggleBesokt()
    {
        besokt = !besokt;
    }

    public boolean getBesokt()
    {
        return besokt;
    }

    public void settBesokt(boolean verdi)
    {
        besokt = verdi;
    }

    public Rute getNaboNord()
    {
        return naboNord;
    }

    public Rute getNaboOst()
    {
        return naboOst;
    }

    public Rute getNaboSor()
    {
        return naboSor;
    }

    public Rute getNaboVest()
    {
        return naboVest;
    }

    public void settNaboNord(Rute nord)
    {
        naboNord = nord;
    }

    public void settNaboOst(Rute ost)
    {
        naboOst = ost;
    }

    public void settNaboVest(Rute vest)
    {
        naboVest = vest;
    }

    public void settNaboSor(Rute sor)
    {
        naboSor = sor;
    }
}