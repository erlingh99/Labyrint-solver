import java.io.FileNotFoundException;
import java.io.File;
import java.util.NoSuchElementException;
import java.util.Scanner;

class Labyrint
{
    private Rute[][] rutenett;
    private int kolonner;
    private int rader;
    private Lenkeliste<String> utveier;
    private ExitShowerController esc;

    private Labyrint(Rute[][] ruter, int kolonner, int rader, ExitShowerController esc)
    {
        rutenett = ruter;
        this.esc = esc;
        this.kolonner = kolonner;
        this.rader = rader;
    }

    public static Labyrint lesFraFil(File fil, ExitShowerController esc) throws FileNotFoundException, IllegalArgumentException
    {
        try (Scanner filLeser = new Scanner(fil)) //sorger for at leseren blir lukket
        {
            final int HOYDE;
            final int BREDDE;
            try
            {
                HOYDE = filLeser.nextInt();
                BREDDE = filLeser.nextInt();
            }
            catch (NoSuchElementException e)
            {
                throw new IllegalArgumentException();
            }
            filLeser.nextLine();
            Rute[][] ruter = new Rute[HOYDE][BREDDE];
            for (int i = 0; i < HOYDE; i++)
            {
                char[] c = filLeser.nextLine().toCharArray();
                for (int j = 0; j < BREDDE; j++)
                {
                    Rute r = null;
                    switch (c[j])
                    {
                        case '.':
                            if (j == BREDDE - 1 || j == 0 || i == 0 || i == HOYDE - 1) //ligger i kanten altsaa en aapning
                            {
                                r = new Aapning(i,j);
                                break;
                            }
                            r = new HvitRute(i, j);
                            break;
                        case '#':
                            r = new SortRute(i, j);
                            break;
                        default:
                            System.out.println("Ugyldig tegn: " + c[j]);
                            throw new IllegalArgumentException();
                    }
                    ruter[i][j] = r;
                }
            }
            Labyrint lab = new Labyrint(ruter, HOYDE, BREDDE, esc);
            for(int i = 0; i < HOYDE; i++)
            {
                for(int j = 0; j < BREDDE; j++)
                {
                    Rute r = ruter[i][j];
                    r.settLabyrint(lab);
                    try
                    {
                        r.settNaboNord(ruter[i-1][j]);
                        r.getNaboNord().settNaboSor(r);
                    }
                    catch (IndexOutOfBoundsException e) { }
                    try
                    {
                        r.settNaboVest(ruter[i][j-1]);
                        r.getNaboVest().settNaboOst(r);
                    }
                    catch (IndexOutOfBoundsException e) { }
                }
            }
            return lab;
        }
    }

    public Liste<String> finnUtveiFra(int kol, int rad, boolean utskrift)
    {
        utveier = new Lenkeliste<>(); //de blir ikke losningene fra forrige koordinat med
        rutenett[rad][kol].finnUtvei(utskrift);
        for(Rute[] rr : rutenett)
        {
            for(Rute r : rr)
            {
                r.settBesokt(false);
            }
        }
        return utveier;
    }

    public Rute[][] getRutenett()
    {
        return rutenett;
    }

    public Liste<String> getUtveier()
    {
        return utveier;
    }

    public int getRader()
    {
        return rader;
    }

    public int getKolonner()
    {
        return kolonner;
    }

    public ExitShowerController getEsc()
    {
        return esc;
    }

    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder("\n");
        for(Rute[] rad : rutenett)
        {
            for (Rute r : rad)
            {
                s.append(r.tilTegn());
            }
            s.append("\n");
        }
        return s.toString();
    }
}