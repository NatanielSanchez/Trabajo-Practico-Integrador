package paquete;

import java.util.ArrayList;

public class Ronda
{
    private static int totalRondas = 0; // cantidad total de rondas creadas
    private String numero;
    private ArrayList<Partido> partidos; // arreglo-lista de clase Partido


    public Ronda()
    {
        totalRondas++;
    }

    public Ronda(String numero)
    {
        this.numero = numero;
        this.partidos = new ArrayList<Partido>();
        totalRondas++;
    }

    public static int getTotalRondas()
    {
        return totalRondas;
    }

    public String getNumero()
    {
        return numero;
    }

    public void setNumero(String numero)
    {
        this.numero = numero;
    }

    public ArrayList<Partido> getPartidos()
    {
        return partidos;
    }

    public void setPartidos(ArrayList<Partido> partidos)
    {
        this.partidos = partidos;
    }

    public int getSize() // devuelve la cantidad de partidos de la ronda
    {
        return partidos.size();
    }

    public void addPartido(Partido x) // añade un partido a la ronda
    {
        partidos.add(x);
    }

    @Override
    public String toString()
    {
        String txt =  "- RONDA " + numero + ": " + "\n";
        for (int i=0; i < partidos.size(); i++)
        {
            txt = txt + ("  *PARTIDO " + Integer.toString(i+1) + ": \n");
            txt = txt + partidos.get(i).toString();
            txt = txt + "\n";
        }
        return txt;
    }
}
