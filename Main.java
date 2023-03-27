/* v1 (Pruebas)
 * import java.util.*;

public class Main {

    public static void main(String[] args) {

        // Paso 1: leer los ficheros de texto y almacenar los platos disponibles en ArrayLists
        ArrayList<String> entrantes = leerFichero("entrantes.txt");
        ArrayList<String> primeros = leerFichero("primeros.txt");
        ArrayList<String> segundos = leerFichero("segundos.txt");
        ArrayList<String> postres = leerFichero("postres.txt");

        try (// Paso 2: mostrar menú de opciones al usuario
        Scanner scanner = new Scanner(System.in)) {
            int opcion = 0;
            String menuActual = "";
            while (opcion != 3) {
                System.out.println("Seleccione una opción:");
                System.out.println("1. Generar nuevo menú");
                System.out.println("2. Ver menú actual");
                System.out.println("3. Salir");
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        // Paso 3: generar un menú aleatorio
                        String entrante = getRandomItem(entrantes);
                        String primero = getRandomItem(primeros);
                        String segundo = getRandomItem(segundos);
                        String postre = getRandomItem(postres);
                        menuActual = "Entrante: " + entrante + "\n" +
                                "Primero: " + primero + "\n" +
                                "Segundo: " + segundo + "\n" +
                                "Postre: " + postre + "\n";
                        System.out.println("Menú generado:");
                        System.out.println(menuActual);

                        // Paso 4: preguntar al usuario si desea guardar el menú
                        System.out.println("¿Desea guardar el menú? (s/n)");
                        String respuesta = scanner.next();
                        if (respuesta.equalsIgnoreCase("s")) {
                            escribirFichero("menu.txt", menuActual);
                        }
                    }
                }
            }
        }

    private static void escribirFichero(String string, String menuActual) {
    }

    private static String getRandomItem(ArrayList<String> entrantes) {
        return null;
    }

    private static ArrayList<String> leerFichero(String string) {
        return null;
    }
}

v2 (Funcional con errores)

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner sc = new Scanner(System.in)) {
            Random rand = new Random();

            String[] entrantes = leerFichero("entrantes.txt");
            String[] primeros = leerFichero("primeros.txt");
            String[] segundos = leerFichero("segundos.txt");
            String[] postres = leerFichero("postres.txt");

            String[] menu = new String[4];
            boolean menuGuardado = false;

            do {
                System.out.println("¿Qué quieres hacer?");
                System.out.println("1. Ver el menú actual");
                System.out.println("2. Generar un nuevo menú");
                System.out.println("3. Salir");

                int opcion = sc.nextInt();

                switch(opcion) {
                    case 1:
                        if (menuGuardado) {
                            System.out.println("El menú guardado es:");
                            new BufferedReader (new FileReader("menu.txt"));
                        } else {
                            System.out.println("No hay ningún menú guardado.");
                        }
                        break;

                    case 2:
                        if (menuGuardado) {
                            System.out.println("Ya hay un menú guardado. ¿Quieres sobrescribirlo? (s/n)");
                            String respuesta = sc.next().toLowerCase();

                            if (!respuesta.equals("s")) {
                                break;
                            }
                        }

                        menu[0] = entrantes[rand.nextInt(entrantes.length)];
                        menu[1] = primeros[rand.nextInt(primeros.length)];
                        menu[2] = segundos[rand.nextInt(segundos.length)];
                        menu[3] = postres[rand.nextInt(postres.length)];

                        System.out.println("El menú generado es:");
                        mostrarMenu(menu);

                        System.out.println("¿Quieres guardarlo? (s/n)");
                        String respuesta = sc.next().toLowerCase();

                        if (respuesta.equals("s")) {
                            escribirFichero("menu.txt", menu);
                            menuGuardado = true;
                            System.out.println("Menú guardado correctamente.");
                        }
                        break;

                    case 3:
                        System.out.println("¡Hasta luego!");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Opción no válida.");
                        break;
                }

                System.out.println();
            } while(true);
        }
    }

    private static void mostrarMenu(String[] menu) {
    }

    public static String[] leerFichero(String nombreFichero) {
        ArrayList<String> lineas = new ArrayList<String>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(nombreFichero));
            String linea = br.readLine();

            while (linea != null) {
                lineas.add(linea);
                linea = br.readLine();
            }

            br.close();
        } catch (IOException e) {
            System.out.println("Error al leer el fichero " + nombreFichero + ": " + e.getMessage());
        }

        String[] resultado = new String[lineas.size()];

        for (int i = 0; i < lineas.size(); i++) {
            resultado[i] = lineas.get(i);
        }

        return resultado;
    }

    public static void escribirFichero(String nombreFichero, String[] lineas) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(nombreFichero));

            for (int i = 0; i < lineas.length; i++) {
                pw.println(lineas[i]);
            }

            pw.close();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
 */

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner sc = new Scanner(System.in)) { //Utilizamos un try para crear un Scanner que lee las entradas 
            Random rand = new Random();//Emplearemos la clase Random para generar números aleatorios para crear el menú

            //Leemos los contenidos de los archivos de texto
            String[] entrantes = leerFichero("entrantes.txt");
            String[] primeros = leerFichero("primeros.txt");
            String[] segundos = leerFichero("segundos.txt");
            String[] postres = leerFichero("postres.txt");

            //Creamos un arreglo para almacenar los elementos del menú generado
            String[] menu = new String[4];
            //Iniciamos una variable que comprueba si existe un menú guardado
            boolean menuGuardado = false;

            do {
                System.out.println("Bienvenido!\nEl precio del menú es de 12$ e incluye bebida y postre.");
                System.out.println("¿Qué quieres hacer?");
                System.out.println("1. Ver el menú actual");
                System.out.println("2. Generar un nuevo menú");
                System.out.println("3. Salir");

                int opcion = sc.nextInt(); //Leemos la opción elegida por el usuario

                switch(opcion) {
                    case 1:
                        if (menuGuardado) {
                            System.out.println("El menú guardado es:");
                            mostrarMenu(menu); //Llamamos al método para mostrar el menú generado
                        } else {
                            System.out.println("No hay ningún menú guardado.");
                        }
                        break;

                    case 2:
                        if (menuGuardado) { //Si hay un menú creado le preguntamos al usuario si quiere sobrescribirlo
                            System.out.println("Ya hay un menú guardado. ¿Quieres sobrescribirlo? (s/n)");
                            String respuesta = sc.next().toLowerCase();

                            //Si el usuario no lo sobrescribe rompemos el switch
                            if (!respuesta.equals("s")) {
                                break;
                            }
                        }

                        //Generamos los elementos del menú de forma aleatoria
                        menu[0] = entrantes[rand.nextInt(entrantes.length)];
                        menu[1] = primeros[rand.nextInt(primeros.length)];
                        menu[2] = segundos[rand.nextInt(segundos.length)];
                        menu[3] = postres[rand.nextInt(postres.length)];

                        System.out.println("El menú generado es:");
                        mostrarMenu(menu); //Llamamos al método para mostrar el menú generado

                        System.out.println("¿Quieres guardar el menú? (s/n)");
                        System.out.println("Si ya hay un menú creado este será sobrescrito sin importar la elección");
                        String respuesta = sc.next().toLowerCase();

                        if (respuesta.equals("s")) {
                            escribirFichero("menu.txt", menu);
                            menuGuardado = true;
                            System.out.println("Menú guardado correctamente.");
                        }
                        break;

                    case 3:
                        System.out.println("¡Hasta luego!");
                        System.exit(opcion); //Cerramos el programa
                        break;

                    default: 
                        System.out.println("Opción no válida."); //Si ninguna de las opciones escogidas por el usuario se corresponde con las anteriores mostramos este mensaje (Solo funciona con números, con letras explota el programa (pendiente de solución))
                        break;
                }
            } 
            while(true); //Si existe el menu
        }
    }

    /* v3 (NO FUNCIONA)
    private static void mostrarMenu(String[] menu) throws FileNotFoundException { //Usamos este método para mostrar el menú generado
         //Bloque try-catch generado automáticamente con una extensión de VSCode
         try (FileReader x = new FileReader("menu.txt")) { //Creamos un FileReader para leer el archivo de texto que contiene el menú generado
            //Retocar System.out.print("menu.txt");
        } catch (FileNotFoundException e) { //Si no se encuentra el archivo lanzamos una excepcion
            throw e;
        } catch (IOException e) { //Si ocurre un error de entrada/salida imprimos mensaje de error
            e.printStackTrace();
        }
    }
    */

    private static void mostrarMenu(String[] menu) throws FileNotFoundException { //Usamos este método para mostrar el menú generado
        String archivo = "menu.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String[] leerFichero(String nombreFichero) { //Usamos este método para leer el menu y almacenar cada línea en un array
        ArrayList<String> lineas = new ArrayList<String>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(nombreFichero)); //Creamos un BufferedReader para leer el archivo de texto y así pasarle el objeto al FileReader
            String linea = br.readLine();

            while (linea != null) {
                lineas.add(linea);
                linea = br.readLine();
            }

            br.close();
        } catch (IOException e) {
            System.out.println("Error al leer el fichero " + nombreFichero + ": " + e.getMessage());
        }

        String[] resultado = new String[lineas.size()];

        for (int i = 0; i < lineas.size(); i++) {
            resultado[i] = lineas.get(i);
        }

        return resultado;
    }

    public static void escribirFichero(String nombreFichero, String[] lineas) { //Método para sobrescribir en el fichero de texto
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(nombreFichero));

            for (int i = 0; i < lineas.length; i++) {
                pw.println(lineas[i]);
            }

            pw.close();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
