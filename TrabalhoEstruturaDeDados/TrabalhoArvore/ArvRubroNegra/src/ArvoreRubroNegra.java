public class ArvoreRubroNegra {
    NoRubroNegra raiz;
    NoRubroNegra nulo;

    public ArvoreRubroNegra() {
        nulo = new NoRubroNegra(0);
        nulo.cor = false;
        nulo.esquerda = nulo;
        nulo.direita = nulo;
        nulo.pai = null;
        raiz = nulo;
    }

    // Inserção
    public void inserir(int valor) {
        NoRubroNegra no = new NoRubroNegra(valor);
        no.pai = null;
        no.esquerda = nulo;
        no.direita = nulo;

        NoRubroNegra y = null;
        NoRubroNegra x = raiz;

        while (x != nulo) {
            y = x;
            if (no.valor < x.valor) {
                x = x.esquerda;
            } else {
                x = x.direita;
            }
        }

        no.pai = y;
        if (y == null) {
            raiz = no;
        } else if (no.valor < y.valor) {
            y.esquerda = no;
        } else {
            y.direita = no;
        }

        if (no.pai == null) {
            no.cor = false;
            return;
        }

        if (no.pai.pai == null) {
            return;
        }

        fixInserir(no);
    }

    private void fixInserir(NoRubroNegra no) {
        NoRubroNegra y;
        while (no.pai.cor) {
            if (no.pai == no.pai.pai.direita) {
                y = no.pai.pai.esquerda;
                if (y.cor) {
                    y.cor = false;
                    no.pai.cor = false;
                    no.pai.pai.cor = true;
                    no = no.pai.pai;
                } else {
                    if (no == no.pai.esquerda) {
                        no = no.pai;
                        rotacaoDireita(no);
                    }
                    no.pai.cor = false;
                    no.pai.pai.cor = true;
                    rotacaoEsquerda(no.pai.pai);
                }
            } else {
                y = no.pai.pai.direita;
                if (y.cor) {
                    y.cor = false;
                    no.pai.cor = false;
                    no.pai.pai.cor = true;
                    no = no.pai.pai;
                } else {
                    if (no == no.pai.direita) {
                        no = no.pai;
                        rotacaoEsquerda(no);
                    }
                    no.pai.cor = false;
                    no.pai.pai.cor = true;
                    rotacaoDireita(no.pai.pai);
                }
            }

            if (no == raiz) {
                break;
            }
        }
        raiz.cor = false;
    }

    // Remoção
    public void remover(int valor) {
        NoRubroNegra z = buscar(raiz, valor);
        if (z == nulo) {
            return;
        }

        NoRubroNegra x;
        NoRubroNegra y = z;
        boolean yOriginalCor = y.cor;

        if (z.esquerda == nulo) {
            x = z.direita;
            rbTransplant(z, z.direita);
        } else if (z.direita == nulo) {
            x = z.esquerda;
            rbTransplant(z, z.esquerda);
        } else {
            y = encontrarMinimo(z.direita);
            yOriginalCor = y.cor;
            x = y.direita;
            if (y.pai == z) {
                x.pai = y;
            } else {
                rbTransplant(y, y.direita);
                y.direita = z.direita;
                y.direita.pai = y;
            }

            rbTransplant(z, y);
            y.esquerda = z.esquerda;
            y.esquerda.pai = y;
            y.cor = z.cor;
        }

        if (!yOriginalCor) {
            fixRemover(x);
        }
    }

    private void fixRemover(NoRubroNegra x) {
        NoRubroNegra w;
        while (x != raiz && !x.cor) {
            if (x == x.pai.esquerda) {
                w = x.pai.direita;
                if (w.cor) {
                    w.cor = false;
                    x.pai.cor = true;
                    rotacaoEsquerda(x.pai);
                    w = x.pai.direita;
                }

                if (!w.esquerda.cor && !w.direita.cor) {
                    w.cor = true;
                    x = x.pai;
                } else {
                    if (!w.direita.cor) {
                        w.esquerda.cor = false;
                        w.cor = true;
                        rotacaoDireita(w);
                        w = x.pai.direita;
                    }

                    w.cor = x.pai.cor;
                    x.pai.cor = false;
                    w.direita.cor = false;
                    rotacaoEsquerda(x.pai);
                    x = raiz;
                }
            } else {
                w = x.pai.esquerda;
                if (w.cor) {
                    w.cor = false;
                    x.pai.cor = true;
                    rotacaoDireita(x.pai);
                    w = x.pai.esquerda;
                }

                if (!w.direita.cor && !w.esquerda.cor) {
                    w.cor = true;
                    x = x.pai;
                } else {
                    if (!w.esquerda.cor) {
                        w.direita.cor = false;
                        w.cor = true;
                        rotacaoEsquerda(w);
                        w = x.pai.esquerda;
                    }

                    w.cor = x.pai.cor;
                    x.pai.cor = false;
                    w.esquerda.cor = false;
                    rotacaoDireita(x.pai);
                    x = raiz;
                }
            }
        }
        x.cor = false;
    }

    private void rbTransplant(NoRubroNegra u, NoRubroNegra v) {
        if (u.pai == null) {
            raiz = v;
        } else if (u == u.pai.esquerda) {
            u.pai.esquerda = v;
        } else {
            u.pai.direita = v;
        }
        v.pai = u.pai;
    }

    // Busca
    public NoRubroNegra buscar(int valor) {
        return buscar(raiz, valor);
    }

    private NoRubroNegra buscar(NoRubroNegra no, int valor) {
        if (no == nulo || valor == no.valor) {
            return no;
        }

        if (valor < no.valor) {
            return buscar(no.esquerda, valor);
        }
        return buscar(no.direita, valor);
    }

    // Cálculo de altura
    public int altura() {
        return altura(raiz);
    }

    private int altura(NoRubroNegra no) {
        if (no == nulo) {
            return 0;
        }
        return 1 + Math.max(altura(no.esquerda), altura(no.direita));
    }

    private void rotacaoEsquerda(NoRubroNegra x) {
        NoRubroNegra y = x.direita;
        x.direita = y.esquerda;
        if (y.esquerda != nulo) {
            y.esquerda.pai = x;
        }

        y.pai = x.pai;
        if (x.pai == null) {
            raiz = y;
        } else if (x == x.pai.esquerda) {
            x.pai.esquerda = y;
        } else {
            x.pai.direita = y;
        }
        y.esquerda = x;
        x.pai = y;
    }

    private void rotacaoDireita(NoRubroNegra x) {
        NoRubroNegra y = x.esquerda;
        x.esquerda = y.direita;
        if (y.direita != nulo) {
            y.direita.pai = x;
        }

        y.pai = x.pai;
        if (x.pai == null) {
            raiz = y;
        } else if (x == x.pai.direita) {
            x.pai.direita = y;
        } else {
            x.pai.esquerda = y;
        }
        y.direita = x;
        x.pai = y;
    }

    private NoRubroNegra encontrarMinimo(NoRubroNegra no) {
        while (no.esquerda != nulo) {
            no = no.esquerda;
        }
        return no;
    }
}
