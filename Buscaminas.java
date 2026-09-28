public class Buscaminas {
    
    static int numAleatorio(int[] rango) {
        return (int)(Math.random() * (rango[1] - rango[0]) + 1) + rango[0];
    }

    static void mostrarArray(int[][] array) {

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                
                System.out.printf("%d ", array[i][j]);
            }
            System.out.print("\n"); 
        }
    }

    static void llenarArray(int[][] array) {
        int[] rangoPorcentaje = {12, 21};
        int cantCasillas = array.length * array[0].length; 
        int porcentaje = numAleatorio(rangoPorcentaje);
        int cantBombas = (int)(cantCasillas * (porcentaje / 100.));
        int cantBombPuestas = 0;
        
        System.out.println(porcentaje);
        System.out.println(cantCasillas);
        System.out.println(cantBombas);

        while (cantBombPuestas < cantBombas) {
            int cordY = numAleatorio([0, array.length - 1]);
            int cordX = numAleatorio([0, array[0].length - 1]);

            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    
                    if (i == cordY && j == cordX) {
                        if (array[i][j] != 1) {
                            array[i][j] = 1;
                            cantBombPuestas += 1;
                        }
                    } else {
                        array[i][j] = 0;
                    }
                }
            }
        }      
    } 

    public static void main(String[] args) {

        int sizeX = 10;
        int sizeY = 10;
        int[][] arraySolucion = new int[sizeY][sizeX];
        String[][] arrayVisible = new String[sizeY][sizeX]; //☢ ▢

        llenarArray(arraySolucion);
        mostrarArray(arraySolucion);
    }
}