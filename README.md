Este trabalho prático da disciplina de **Estruturas, Pesquisa e Ordenação de Dados** tem como objetivo consolidar os conhecimentos fundamentais em estruturas de dados, algoritmos de busca e ordenação, além de desenvolver habilidades práticas de implementação e análise de desempenho. A avaliação é composta por três projetos, nos quais foram implementadas diferentes estruturas como árvores (BST, AVL e Rubro-Negra), algoritmos de busca (sequencial, binária e em árvore) e algoritmos de ordenação.

Além da implementação, o trabalho também envolveu a análise de complexidade dos algoritmos e a realização de experimentos práticos, com coleta de dados, múltiplas execuções e análise estatística dos resultados, como média e desvio padrão. Dessa forma, o projeto simula situações reais do mercado de trabalho, exigindo não apenas a codificação, mas também a capacidade de avaliar desempenho e tomar decisões baseadas em dados.

# Trabalho de Estrutura de Dados II

## Estrutura do repositório

- `TrabalhoArvore/src`: BST e experimento da heurística do caixeiro-viajante
- `TrabalhoArvore/ArvoreAVL/src`: árvore AVL
- `TrabalhoArvore/ArvRubroNegra/src`: árvore rubro-negra
- `PartDoisBuscas/src`: busca sequencial, binária e em árvore de busca
- `PartTres/src`: benchmark de ordenação com Bubble Sort e Insertion Sort

## Como executar

### Projeto 1

BST:
```bash
cd TrabalhoArvore/src
javac *.java
java Main
```

Heurística do caixeiro-viajante:
```bash
cd TrabalhoArvore/src
javac *.java
java ExperimentoCaixeiroViajante
```

AVL:
```bash
cd TrabalhoArvore/ArvoreAVL/src
javac *.java
java Main
```

Rubro-negra:
```bash
cd TrabalhoArvore/ArvRubroNegra/src
javac *.java
java Main
```

### Projeto 2

```bash
cd PartDoisBuscas/src
javac *.java
java Main
```

### Projeto 3

```bash
cd PartTres/src
javac *.java
java Main
```

## Observação

Os experimentos utilizam semente fixa para facilitar reprodutibilidade básica dos resultados.
