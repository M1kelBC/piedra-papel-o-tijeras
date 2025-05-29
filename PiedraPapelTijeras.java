import java.util.Random;
import java.util.Scanner;

public class PiedraPapelTijeras {
    private static final String[] OPCIONES = {"PIEDRA", "PAPEL", "TIJERAS"};
    private static final Random random = new Random();
    private static final Scanner scanner = new Scanner(System.in);

    private int victoriasJugador = 0;
    private int victoriasComputadora = 0;
    private int empates = 0;

    public static void main(String[] args) {
        PiedraPapelTijeras juego = new PiedraPapelTijeras();
        juego.iniciarJuego();
    }

    public void iniciarJuego() {
        System.out.println("¡Bienvenido a Piedra, Papel o Tijeras!");
        System.out.println("Opciones válidas: PIEDRA, PAPEL o TIJERAS");
        System.out.println("Escribe 'SALIR' para terminar el juego.\n");

        while (true) {
            System.out.print("Elige tu jugada: ");
            String input = scanner.nextLine().toUpperCase();

            if (input.equals("SALIR")) {
                mostrarEstadisticas();
                break;
            }

            if (!esJugadaValida(input)) {
                System.out.println("Opción inválida. Intenta nuevamente.\n");
                continue;
            }

            String jugadaComputadora = generarJugadaComputadora();
            System.out.println("Computadora eligió: " + jugadaComputadora);

            String resultado = determinarGanador(input, jugadaComputadora);
            actualizarEstadisticas(resultado);

            System.out.println("Resultado: " + resultado + "\n");
        }

        scanner.close();
    }

    private boolean esJugadaValida(String jugada) {
        for (String opcion : OPCIONES) {
            if (opcion.equals(jugada)) {
                return true;
            }
        }
        return false;
    }

    private String generarJugadaComputadora() {
        int indice = random.nextInt(OPCIONES.length);
        return OPCIONES[indice];
    }

    private String determinarGanador(String jugadaJugador, String jugadaComputadora) {
        if (jugadaJugador.equals(jugadaComputadora)) {
            return "¡Empate!";
        }

        switch (jugadaJugador) {
            case "PIEDRA":
                return jugadaComputadora.equals("TIJERAS") ? "¡Ganaste!" : "¡La computadora gana!";
            case "PAPEL":
                return jugadaComputadora.equals("PIEDRA") ? "¡Ganaste!" : "¡La computadora gana!";
            case "TIJERAS":
                return jugadaComputadora.equals("PAPEL") ? "¡Ganaste!" : "¡La computadora gana!";
            default:
                return "Error inesperado";
        }
    }

    private void actualizarEstadisticas(String resultado) {
        if (resultado.contains("Ganaste")) {
            victoriasJugador++;
        } else if (resultado.contains("computadora")) {
            victoriasComputadora++;
        } else {
            empates++;
        }
    }

    private void mostrarEstadisticas() {
        System.out.println("\n--- Estadísticas Finales ---");
        System.out.println("Victorias tuyas: " + victoriasJugador);
        System.out.println("Victorias de la computadora: " + victoriasComputadora);
        System.out.println("Empates: " + empates);
        System.out.println("\n¡Gracias por jugar!");
    }
}
