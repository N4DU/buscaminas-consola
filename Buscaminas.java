import java.util.Scanner;

public class Buscaminas {
    
    static int numAleatorio(int[] rango) {
        return (int)(Math.random() * (rango[1] - rango[0] + 1)) + rango[0];
    }


    static void mostrarArrayVisible(String[][] arrayVisible, String casilla) {
        int numMax_Y = arrayVisible.length;
        int numMax_X = arrayVisible[0].length;
        int digitosMax_Y = Integer.toString(numMax_Y).length();
        int digitosMax_X = Integer.toString(numMax_X).length();
        int spaceFinalSiempre = 1;


        int digiTotal = ((digitosMax_X > casilla.length()) ? digitosMax_X : casilla.length()) + spaceFinalSiempre;
        int spaceBetwCorchetes = digiTotal - casilla.length();

        // Coordenadas superiores.
        System.out.print(" ".repeat(digitosMax_Y + spaceBetwCorchetes + 1));
        for (int i = 0; i < numMax_X; i++) {

            System.out.printf("%d%s", i+1, " ".repeat(digiTotal - (Integer.toString(i+1).length())));
        }
        System.out.print("(x)\n");

        // Línea divisora 
        System.out.print(" ".repeat(digitosMax_Y + spaceBetwCorchetes));
        for (int i = 0; i < (digiTotal * numMax_X); i++) {

            System.out.print("-");
        }
        System.out.print("\n".repeat(Math.max(spaceBetwCorchetes, 1)));


        for (int i = 0; i < numMax_Y; i++) {

            System.out.printf("%d%s|%s", i+1, " ".repeat(digitosMax_Y - (Integer.toString(i+1).length())), " ".repeat(spaceBetwCorchetes));

            for (int j = 0; j < numMax_X; j++) {
                System.out.printf("%s%s", arrayVisible[i][j], " ".repeat(spaceBetwCorchetes));
            }

            System.out.println(("\n" + " ".repeat(digitosMax_Y) + "|").repeat(spaceBetwCorchetes));
        }

        System.out.print("(y)\n\n");
    }


    static void llenarArraySolucion(int[][] arraySolucion, int[] rangoPorcentaje) {
        int cantCasillas = arraySolucion.length * arraySolucion[0].length; 
        int porcentaje = numAleatorio(rangoPorcentaje);
        int cantBombas = (int)Math.round(cantCasillas * (porcentaje / 100.));
        
        System.out.printf("%d%%\n", porcentaje);
        System.out.printf("%d[]\n", cantCasillas);
        System.out.printf("%dX\n", cantBombas);

        int cantBombPuestas = 0;
        while (cantBombPuestas < cantBombas) {
            int cordY = numAleatorio(new int[] {0, arraySolucion.length - 1});
            int cordX = numAleatorio(new int[] {0, arraySolucion[0].length - 1});

            if (arraySolucion[cordY][cordX] != 1) {
                arraySolucion[cordY][cordX] = 1;
                cantBombPuestas += 1;
            }
        }      
    } 


    static int[] pedirCoordenadas(Scanner sc) {
        System.out.print("(x) (y) si/no:\n");
        String resp = sc.nextLine().toLowerCase();
        String respNum = "";
        int[] nums = new int[2];
        int numCount = 0;

        for (int i = 0; i < resp.length(); i++) {
            char c = resp.charAt(i);

            if (Character.isDigit(c)) {
                respNum += c;
            } else if (!respNum.isEmpty()) {
                nums[numCount] = Integer.parseInt(respNum);
                numCount++;
                respNum = "";
                if (numCount == 2) break;
            }
        }

        // Por si termina en digitos
        if (!respNum.isEmpty() && numCount < 2) {
            nums[numCount] = Integer.parseInt(respNum);
        }

        int siNo = resp.contains("si") ? 1 : 0;

        return new int[]{nums[0] - 1, nums[1] - 1, siNo};
    }


    static int CantBombasAlrededor(int[][] arraySolucion, int[] coordenada) {

        int bombasTotal = 0;
        int[] cordRevisar = {-1, 0, 1};
        
        for (int y : cordRevisar) {
            for (int x : cordRevisar) {

                int ejeY = coordenada[1] + y;
                int ejeX = coordenada[0] + x;

                if ((ejeY < arraySolucion.length && ejeY >= 0) && (ejeX < arraySolucion[0].length && ejeX >= 0)) {
                    if (arraySolucion[ejeY][ejeX] == 1) {
                        bombasTotal += 1;
                    }
                }
            }   
        }

        return bombasTotal;
    }


    static void limpiarAlrededor(String[][] arrayVisible, int[][] arraySolucion, int[] coordSelec, String casilla) {
        int cordX = coordSelec[0];
        int cordY = coordSelec[1];
        int[] cordRevisar = {-1, 1};
        int bombas;
        
        arrayVisible[cordY][cordX] = "0" + " ".repeat(casilla.length() - 1);

        for (int m : cordRevisar) {
            int newCordX = cordX + m;

            if ((newCordX >= 0 && newCordX < arraySolucion[0].length) && !Character.isDigit(arrayVisible[cordY][newCordX].charAt(0))) {
                bombas = CantBombasAlrededor(arraySolucion, new int[] {newCordX, cordY});

                if (bombas >= 1) {
                    arrayVisible[cordY][newCordX] = bombas  + " ".repeat(casilla.length() - 1);
                } else {
                    limpiarAlrededor(arrayVisible, arraySolucion, new int[] {newCordX, cordY}, casilla);
                }
            }
        }

        for (int m : cordRevisar) {
            int newCordY = cordY + m;

            if ((newCordY >= 0 && newCordY < arraySolucion.length) && !Character.isDigit(arrayVisible[newCordY][cordX].charAt(0))) {
                bombas = CantBombasAlrededor(arraySolucion, new int[] {cordX, newCordY});

                if (bombas >= 1) {
                    arrayVisible[newCordY][cordX] = bombas  + " ".repeat(casilla.length() - 1);
                } else {
                    limpiarAlrededor(arrayVisible, arraySolucion, new int[] {cordX, newCordY}, casilla);
                }
            }
        }
    }


    static void modificarArrayVisible(String[][] arrayVisible, int[][] arraySolucion, int[] coordSelec, String casilla, char charBomba) {
        String espacios = " ".repeat(casilla.length() - 1);
        int cordX = coordSelec[0];
        int cordY = coordSelec[1];

        // Poner banderita
        if (coordSelec[2] == 1) {
            arrayVisible[cordY][cordX] = charBomba  + espacios;
            return;
        }

        // Mostrar numero o desplegar :P
        int bombas = CantBombasAlrededor(arraySolucion, coordSelec);

        if (bombas > 0) {
            arrayVisible[cordY][cordX] = bombas  + " ";
        } else {
            limpiarAlrededor(arrayVisible, arraySolucion, coordSelec, casilla);
        }
    }


    static void mostrarArraySolucion(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {

                System.out.printf("%d ", array[i][j]);
            }
            System.out.print("\n");
        }
        System.out.print("\n\n");
    }


    static boolean comprobarGanaste(String[][] arrayVisible, int[][] arraySolucion) {

        for (int i = 0; i < arrayVisible.length; i++) {
            for (int j = 0; j < arrayVisible[0].length; j++) {

                if (!Character.isDigit(arrayVisible[i][j].charAt(0)) && arraySolucion[i][j] != 1) {
                    return false;
                }
            }
        }

        return true;
    }


    static void mostrarSolucion(String[][] arrayVisible, int[][] arraySolucion, String casilla, char charBomba) {
        String espacios = " ".repeat(casilla.length() - 1);

        for (int i = 0; i < arrayVisible.length; i++) {
            for (int j = 0; j < arrayVisible[0].length; j++) {

                if (!Character.isDigit(arrayVisible[i][j].charAt(0))) {
                    
                    if (arraySolucion[i][j] == 0) {
                        arrayVisible[i][j] = Integer.toString(CantBombasAlrededor(arraySolucion, new int[] {j, i})) + espacios;
                    } else {
                        arrayVisible[i][j] = charBomba + espacios;
                    }
                }
            }
        }

        mostrarArrayVisible(arrayVisible, casilla);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String casilla = "[]";
        char charBomba = 'X';

        System.out.println("\nBienvenido a mi buscaminas ;)");
        boolean respInalida;


        // Pedir size de la cuadricula
        int sizeY;
        int sizeX;
        do {
            System.out.print("Ancho cuadrícula: ");
            sizeX = sc.nextInt();
            System.out.print("Alto cuadrícula: ");
            sizeY = sc.nextInt();

            respInalida = sizeX <= 0 || sizeY <= 0;
            if (respInalida) {
                System.out.print("Respuesta inválida\n\n");
            }
        } while (respInalida);
        

        // Pedir nivel de dificultad
        int[][] rangosPorcentaje = {{5, 8}, {10, 13}, {15, 17}, {20, 23}, {27, 33}};
        int nivelElegido;
        do {
            System.out.print("Nivel (1-5): ");
            nivelElegido = sc.nextInt() - 1;

            respInalida = nivelElegido < 0 || nivelElegido > 4;
            if (respInalida) {
                System.out.print("Respuesta inválida\n\n");
            }
        } while (respInalida);

        sc.nextLine(); // Bug, \n queda en el buffer, y cuando pido q escriban se pone enter automatico. Con esto limpio.

        int[][] arraySolucion = new int[sizeY][sizeX];
        String[][] arrayVisible = new String[sizeY][sizeX];


        // Llenar Array Visible
        for (int i = 0; i < sizeY ; i++) {
            for (int j = 0; j < sizeX; j++) {
                arrayVisible[i][j] = casilla;
            }
        }


        llenarArraySolucion(arraySolucion, rangosPorcentaje[nivelElegido]);

        mostrarArrayVisible(arrayVisible, casilla);
        mostrarArraySolucion(arraySolucion);


        // Comienza el juego xDxD
        boolean rangoInvalido;
        int[] coordSelec;
        boolean perdiste = false;

        while (perdiste == false && !comprobarGanaste(arrayVisible, arraySolucion)) {

            // Pide coordenadas y verifica q sean validas
            do {
                coordSelec = pedirCoordenadas(sc);
                rangoInvalido = (coordSelec[0] < 0 || coordSelec[0] >= sizeX) || (coordSelec[1] < 0 || coordSelec[1] >= sizeY);
                if (rangoInvalido) {
                    System.out.println("Rango invalido\n");
                }
            } while (rangoInvalido);

            System.out.print("\n");

            if (arraySolucion[coordSelec[1]][coordSelec[0]] == 0 || coordSelec[2] == 1) {

                modificarArrayVisible(arrayVisible, arraySolucion, coordSelec, casilla, charBomba);
                mostrarArrayVisible(arrayVisible, casilla);
            } else {

                perdiste = true;
                break;
            }
        }

        mostrarSolucion(arrayVisible, arraySolucion, casilla, charBomba);
        if (perdiste) {
            System.out.println("PERDISTE JEJE :3");
        } else {
            System.out.print("¡¡¡GANASTEEEE!!! :D  muchisimas graciass por jugarr :3  atentamente N4DU");
        }

        sc.close();
    }
}