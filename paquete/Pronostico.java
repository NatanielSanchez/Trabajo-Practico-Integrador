package paquete;

public class Pronostico // consiste en una apuesta o prediccion hacia un equipo en un partido
{
    private final Partido partido; // El partido particular en donde jugó el equipo
    private final int idPartido;
    private final Equipo equipo; // El equipo al que se apuesta
    private final String resultado; // La prediccion. Puede ser "ganador", "perdedor" o "empate"

    public Pronostico(Partido partido, Equipo equipo, String resultado)
    {
        this.partido = partido;
        this.equipo = equipo;
        this.resultado = resultado.toLowerCase(); // por conveniencia se guarda y procesa en minuscula
        this.idPartido = partido.getId();
    }

    public Partido getPartido()
    {
        return partido;
    }

    public Equipo getEquipo()
    {
        return equipo;
    }

    public String getResultado()
    {
        return resultado;
    }

    public int getIdPartido()
    {
        return idPartido;
    }

    @Override
    public String toString()
    {
        return partido.toString() + "\n\tPREDICCION: " + equipo.toString() + " - " + resultado.toUpperCase() + "\n";
    }

    public boolean acierto() //determina si la prediccion fue acertada
    {
        String x = partido.confirmarResultado(equipo); //ver confirmarResultado() en Partido
        if (x.equals(resultado))
        {
            return true;
        }
        return false;
    }
}
