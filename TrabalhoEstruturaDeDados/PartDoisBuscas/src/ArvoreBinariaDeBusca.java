public class ArvoreBinariaDeBusca {
    No raiz;

    public ArvoreBinariaDeBusca() {
        this.raiz = null;
    }

    public void inserir(int valor) {
        raiz = inserir(raiz, valor);
    }

    private No inserir(No no, int valor) {
        if (no == null) {
            return new No(valor);
        }

        if (valor < no.valor) {
            no.esquerda = inserir(no.esquerda, valor);
        } else if (valor > no.valor) {
            no.direita = inserir(no.direita, valor);
        }

        return no;
    }

    public boolean buscar(int valor) {
        return buscar(raiz, valor);
    }

    private boolean buscar(No no, int valor) {
        if (no == null) {
            return false;
        }

        if (valor < no.valor) {
            return buscar(no.esquerda, valor);
        } else if (valor > no.valor) {
            return buscar(no.direita, valor);
        } else {
            return true;
        }
    }
}
