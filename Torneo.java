import paquete.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Torneo// Crea y almacena todos los datos de los equipos, partidos, rondas y participantes
{
    private ArrayList<Persona> lista_personas = new ArrayList<>(); // lista de participantes
    private ArrayList<Equipo> lista_equipos = new ArrayList<>(); // lista de equipos (para no repetir equipos)
    private ArrayList<Ronda> lista_rondas = new ArrayList<>(); // lista de rondas

    // LOS ARCHIVOS ESTAN ESCRITOS DE LA SIGUIENTE FORMA:
    //- resultados.csv : una linea por partido, con formato "equipo1,descripcion1,goles1,equipo2,descripcion2,goles2"
    //- pronosticos.csv : cada linea contiene un nombre, la id del partido a pronosticar, el equipo elegido y
    // 					 el resultado elegido, con formato "nombre,id,equipo,resultado"
    private File resultados;
    private File pronosticos;

    public Torneo() // NO SE USA ESTO
    {

    }

    public Torneo(String resultados, String pronosticos) // Recibe los paths de los archivos.
    {
        this.resultados = new File(resultados);
        this.pronosticos = new File(pronosticos);
        generatePartidos();
        generateParticipantes();
    }

    private void generatePartidos()
    {
        // Procesa el archivo de texto para crear las instancias de equipo, partido y ronda.
        // Estas instancias se almacenan en sus listas correspondientes.

        int id_partidos = 1; // para usar como numero ID de cada partido creado
        Ronda ronda = new Ronda(1);
        try (Scanner sc = new Scanner(resultados))
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
        catch (FileNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        }
        lista_rondas.add(ronda);
    }

    private void generateParticipantes()
    {
        // Procesa el archivo de texto para crear las instancias de Persona
        // Estas instancias se almacenan en su lista correspondiente.

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
                Partido partido = buscarPartido( Integer.valueOf(datos[1]) );
                Pronostico pron = new Pronostico(partido, equipo, res);
                persona.addPronostico(pron);
            }
        }
        catch (FileNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    private Partido buscarPartido(int num)
    {
        // Devuelve la instancia de Partido segun el numero id.
        Partido x = null;
        for (int i = 0; i < lista_rondas.size(); i++) // Recorre cada ronda del torneo
        {
            x = lista_rondas.get(i).getPartido(num); // Ver getPartido() en Ronda
            if (x != null) break;
        }
        return x;
    }

    private Persona buscarPersona(String nombre)
    {
        // Devuelve un puntero de Persona si se encuentra una persona (participante) ya registrada con el nombre
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

    private Equipo buscarEquipo(String nombre)
    {
        // Devuelve un puntero de Equipo si se encuentra un equipo ya registrada con el nombre
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

    public String listadoParticipantes()
    {
        StringBuilder stb = new StringBuilder();
        for (int i=0; i < lista_personas.size(); i++)
        {
            stb.append(lista_personas.get(i).toString());
            stb.append("\n");
        }
        return stb.toString();
    }
}
