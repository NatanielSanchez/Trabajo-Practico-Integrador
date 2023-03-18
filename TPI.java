import paquete.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class TPI
{
    private static ArrayList<Persona> lista_personas = new ArrayList<>(); // lista de participantes
    private static ArrayList<Equipo> lista_equipos = new ArrayList<>(); // lista de equipos (para no repetir equipos)
    private static ArrayList<Ronda> lista_rondas = new ArrayList<>(); // lista de rondas

    public static void main(String[] args)
    {
        generatePartidos();
        generateParticipantes();

        for (int i = 0; i < lista_personas.size(); i++)
        {
            // Por cada persona, muestra sus puntos y sus pronosticos.
            System.out.println(lista_personas.get(i));
        }
    }

    public static void generatePartidos()
    {
        // Procesa el archivo de texto para crear las instancias de equipo, partido y ronda.
        // Estas instancias se almacenan en sus listas correspondientes.
        File partidos = new File("resultados.csv");

        int id_partidos = 1; // para usar como numero ID de cada partido creado
        Ronda ronda = new Ronda(1);
        try (Scanner sc = new Scanner(partidos))
        {
            while (sc.hasNextLine())
            {
                String linea = sc.nextLine();
                String[] datos = linea.split(",");

                Equipo equipo1 = buscarEquipo(datos[0]);
                if (equipo1 == null)
                {
                    equipo1 = new Equipo(datos[0], datos[1]);
                    lista_equipos.add(equipo1);
                }

                Equipo equipo2 = buscarEquipo(datos[3]);
                if (equipo2 == null)
                {
                    equipo2 = new Equipo(datos[3], datos[4]);
                    lista_equipos.add(equipo2);
                }

                int goles1 = Integer.valueOf(datos[2]);
                int goles2 = Integer.valueOf(datos[5]);
                Partido partido = new Partido(id_partidos, equipo1, equipo2, goles1, goles2);
                id_partidos++;
                ronda.addPartido(partido);
            }
        }
        catch (Exception ex)
        {
            System.out.println("Algo salió mal...");
        }
        lista_rondas.add(ronda);
    }

    public static void generateParticipantes()
    {
        // Procesa el archivo de texto para crear las instancias de Persona
        // Estas instancias se almacenan en su lista correspondiente.
        File pronosticos = new File("pronosticos.csv");

        try (Scanner sc = new Scanner(pronosticos))
        {
            while (sc.hasNextLine())
            {
                String linea = sc.nextLine();
                String[] datos = linea.split(",");

                Persona persona = buscarPersona(datos[0]);
                if (persona == null)
                {
                    persona = new Persona(datos[0]);
                    lista_personas.add(persona);
                }

                ResultadoEnum res = null;
                switch (datos[3])
                {
                    case "ganador":
                        res = ResultadoEnum.GANADOR;
                        break;
                    case "perdedor":
                        res = ResultadoEnum.PERDEDOR;
                        break;
                    case "empate":
                        res = ResultadoEnum.EMPATE;
                        break;
                }

                Equipo equipo = buscarEquipo(datos[2]);
                Partido partido = conseguirPartido( Integer.valueOf(datos[1]) );
                Pronostico pron = new Pronostico(partido, equipo, res);
                persona.addPronostico(pron);
            }
        }
        catch (Exception ex)
        {
            System.out.println("Algo salió mal...");
        }
    }

    public static Equipo buscarEquipo(String nombre)
    {
        // Devuelve una instancia de Equipo si se encuentra una ya registrada con el nombre
        // De lo contrario, devuelve null.
        Equipo x = null;
        for (int i = 0; i < lista_equipos.size(); i++)
        {
            if ( nombre.equals(lista_equipos.get(i).getNombre()) )
            {
                x = lista_equipos.get(i);
                break;
            }
        }
        return x;


    }

    public static Persona buscarPersona(String nombre)
    {
        // Devuelve una instancia de Persona si se encuentra una ya registrada con el nombre
        // De lo contrario, devuelve null.
        Persona x = null;
        for (int i = 0; i < lista_personas.size(); i++)
        {
            if ( nombre.equals(lista_personas.get(i).getNombre()) )
            {
                x = lista_personas.get(i);
                break;
            }
        }
        return x;


    }

    public static Partido conseguirPartido(int num)
    {
        // Devuelve la instancia de Partido segun el numero id.
        Partido x = null;
        boolean found = false;
        for (int i = 0; i < lista_rondas.size(); i++)
        {
            if (found)
                break;
            else
            {
                ArrayList<Partido> partidos = lista_rondas.get(i).getPartidos();
                for (int j = 0; j < partidos.size(); j++)
                {
                    if (num == partidos.get(j).getId())
                    {
                        found = true;
                        x = partidos.get(j);
                        break;
                    }
                }
            }
        }
        return x;
    }
}