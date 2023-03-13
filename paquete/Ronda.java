package paquete;

import java.util.Arrays;

public class Ronda
{
    private static int totalRondas = 0; // cantidad total de rondas creadas
    private String numero;
    private Partido[] partidos; // arreglo de clase Partido


    public Ronda()
    {
        totalRondas++;
    }

    public Ronda(String numero, Partido[] partidos)
    {
        this.numero = numero;
        this.partidos = partidos;
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

    public Partido[] getPartidos()
    {
        return partidos;
    }

    public void setPartidos(Partido[] partidos)
    {
        this.partidos = partidos;
    }

    public int getLength() // devuelve la cantidad de partidos de la ronda
    {
        return partidos.length;
    }

    @Override
    public String toString()
    {
        String txt =  "- RONDA " + numero + ": " + "\n";
        for (int i=0; i < partidos.length; i++)
        {
            txt = txt + partidos[i].toString();
        }
        return txt;
    }
}
