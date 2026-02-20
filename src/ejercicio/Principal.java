package ejercicio;
import java.util.Scanner;
public class Principal {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        int posicion = 0;
        boolean finjuego = false, valido = false;
        int galaxia = 0, galaxianueva, numero;
        String opcion, confirmacion;

        while (!valido) {
            MenuInicio();
            opcion = sc.nextLine();
            switch (opcion) {
                case "1":
                    valido = true;
                    System.out.println("\n Comienza el juego \n");
                    break;
                case "2":
                    instrucciones();
                    break;
                case "3":
                    finjuego = true;
                    valido = true;
                    break;
                default:
                    System.out.println("Introduce un opcion valida");
                    break;
            }
        }
        while (!finjuego) {
            tablero(posicion);
            if (!finjuego) {
                System.out.println("Tirar los dados? Si/No");
                confirmacion = sc.nextLine().toLowerCase();

                switch (confirmacion) {
                    case "no":
                        System.out.println("Decides salir del juego.");
                        finjuego = true;
                        break;
                    case "si", "sí":
                        galaxianueva = dado();
                        System.out.println("¡Has encontrado la galaxia " + galaxianueva + "!");
                        numero = galaxias(galaxianueva);
                        if (AvancePosicion(numero, galaxia) == 0 || AvancePosicion(numero, galaxia) > 4) {
                            System.out.println("La galaxia se encuentra a " + AvancePosicion(numero, galaxia) + " años luz.\nNo avanzas ninguna casilla.");
                        } else {
                            posicion += AvancePosicion(numero, galaxia);
                            System.out.println("La galaxia se encuentra a " + AvancePosicion(numero, galaxia) + " años luz.\nAvanzas " + AvancePosicion(numero, galaxia) + " casillas.");
                        }
                        switch (posicion) {
                            case 31:
                                System.out.println("Te encuentras con unos alienigenas que te hacen retroceder.\nRetrocedes a la casilla 13");
                                posicion = 13;
                                break;
                            case 33:
                                System.out.println("Caes en un agujero negro del que no puedes escapar.");
                                finjuego = true;
                                break;
                            case 41:
                                System.out.println("¡Enhorabuena! ¡Has superado el juego!");
                                finjuego = true;
                            default:
                                if (posicion > 41) {
                                    System.out.println("¡Te has pasado!\n¡Vuelves al inicio!");
                                    posicion = posicion-45;
                                }
                                break;
                        }
                        galaxia = numero;
                        break;
                    default:
                        System.out.println("Introduce una opción valida");
                        break;
                }
            }
        }
        System.out.println("FIN DEL JUEGO.");
        sc.close();
    }

    public static void instrucciones() {
        System.out.println("\n --INSTRUCCIONES--");
        System.out.println("\nLas galaxias se representan por direcciones de 3 dígitos y el jugador comienza en la casilla 1 en la galaxia con dirección 000.\n"
                + "El viajero lanza los 3 dados al mismo tiempo para obtener una dirección y el resultado es la dirección de la galaxia.\n"
                + "El viajero puede ir avanzando mientras las galaxias sean cercanas. Son cercanas si se encuentran menos o igual a 4 años luz.");
        System.out.println("\n--¿Cómo proceder?--\n");
        System.out.println("Por ejemplo:\n"
                + "Galaxia 000 → 0+0+0=0\n"
                + "Galaxia 184 → 1+8+4 = 13 → 1+ 3 = 4\n"
                + "Galaxia 231 → 2+3+1=6\n");
        System.out.println("Las galaxias son cercanas porque calculamos la diferencia de las dos últimas tirdas 4-0 = 4 y avanzas 4 casillas. Me encuentro en la casilla 5\n"
                + "La siguiente tirada será:\n"
                + " ");
        System.out.println("Galaxia 184 → 1+8+4 = 13 → 1+ 3 = 4\n"
                + "Galaxia 231 → 2+3+1=6\n"
                + "Cuya diferencia será 6-4=2. Avanzamos a la casilla 7 y así sucesivamente\n");
        System.out.println("--¡¡¡PELIGROS!!!--\n");
        System.out.println("Si un jugador cae en la casilla 31, se encontrará con unos extraterrestres peligrosos haciendo que le retrasen a la casilla 13\n"
                + "\nSi un jugador cae en la casilla 33, se encontrará un agujero negro y perderá el juego\n");
        System.out.println("--FIN del juego--\n");
        System.out.println("El juego termina si llega a la casilla 42. Si la sobrepasa, deberá volver a la casilla 1. También terminará si cae en el agujero negro.\n");
        System.out.println("La X en el tablero indica tu posicion\n");
    }

    public static int dado() {
        int tiradas=0;
        String dado = "";
        for (int i = 0; i < 3; i++) {
            tiradas=(int) (1 + Math.random() * 9);
            dado += "" + tiradas;
            System.out.println("El "+(i+1)+"º dado es "+ tiradas);
        }

        return Integer.parseInt(dado);
    }

    public static int galaxias(int numero) {
        int resultado = 0;
        while (numero != 0) {
            resultado += numero % 10;
            numero = numero / 10;
        }
        if (resultado < 10) {
            return resultado;
        }

        return galaxias(resultado);
    }

    public static void MenuInicio() {
        System.out.println("BIENVENIDOS A LA GUÍA DEL VIAJERO INTERGALÁCTICO\n1.Comenzar\n2.Instrucciones\n3.Salir.\n\nElige una opcion");
    }

    public static int AvancePosicion(int numero, int galaxia) {
        int avance = 0;
        if (numero > galaxia) {
            avance = numero - galaxia;
        } else {
            avance = galaxia - numero;
        }
        return avance;
    }

    public static void tablero(int pos) {
        int contador = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (contador == pos) {
                    System.out.print("[X]\t");
                } else {
                    switch (contador) {
                        case 0:
                            System.out.print("[Inicio]");
                            break;
                        case 31:
                            System.out.print("[Aliens]");
                            break;
                        case 33:
                            System.out.print("[Agujero]");
                            break;
                        case 41:
                            System.out.println("[META]");
                            break;
                        default:
                            System.out.print("[" + contador + "]\t");
                            break;
                    }
                }
                contador++;
            }
            System.out.println();
        }
    }
}
