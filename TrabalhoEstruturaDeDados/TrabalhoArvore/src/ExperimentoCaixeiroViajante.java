import java.util.Random;

public class ExperimentoCaixeiroViajante {
    private static final int EXECUCOES = 30;

    public static void main(String[] args) {
        int[] tamanhos = {10, 30, 50};
        Random random = new Random(42);

        for (int tamanho : tamanhos) {
            double[] tempos = new double[EXECUCOES];
            double[] distancias = new double[EXECUCOES];

            for (int execucao = 0; execucao < EXECUCOES; execucao++) {
                double[][] cidades = gerarCidades(tamanho, random);

                long inicio = System.nanoTime();
                distancias[execucao] = CaixeiroViajanteHeuristica.resolver(cidades);
                tempos[execucao] = System.nanoTime() - inicio;
            }

            double mediaTempo = calcularMedia(tempos);
            double desvioTempo = calcularDesvioPadrao(tempos, mediaTempo);
            double mediaDistancia = calcularMedia(distancias);
            double desvioDistancia = calcularDesvioPadrao(distancias, mediaDistancia);

            System.out.println("Tamanho: " + tamanho + " cidades");
            System.out.printf("Tempo -> média: %.2f ns | desvio padrão: %.2f ns%n", mediaTempo, desvioTempo);
            System.out.printf("Distância -> média: %.2f | desvio padrão: %.2f%n%n", mediaDistancia, desvioDistancia);
        }
    }

    private static double[][] gerarCidades(int quantidade, Random random) {
        double[][] cidades = new double[quantidade][2];
        for (int i = 0; i < quantidade; i++) {
            cidades[i][0] = random.nextDouble() * 1000;
            cidades[i][1] = random.nextDouble() * 1000;
        }
        return cidades;
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
