import javafx.scene.control.Label;

class LabelManager
{
    private Label hvilkenSomVises;
    private Label antall;
    private Label kortesteVei;
    private int antallVeier;
    private int nrSomVises;
    private int kortSteg;

    public LabelManager(Label showStatus, Label nrOfExits, Label shortestRoute)
    {
        hvilkenSomVises = showStatus;
        antall = nrOfExits;
        kortesteVei = shortestRoute;
    }

    public void antall(int i)
    {
        antallVeier = i;
        antall.setText(""+antallVeier);
    }

    public void nrSomVises(int i)
    {
        nrSomVises = i+1;
        if (antallVeier == 0)
        {
            hvilkenSomVises.setText("-/"+antallVeier);
            kortesteVei.setText("-");
        }
        else
        {
            hvilkenSomVises.setText(nrSomVises+"/"+antallVeier);
        }
    }

    public void kortesteVei(int i)
    {
        kortSteg = i;
        kortesteVei.setText(""+kortSteg);
    }
}