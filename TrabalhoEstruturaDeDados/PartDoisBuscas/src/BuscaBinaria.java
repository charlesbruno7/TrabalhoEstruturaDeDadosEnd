public class BuscaBinaria {
    public static int buscar(int[] array, int valor) {
        int esquerda = 0;
        int direita = array.length - 1;
        while (esquerda <= direita) {
            int meio = (esquerda + direita) / 2;
            if (array[meio] == valor) {
                return meio;
            } else if (array[meio] < valor) {
                esquerda = meio + 1;
            } else {
                direita = meio - 1;
            }
        }
        return -1; // não encontrado
    }
}