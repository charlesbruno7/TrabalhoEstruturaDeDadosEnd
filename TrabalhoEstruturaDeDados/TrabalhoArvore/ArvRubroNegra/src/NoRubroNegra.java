public class NoRubroNegra {
    int valor;
    NoRubroNegra esquerda;
    NoRubroNegra direita;
    NoRubroNegra pai;
    boolean cor; // true para vermelho, false para negro

    public NoRubroNegra(int valor) {
        this.valor = valor;
        this.esquerda = null;
        this.direita = null;
        this.pai = null;
        this.cor = true; // novo nó é vermelho
    }
}