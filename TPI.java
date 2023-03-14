import paquete.*;
import java.util.ArrayList;

public class TPI
{
    public static void main(String[] args)
    {
        Ronda ronda1 = new Ronda("1");
        Equipo a = new Equipo("JURASICOS", "Los viejos.");
        Equipo b = new Equipo("PANTERAS", "Los principiantes.");
        Partido partido1 = new Partido(1, a, b, 50, 33);
        ronda1.addPartido(partido1);

        Equipo c = new Equipo("PESCA", "Los pepegas.");
        Partido partido2 = new Partido(2, a, c, 45, 53);
        ronda1.addPartido(partido2);

        Persona persona1 = new Persona("Donald");
        Pronostico gamba1 = new Pronostico(partido1, a, "ganador");
        persona1.addPronostico(gamba1);
        Pronostico gamba2 = new Pronostico(partido2, c, "ganador");
        persona1.addPronostico(gamba2);
        System.out.print(persona1);
        System.out.print(persona1.calcularPuntos());
        System.out.print("\n" + ronda1);
    }
}
