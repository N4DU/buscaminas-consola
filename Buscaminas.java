public class Buscaminas {
    
    static int numAleatorio(int[] rango) {
        return (int)(Math.random() * (rango[1] - rango[0]) + 1) + rango[0];
    }

    static void mostrarArray(String[][] array) {

        for (int i = 0; i < array.length; i++) {

            int indice = i + 1;
            System.out.printf("%s", indice);
            for (int j = 0; j < (((String)indice)).length; j++) {
                System.out.print(" ");
            }

            for (int j = 0; j < array[i].length; j++) {
                System.out.printf("%s", array[i][j]);
            }
            System.out.print("\n"); 
        }
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
    }
}