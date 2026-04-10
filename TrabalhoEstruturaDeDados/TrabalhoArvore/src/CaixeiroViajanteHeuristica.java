public class CaixeiroViajanteHeuristica {
    public static double resolver(double[][] cidades) {
        int quantidade = cidades.length;
        if (quantidade == 0) {
            return 0;
        }

        boolean[] visitada = new boolean[quantidade];
        int atual = 0;
        visitada[atual] = true;
        double distanciaTotal = 0;

        for (int visitadas = 1; visitadas < quantidade; visitadas++) {
            int proxima = -1;
            double melhorDistancia = Double.MAX_VALUE;

            for (int i = 0; i < quantidade; i++) {
                if (!visitada[i]) {
                    double distancia = distancia(cidades[atual], cidades[i]);
                    if (distancia < melhorDistancia) {
                        melhorDistancia = distancia;
                        proxima = i;
                    }
                }
            }

            visitada[proxima] = true;
            distanciaTotal += melhorDistancia;
            atual = proxima;
        }

        distanciaTotal += distancia(cidades[atual], cidades[0]);
        return distanciaTotal;
    }

    private static double distancia(double[] cidadeA, double[] cidadeB) {
        double dx = cidadeA[0] - cidadeB[0];
        double dy = cidadeA[1] - cidadeB[1];
        return Math.sqrt(dx * dx + dy * dy);
    }
}
