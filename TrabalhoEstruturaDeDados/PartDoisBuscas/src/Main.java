import java.util.Random;

public class Main {
    private static final int EXECUCOES = 30;
    private static final int CONSULTAS_POR_EXECUCAO = 1000;

    public static void main(String[] args) {
        int[] tamanhos = {100, 1000, 10000};
        Random random = new Random(42);

        for (int tamanho : tamanhos) {
            int[] array = new int[tamanho];
            for (int i = 0; i < tamanho; i++) {
                array[i] = i;
            }

            int[] ordemInsercao = array.clone();
            embaralhar(ordemInsercao, random);

            BuscaArvoreBusca arvore = new BuscaArvoreBusca();
            for (int valor : ordemInsercao) {
                arvore.inserir(valor);
            }

            double[] temposSequencial = new double[EXECUCOES];
            double[] temposBinaria = new double[EXECUCOES];
            double[] temposArvore = new double[EXECUCOES];

            for (int execucao = 0; execucao < EXECUCOES; execucao++) {
                int[] consultas = new int[CONSULTAS_POR_EXECUCAO];
                for (int i = 0; i < CONSULTAS_POR_EXECUCAO; i++) {
                    consultas[i] = random.nextInt(tamanho);
                }

                long inicio = System.nanoTime();
                for (int consulta : consultas) {
                    BuscaSequencial.buscar(array, consulta);
                }
                temposSequencial[execucao] = System.nanoTime() - inicio;

                inicio = System.nanoTime();
                for (int consulta : consultas) {
                    BuscaBinaria.buscar(array, consulta);
                }
                temposBinaria[execucao] = System.nanoTime() - inicio;

                inicio = System.nanoTime();
                for (int consulta : consultas) {
                    arvore.buscar(consulta);
                }
                temposArvore[execucao] = System.nanoTime() - inicio;
            }

            System.out.println("Tamanho: " + tamanho);
            imprimirEstatisticas("Busca sequencial", temposSequencial);
            imprimirEstatisticas("Busca binária", temposBinaria);
            imprimirEstatisticas("Busca em árvore", temposArvore);
            System.out.println();
        }
    }

    private static void embaralhar(int[] array, Random random) {
        for (int i = array.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
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
