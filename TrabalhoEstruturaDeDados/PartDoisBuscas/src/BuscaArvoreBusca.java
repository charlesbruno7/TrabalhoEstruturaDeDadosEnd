public class BuscaArvoreBusca {
    private ArvoreBinariaDeBusca arvore;

    public BuscaArvoreBusca() {
        arvore = new ArvoreBinariaDeBusca();
    }

    public void inserir(int valor) {
        arvore.inserir(valor);
    }

    public boolean buscar(int valor) {
        return arvore.buscar(valor);
    }
}