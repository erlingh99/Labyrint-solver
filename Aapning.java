class Aapning extends HvitRute
{
    public Aapning(int kolonne, int rad)
    {
        super(kolonne, rad);
    }

    @Override
    void gaa(Rute fra, String vei) 
    {
        String s = "(" + getKolonne() + ", " + getRad() + ")";
        if (vei.length() == 0)
        {
            vei += s;
        }
        else
        {
            vei += " --> " + s;
        }
        getLabyrint().getUtveier().leggTil(vei);
    }
}