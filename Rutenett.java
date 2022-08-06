import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class Rutenett extends GridPane
{
    private final int minZoom = 6;
    private final int maxZoom = 90;
    private final int zoomSteps = 4;

    private Labyrint lab;
    private Rute[][] ruter;
    private double nodeSize = 30;
    
    public Rutenett()
    {

    }

    public void setLabyrint(Labyrint lab)
    {
        this.lab = lab;
        ruter = lab.getRutenett();
        tegnRutenett();
        setNodeSize(nodeSize);
    }

    public Labyrint getLabyrint()
    {
        return lab;
    }

    public double getNodeSize()
    {
        return nodeSize;
    }

    public void setNodeSize(double size)
    {
        if (size > maxZoom || size < minZoom)
        {
            return;
        }
        nodeSize = size;
        for (Node c : getChildren())
        {
            Rectangle r;
            try
            {
                r = (Rectangle)c;
            }
            catch (ClassCastException e)
            {
                throw new RuntimeException("Noe annet enn et rektangel i gridpane");
            }
            r.setWidth(nodeSize);
            r.setHeight(nodeSize);
        }
    }

    private void tegnRutenett()
    {
        getChildren().clear();
        for (int i = 0; i < ruter.length; i++)
        {
            for (int j = 0; j < ruter[0].length; j++)//er rektangulaer og ikke-null, saa [0] gaar fint
            {
                add(ruter[i][j], j, i);
            }
        }
    }

    public void paintPath(boolean[][] sti)
    {
        for (Node c : getChildren())
        {
            Rute c2 = ((Rute)c);
            if (sti[GridPane.getRowIndex(c)][GridPane.getColumnIndex(c)])
            {
                c2.setFill(Color.YELLOW);
            }
            else
            {
                c2.setFill(c2.farge()); //farge tilbake tidligere ruter
            }
        }
    }

    public void okeStorrelse()
    {
        setNodeSize(nodeSize + zoomSteps);
    }

    public void minkeStorrelse()
    {
        setNodeSize(nodeSize - zoomSteps);
    }

    /**
     * Konverterer losning-String fra oblig 5 til en boolean[][]-representasjon
     * av losningstien.
     * @param losningString String-representasjon av utveien
     * @param bredde        bredde til labyrinten
     * @param hoyde         hoyde til labyrinten
     * @return              2D-representasjon av rutene der true indikerer at
     *                      ruten er en del av utveien.
     */
    static boolean[][] losningStringTilTabell(String losningString, int bredde, int hoyde)
    {
        boolean[][] losning = new boolean[hoyde][bredde];
        java.util.regex.Pattern p = java.util.regex.Pattern.compile("\\(([0-9]+),([0-9]+)\\)");
        java.util.regex.Matcher m = p.matcher(losningString.replaceAll("\\s",""));
        while (m.find()) {
            int x = Integer.parseInt(m.group(1));
            int y = Integer.parseInt(m.group(2));
            losning[y][x] = true;
        }
        return losning;
    }
}