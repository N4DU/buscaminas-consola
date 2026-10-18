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
        System.out.print("\n\n(y)");
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

    public static void main(String[] args) {

        int sizeY = 10;
        int sizeX = 9;
        int[][] arraySolucion = new int[sizeY][sizeX];
        String[][] arrayVisible = new String[sizeY][sizeX]; 

        for (int i = 0; i < sizeY ; i++) {
            for (int j = 0; j < sizeX; j++) {
                arrayVisible[i][j] = "[]";
            }
        }
        llenarArraySolucion(arraySolucion);
        mostrarArray(arrayVisible);
    }
}