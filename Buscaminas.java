public class Buscaminas {
    
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
        int porcentaje = (int)(Math.random() * (rangoPorcentaje[1] - rangoPorcentaje[0]) + 1) + rangoPorcentaje[0];
        int cantBombas = (int)(cantCasillas * (porcentaje / 100.));
        
        System.out.println(porcentaje);
        System.out.println(cantCasillas);
        System.out.println(cantBombas);

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                
                array[i][j] = j;  
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