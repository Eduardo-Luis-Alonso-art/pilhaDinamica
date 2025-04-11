public class PilhaDinamica implements IEstruturaDinamica {
    private No topo;
    private int tamanho;

    public PilhaDinamica() {
        topo = null;
        tamanho = 0;
    }

    @Override
    public void inserirElemento(Object elemento) {
        Integer valor = converterParaInteiro(elemento);
        while (valor == null) {
            System.out.println("O NÚMERO DEVE SER UM NÚMERO INTEIRO.");
            System.out.print("DIGITE UM NÚMERO INTEIRO: ");
            try {
                String entrada = new java.util.Scanner(System.in).nextLine();
                valor = converterParaInteiro(entrada);
            } catch (Exception ignored) {}
        }

        No novoNo = new No(valor);
        novoNo.setProx(topo);
        topo = novoNo;
        tamanho++;
    }

    @Override
    public void inserirSequencia(Object[] elementos) {
        if (elementos == null) return;
        for (Object elemento : elementos) {
            inserirElemento(elemento);
        }
    }

    @Override
    public boolean removerElemento(Object elemento) {
        if (estaVazia()) {
            System.out.println("A PILHA ESTÁ VAZIA.");
            return false;
        }

        Integer alvo = converterParaInteiro(elemento);
        if (alvo == null) {
            System.out.println("O NÚMERO DEVE SER UM NÚMERO INTEIRO.");
            return false;
        }

        No anterior = null;
        No atual = topo;

        while (atual != null) {
            if (alvo.equals(atual.getConteudo())) {
                if (anterior == null) {
                    topo = topo.getProx();
                } else {
                    anterior.setProx(atual.getProx());
                }
                tamanho--;
                System.out.println("O NÚMERO " + alvo + " FOI REMOVIDO.");
                return true;
            }

            anterior = atual;
            atual = atual.getProx();
        }

        System.out.println("O NÚMERO " + alvo + " NÃO FOI ENCONTRADO NA PILHA.");
        return false;
    }

    @Override
    public void removerSequencia(Object[] elementos) {
        if (estaVazia()) {
            System.out.println("A PILHA ESTÁ VAZIA.");
            return;
        }

        if (elementos == null || elementos.length == 0) {
            System.out.println("SEQUÊNCIA NULA OU VAZIA FORNECIDA.");
            return;
        }

        PilhaDinamica aux = new PilhaDinamica();
        int removidos = 0;

        // Flag para controlar os números que foram removidos (apenas uma vez)
        boolean[] removidosFlags = new boolean[elementos.length];

        while (!estaVazia()) {
            Integer atual = (Integer) topo.getConteudo();
            topo = topo.getProx();
            tamanho--;

            boolean encontrado = false;

            // Verifica se o elemento atual está na sequência e ainda não foi removido
            for (int i = 0; i < elementos.length; i++) {
                Integer alvo = converterParaInteiro(elementos[i]);
                if (alvo != null && alvo.equals(atual) && !removidosFlags[i]) {
                    removidos++;
                    removidosFlags[i] = true; // Marca o número como removido
                    encontrado = true;
                    break; // Remove apenas a primeira ocorrência do número
                }
            }

            // Se não encontrou o número, coloca ele na pilha auxiliar
            if (!encontrado) {
                // Inserção manual sem mensagem
                No novoNo = new No(atual);
                novoNo.setProx(aux.topo);
                aux.topo = novoNo;
                aux.tamanho++;
            }
        }

        // Recoloca os elementos que não foram removidos de volta na pilha original
        while (!aux.estaVazia()) {
            No noAux = aux.topo;
            aux.topo = aux.topo.getProx();
            noAux.setProx(topo);
            topo = noAux;
            aux.tamanho--;
            tamanho++;
        }

        System.out.println("A SOMA DE NÚMEROS REMOVIDOS É DE: " + removidos + " NÚMEROS.");
    }

    @Override
    public void removerTodasOcorrencias(Object elemento) {
        if (estaVazia()) {
            System.out.println("A PILHA ESTÁ VAZIA.");
            return;
        }

        Integer alvo = converterParaInteiro(elemento);
        if (alvo == null) {
            System.out.println("O NÚMERO DEVE SER UM NÚMERO INTEIRO VÁLIDO.");
            return;
        }

        PilhaDinamica aux = new PilhaDinamica();
        int contador = 0;

        while (!estaVazia()) {
            Integer atual = (Integer) topo.getConteudo();
            topo = topo.getProx();
            tamanho--;

            if (!atual.equals(alvo)) {
                aux.inserirElemento(atual);
            } else {
                contador++;
            }
        }

        while (!aux.estaVazia()) {
            inserirElemento(aux.topo.getConteudo());
            aux.topo = aux.topo.getProx();
            aux.tamanho--;
        }

        System.out.println("FORAM REMOVIDAS " + contador + " OCORRÊNCIAS DO NÚMERO " + alvo + ".");
    }

    @Override
    public boolean estaVazia() {
        return topo == null;
    }

    @Override
    public boolean buscarElemento(Object elemento) {
        Integer alvo = converterParaInteiro(elemento);
        if (alvo == null) return false;

        No atual = topo;
        while (atual != null) {
            if (alvo.equals(atual.getConteudo())) return true;
            atual = atual.getProx();
        }
        return false;
    }

    @Override
    public void ordenarCrescente() {
        if (estaVazia()) {
            System.out.println("A PILHA ESTÁ VAZIA.");
            return;
        }else{
            System.out.println("PILHA ORDENADA EM ORDEM CRESCENTE.");
        }

        // Não há uso de Collections, apenas manipulação manual
        if (topo == null || topo.getProx() == null) return;

        PilhaDinamica aux = new PilhaDinamica();
        while (!estaVazia()) {
            Integer valor = (Integer) topo.getConteudo();
            topo = topo.getProx();
            tamanho--;

            // Inserção ordenada na pilha auxiliar
            No temp = aux.topo;
            No anterior = null;

            while (temp != null && valor > (Integer) temp.getConteudo()) {
                anterior = temp;
                temp = temp.getProx();
            }

            No novoNo = new No(valor);
            if (anterior == null) {
                novoNo.setProx(aux.topo);
                aux.topo = novoNo;
            } else {
                novoNo.setProx(temp);
                anterior.setProx(novoNo);
            }
        }

        // Recolocando os elementos da pilha auxiliar de volta na pilha original
        while (!aux.estaVazia()) {
            inserirElemento(aux.topo.getConteudo());
            aux.topo = aux.topo.getProx();
            aux.tamanho--;
        }
    }

    @Override
    public void ordenarDecrescente() {
        if (estaVazia()) {
            System.out.println("A PILHA ESTÁ VAZIA.");
            return;
        }else{
            System.out.println("PILHA ORDENADA EM ORDEM DECRESCENTE.");
        }

        // Implementação similar ao crescente, mas invertendo a comparação
        if (topo == null || topo.getProx() == null) return;

        PilhaDinamica aux = new PilhaDinamica();
        while (!estaVazia()) {
            Integer valor = (Integer) topo.getConteudo();
            topo = topo.getProx();
            tamanho--;

            // Inserção ordenada decrescente na pilha auxiliar
            No temp = aux.topo;
            No anterior = null;

            while (temp != null && valor < (Integer) temp.getConteudo()) {
                anterior = temp;
                temp = temp.getProx();
            }

            No novoNo = new No(valor);
            if (anterior == null) {
                novoNo.setProx(aux.topo);
                aux.topo = novoNo;
            } else {
                novoNo.setProx(temp);
                anterior.setProx(novoNo);
            }
        }

        // Recolocando os elementos da pilha auxiliar de volta na pilha original
        while (!aux.estaVazia()) {
            inserirElemento(aux.topo.getConteudo());
            aux.topo = aux.topo.getProx();
            aux.tamanho--;
        }
    }

    @Override
    public int quantidadeElementos() {
        return tamanho;
    }

    @Override
    public void editarElemento(Object antigo, Object novoValor) {
        if (antigo == null || novoValor == null) {
            System.out.println("OS NÚMEROS NÃO PODEM SER NULOS.");
            return;
        }

        Integer velho = converterParaInteiro(antigo);
        Integer novoInt = converterParaInteiro(novoValor);

        if (velho == null || novoInt == null) {
            System.out.println("NÚMERO INVÁLIDO PARA EDITAR.");
            return;
        }

        No atual = topo;
        boolean encontrado = false;

        while (atual != null) {
            if (velho.equals(atual.getConteudo())) {
                atual.setConteudo(novoInt);
                encontrado = true;
            }
            atual = atual.getProx();
        }

        if (encontrado) {
            System.out.println("O NÚMERO " + velho + " FOI SUBSTITUIDO POR " + novoInt + ".");
        } else {
            System.out.println("O NÚMERO " + velho + " NÃO FOI ENCONTRADO NA PILHA.");
        }
    }

    @Override
    public void limpar() {
        if (estaVazia()) {
            System.out.println("A PILHA ESTÁ VAZIA.");
            return;
        }else {
            System.out.println("A PILHA FOI LIMPA.");
            topo = null;
            tamanho = 0;
        }
    }

    @Override
    public void exibir() {
        if (estaVazia()) {
            System.out.println("A PILHA ESTÁ VAZIA.");
            return;
        }

        System.out.println("MOSTRANDO LISTA:");
        No atual = topo;
        while (atual != null) {
            System.out.println(atual.getConteudo());
            atual = atual.getProx();
        }
    }

    @Override
    public No obterPrimeiroElemento() {
        return topo;
    }

    @Override
    public No obterUltimoElemento() {
        No atual = topo;
        while (atual != null && atual.getProx() != null) {
            atual = atual.getProx();
        }
        return atual;
    }

    private Integer converterParaInteiro(Object obj) {
        try {
            return Integer.parseInt(obj.toString());
        } catch (Exception e) {
            return null;
        }
    }
}