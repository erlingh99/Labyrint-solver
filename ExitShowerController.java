class ExitShowerController
{
    private LabelManager labManager;
    private Rutenett r;
    private Labyrint lab;    
    private int nrVises;
    private int antLosninger;
    private Liste<String> utveier;

    public ExitShowerController(Rutenett r, LabelManager labManager)
    {
        this.r = r;
        this.labManager = labManager;
    }

    public void go()
    {
        lab = r.getLabyrint();
        utveier = r.getLabyrint().getUtveier();        
        antLosninger = utveier.stoerrelse();
        labManager.antall(antLosninger);
        if (antLosninger > 0)
        {
            int min = Integer.MAX_VALUE;
            for (String s : utveier)
            {
                int a = 0;
                for (char c : s.toCharArray())
                {
                    if (c == '(')
                    {
                        a++;
                    }
                }
                if (a < min)
                {
                    min = a;
                }
            }
            labManager.kortesteVei(min);
        }
        visNr(0);
    }

    public void visNr(int nr)
    {
        boolean[][] b;
        if (utveier.stoerrelse() == 0)
        {
            b = Rutenett.losningStringTilTabell("", lab.getRader(), lab.getKolonner());
        }
        else
        {
            b = Rutenett.losningStringTilTabell(utveier.hent(nr), lab.getRader(), lab.getKolonner());
        }
        r.paintPath(b);
        labManager.nrSomVises(nr);
    }

    public int getAntLosninger()
    {
        return antLosninger;
    }

    public int getNrVises()
    {
        return nrVises;
    }

    public void setNrVises(int ny)
    {
        if (ny < 0 || ny >= antLosninger)
        {
            return;
        }
        nrVises = ny;
        visNr(ny);
    }
}