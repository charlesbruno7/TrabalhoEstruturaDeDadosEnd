public class ArvoreBinariaDeBusca {
    No raiz;

    public ArvoreBinariaDeBusca() {
        this.raiz = null;
    }

    // Inserção
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

    // Remoção
    public void remover(int valor) {
        raiz = remover(raiz, valor);
    }

    private No remover(No no, int valor) {
        if (no == null) {
            return null;
        }

        if (valor < no.valor) {
            no.esquerda = remover(no.esquerda, valor);
        } else if (valor > no.valor) {
            no.direita = remover(no.direita, valor);
        } else {
            if (no.esquerda == null) {
                return no.direita;
            } else if (no.direita == null) {
                return no.esquerda;
            }

            No temp = encontrarMinimo(no.direita);
            no.valor = temp.valor;
            no.direita = remover(no.direita, temp.valor);
        }

        return no;
    }

    // Busca
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

    // Cálculo de altura
    public int altura() {
        return altura(raiz);
    }

    private int altura(No no) {
        if (no == null) {
            return 0;
        }

        return 1 + Math.max(altura(no.esquerda), altura(no.direita));
    }

    private No encontrarMinimo(No no) {
        while (no.esquerda != null) {
            no = no.esquerda;
        }
        return no;
    }
}
