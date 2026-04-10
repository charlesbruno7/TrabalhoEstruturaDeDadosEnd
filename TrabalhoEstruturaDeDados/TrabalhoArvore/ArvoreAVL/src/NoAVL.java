public class NoAVL {
    int valor;
    NoAVL esquerda;
    NoAVL direita;
    int altura;

    public NoAVL(int valor) {
        this.valor = valor;
        this.esquerda = null;
        this.direita = null;
        this.altura = 1;
    }
}