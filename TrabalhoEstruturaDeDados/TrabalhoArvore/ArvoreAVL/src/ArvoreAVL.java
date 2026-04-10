public class ArvoreAVL {
    NoAVL raiz;

    public ArvoreAVL() {
        this.raiz = null;
    }

    // Inserção
    public void inserir(int valor) {
        raiz = inserir(raiz, valor);
    }

    private NoAVL inserir(NoAVL no, int valor) {
        if (no == null) {
            return new NoAVL(valor);
        }

        if (valor < no.valor) {
            no.esquerda = inserir(no.esquerda, valor);
        } else if (valor > no.valor) {
            no.direita = inserir(no.direita, valor);
        }

        no.altura = 1 + Math.max(getAltura(no.esquerda), getAltura(no.direita));

        int fatorBalanceamento = getFatorBalanceamento(no);

        // Rotação à esquerda
        if (fatorBalanceamento < -1 && valor > no.direita.valor) {
            return rotacaoEsquerda(no);
        }

        // Rotação à direita
        if (fatorBalanceamento > 1 && valor < no.esquerda.valor) {
            return rotacaoDireita(no);
        }

        // Rotação à esquerda-direita
        if (fatorBalanceamento < -1 && valor < no.direita.valor) {
            no.direita = rotacaoDireita(no.direita);
            return rotacaoEsquerda(no);
        }

        // Rotação à direita-esquerda
        if (fatorBalanceamento > 1 && valor > no.esquerda.valor) {
            no.esquerda = rotacaoEsquerda(no.esquerda);
            return rotacaoDireita(no);
        }

        return no;
    }

    // Remoção
    public void remover(int valor) {
        raiz = remover(raiz, valor);
    }

    private NoAVL remover(NoAVL no, int valor) {
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

            NoAVL temp = encontrarMinimo(no.direita);
            no.valor = temp.valor;
            no.direita = remover(no.direita, temp.valor);
        }

        if (no == null) {
            return no;
        }

        no.altura = 1 + Math.max(getAltura(no.esquerda), getAltura(no.direita));

        int fatorBalanceamento = getFatorBalanceamento(no);

        // Rotação à esquerda
        if (fatorBalanceamento < -1 && getFatorBalanceamento(no.direita) <= 0) {
            return rotacaoEsquerda(no);
        }

        // Rotação à direita
        if (fatorBalanceamento > 1 && getFatorBalanceamento(no.esquerda) >= 0) {
            return rotacaoDireita(no);
        }

        // Rotação à esquerda-direita
        if (fatorBalanceamento < -1 && getFatorBalanceamento(no.direita) > 0) {
            no.direita = rotacaoDireita(no.direita);
            return rotacaoEsquerda(no);
        }

        // Rotação à direita-esquerda
        if (fatorBalanceamento > 1 && getFatorBalanceamento(no.esquerda) < 0) {
            no.esquerda = rotacaoEsquerda(no.esquerda);
            return rotacaoDireita(no);
        }

        return no;
    }

    // Busca
    public boolean buscar(int valor) {
        return buscar(raiz, valor);
    }

    private boolean buscar(NoAVL no, int valor) {
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
        return getAltura(raiz);
    }

    private int getAltura(NoAVL no) {
        if (no == null) {
            return 0;
        }
        return no.altura;
    }

    private int getFatorBalanceamento(NoAVL no) {
        if (no == null) {
            return 0;
        }
        return getAltura(no.esquerda) - getAltura(no.direita);
    }

    private NoAVL rotacaoEsquerda(NoAVL no) {
        NoAVL temp = no.direita;
        no.direita = temp.esquerda;
        temp.esquerda = no;

        no.altura = 1 + Math.max(getAltura(no.esquerda), getAltura(no.direita));
        temp.altura = 1 + Math.max(getAltura(temp.esquerda), getAltura(temp.direita));

        return temp;
    }

    private NoAVL rotacaoDireita(NoAVL no) {
        NoAVL temp = no.esquerda;
        no.esquerda = temp.direita;
        temp.direita = no;

        no.altura = 1 + Math.max(getAltura(no.esquerda), getAltura(no.direita));
        temp.altura = 1 + Math.max(getAltura(temp.esquerda), getAltura(temp.direita));

        return temp;
    }

    private NoAVL encontrarMinimo(NoAVL no) {
        while (no.esquerda != null) {
            no = no.esquerda;
        }
        return no;
    }
}