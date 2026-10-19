import java.util.Arrays;
import java.util.Scanner;

public class Buscaminas {
    
    static int numAleatorio(int[] rango) {
        return (int)(Math.random() * (rango[1] - rango[0]) + 1) + rango[0];
    }

    static void mostrarArray(String[][] array) {
        int numMax_Y = array.length;
        int numMax_X = array[0].length;

        System.out.print(" ".repeat(Integer.toString(numMax_Y).length() + 1));
        for (int i = 0; i < numMax_X; i++) {

            int digitosMax_X = Integer.toString(numMax_X).length();
            System.out.printf("%d%s", i+1, " ".repeat(Math.max(((digitosMax_X > 1) ? digitosMax_X + 1 : digitosMax_X + 2) - (Integer.toString(i+1).length()), 1)));
        }
        System.out.print(" (x)\n\n");
        int spaceBetw_X = Math.max(Integer.toString(numMax_X).length() - 1, 1);

        for (int i = 0; i < numMax_Y; i++) {

            System.out.printf("%d%s", i+1, " ".repeat(Integer.toString(numMax_Y).length() + 1 - (Integer.toString(i+1).length())));

            for (int j = 0; j < numMax_X; j++) {
                System.out.printf("%s%s", array[i][j], " ".repeat(spaceBetw_X));
            }

            if (i < numMax_Y - 1) {
                System.out.print("\n".repeat(spaceBetw_X + 1)); 
            }
        }
        System.out.print("\n\n(y)\n");
    }

    static void llenarArraySolucion(int[][] array) {
        int[] rangoPorcentaje = {12, 21};
        int cantCasillas = array.length * array[0].length; 
        int porcentaje = numAleatorio(rangoPorcentaje);
        int cantBombas = (int)(cantCasillas * (porcentaje / 100.));
        
        System.out.println(porcentaje);
        System.out.println(cantCasillas);
        System.out.println(cantBombas);

        int cantBombPuestas = 0;
        while (cantBombPuestas < cantBombas) {
            int cordY = numAleatorio(new int[] {0, array.length - 1});
            int cordX = numAleatorio(new int[] {0, array[0].length - 1});

            if (array[cordY][cordX] != 1) {
                array[cordY][cordX] = 1;
                cantBombPuestas += 1;
            }
        }      
    } 

    static int[] pedirCoordenadas(Scanner sc) {
        System.out.print("\n\n(x) (y) si/no:\n");
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

        return new int[]{nums[0], nums[1], siNo};
    }

    static int CantBombasAlrededor(int[][] arraySolucion, int[] coordenada) {
        int bombasTotal = 0;
        int[] cordRevisar = {-1, 0, 1};
        
        for (int i = 0; i < 3; i++) {
            for (int n : cordRevisar) {

                int ejeY = coordenada[1] + n;
                int ejeX = coordenada[0] + n;

                if (arraySolucion[ejeY][ejeX] == 1) {
                    bombasTotal += 1;
                }
            }   
        }

        return bombasTotal;
    }

    static void mostrarArraySolucion(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {

                System.out.printf("%d ", array[i][j]);
            }
            System.out.print("\n");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int sizeY = 10;
        int sizeX = 10;
        int[][] arraySolucion = new int[sizeY][sizeX];
        String[][] arrayVisible = new String[sizeY][sizeX]; 

        for (int i = 0; i < sizeY ; i++) {
            for (int j = 0; j < sizeX; j++) {
                arrayVisible[i][j] = "[]";
            }
        }
        llenarArraySolucion(arraySolucion);

        mostrarArray(arrayVisible);
        System.out.print("\n\n");
        mostrarArraySolucion(arraySolucion);
        System.out.print("\n\n");

        System.out.print(CantBombasAlrededor(arraySolucion, pedirCoordenadas(sc)));
    }
}