import java.util.Random;

public class Main {
    private static final int EXECUCOES = 30;

    public static void main(String[] args) {
        int[] tamanhos = {100, 1000, 3000};
        Random random = new Random(42);

        for (int tamanho : tamanhos) {
            double[] bubbleMelhor = new double[EXECUCOES];
            double[] bubbleMedio = new double[EXECUCOES];
            double[] bubblePior = new double[EXECUCOES];
            double[] insertionMelhor = new double[EXECUCOES];
            double[] insertionMedio = new double[EXECUCOES];
            double[] insertionPior = new double[EXECUCOES];

            for (int execucao = 0; execucao < EXECUCOES; execucao++) {
                int[] arrayMelhorCaso = gerarMelhorCaso(tamanho);
                int[] arrayCasoMedio = gerarCasoMedio(tamanho, random);
                int[] arrayPiorCaso = gerarPiorCaso(tamanho);

                int[] arrayBubbleMelhor = arrayMelhorCaso.clone();
                int[] arrayBubbleMedio = arrayCasoMedio.clone();
                int[] arrayBubblePior = arrayPiorCaso.clone();
                int[] arrayInsertionMelhor = arrayMelhorCaso.clone();
                int[] arrayInsertionMedio = arrayCasoMedio.clone();
                int[] arrayInsertionPior = arrayPiorCaso.clone();

                long inicio = System.nanoTime();
                BubbleSort.ordenar(arrayBubbleMelhor);
                bubbleMelhor[execucao] = System.nanoTime() - inicio;

                inicio = System.nanoTime();
                BubbleSort.ordenar(arrayBubbleMedio);
                bubbleMedio[execucao] = System.nanoTime() - inicio;

                inicio = System.nanoTime();
                BubbleSort.ordenar(arrayBubblePior);
                bubblePior[execucao] = System.nanoTime() - inicio;

                inicio = System.nanoTime();
                InsertionSort.ordenar(arrayInsertionMelhor);
                insertionMelhor[execucao] = System.nanoTime() - inicio;

                inicio = System.nanoTime();
                InsertionSort.ordenar(arrayInsertionMedio);
                insertionMedio[execucao] = System.nanoTime() - inicio;

                inicio = System.nanoTime();
                InsertionSort.ordenar(arrayInsertionPior);
                insertionPior[execucao] = System.nanoTime() - inicio;
            }

            System.out.println("Tamanho: " + tamanho);
            imprimirEstatisticas("Bubble Sort - melhor caso", bubbleMelhor);
            imprimirEstatisticas("Bubble Sort - caso médio", bubbleMedio);
            imprimirEstatisticas("Bubble Sort - pior caso", bubblePior);
            imprimirEstatisticas("Insertion Sort - melhor caso", insertionMelhor);
            imprimirEstatisticas("Insertion Sort - caso médio", insertionMedio);
            imprimirEstatisticas("Insertion Sort - pior caso", insertionPior);
            System.out.println();
        }
    }

    private static int[] gerarMelhorCaso(int tamanho) {
        int[] array = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            array[i] = i;
        }
        return array;
    }

    private static int[] gerarCasoMedio(int tamanho, Random random) {
        int[] array = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            array[i] = random.nextInt(tamanho * 10);
        }
        return array;
    }

    private static int[] gerarPiorCaso(int tamanho) {
        int[] array = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            array[i] = tamanho - i;
        }
        return array;
    }

    private static void imprimirEstatisticas(String nome, double[] valores) {
        double media = calcularMedia(valores);
        double desvioPadrao = calcularDesvioPadrao(valores, media);
        System.out.printf("%s -> média: %.2f ns | desvio padrão: %.2f ns%n", nome, media, desvioPadrao);
    }

    private static double calcularMedia(double[] valores) {
        double soma = 0;
        for (double valor : valores) {
            soma += valor;
        }
        return soma / valores.length;
    }

    private static double calcularDesvioPadrao(double[] valores, double media) {
        double soma = 0;
        for (double valor : valores) {
            double diferenca = valor - media;
            soma += diferenca * diferenca;
        }
        return Math.sqrt(soma / valores.length);
    }
}
