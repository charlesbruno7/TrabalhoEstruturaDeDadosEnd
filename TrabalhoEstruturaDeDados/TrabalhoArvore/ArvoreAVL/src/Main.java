public class Main {
    public static void main(String[] args) {
        ArvoreAVL arvore = new ArvoreAVL();

        arvore.inserir(5);
        arvore.inserir(3);
        arvore.inserir(7);
        arvore.inserir(2);
        arvore.inserir(4);
        arvore.inserir(6);
        arvore.inserir(8);

        System.out.println("Altura da árvore: " + arvore.altura());

        System.out.println("Busca por 6: " + arvore.buscar(6));
        System.out.println("Busca por 9: " + arvore.buscar(9));

        arvore.remover(7);

        System.out.println("Altura da árvore após remoção: " + arvore.altura());
        //Altura da árvore: 3
        //Busca por 6: true
        //Busca por 9: false
        //Altura da árvore após remoção: 3
    }
}