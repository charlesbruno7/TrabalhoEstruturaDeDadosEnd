public class BuscaSequencial {
    public static int buscar(int[] array, int valor) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == valor) {
                return i;
            }
        }
        return -1; // não encontrado
    }
}