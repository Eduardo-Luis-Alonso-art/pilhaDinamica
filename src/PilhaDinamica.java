import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PilhaDinamica implements IEstruturaDinamica {
    private No topo;
    private int tamanho;

    public PilhaDinamica() {
        topo = null;
        tamanho = 0;
    }

    @Override
    public void inserirElemento(Object elemento) {
        Scanner scanner = new Scanner(System.in);
        Integer valorInteiro = null;

        while (true) {
            try {
                if (elemento instanceof Integer) {
                    valorInteiro = (Integer) elemento;
                }
                else if (elemento instanceof String) {
                    valorInteiro = Integer.parseInt((String) elemento);
                }
                else if (elemento instanceof Number) {
                    valorInteiro = ((Number) elemento).intValue();
                }

                if (valorInteiro != null) {
                    No novoNo = new No(valorInteiro);
                    novoNo.setProx(topo);
                    topo = novoNo;
                    tamanho++;
                    return;
                }
                else {
                    throw new NumberFormatException();
                }
            }
            catch (NumberFormatException e) {
                System.out.println("O ELEMENTO DEVE SER UM NÚMERO INTEIRO");
                System.out.print("DIGITE UM NÚMERO INTEIRO: ");
                elemento = scanner.nextLine();
            }
        }
    }

    @Override
    public void inserirSequencia(Object elementos) {
        if (elementos instanceof Object[]) {
            Object[] seq = (Object[]) elementos;
            for (Object elemento : seq) {
                inserirElemento(elemento);
            }
        }
    }

    @Override
    public boolean removerElemento(Object elemento) {
        if (topo == null) {
            System.out.println("A PILHA ESTÁ VAZIA, NÃO É POSSÍVEL REMOVER ELEMENTOS");
            return false;
        }

        Integer elementoInt = null;
        try {
            if (elemento instanceof Integer) {
                elementoInt = (Integer) elemento;
            }
            else if (elemento instanceof String) {
                elementoInt = Integer.parseInt((String) elemento);
            }
            else if (elemento instanceof Number) {
                elementoInt = ((Number) elemento).intValue();
            }
        } catch (Exception e) {
            System.out.println("O ELEMENTO DEVE SER UM NÚMERO INTEIRO");
            return false;
        }

        if (elementoInt != null && elementoInt.equals(topo.getConteudo())) {
            System.out.println("REMOVENDO O TOPO: " + topo.getConteudo());
            topo = topo.getProx();
            tamanho--;
            return true;
        }

        System.out.println("SÓ É POSSÍVEL REMOVER O TOPO(" + topo.getConteudo() + ")");
        return false;
    }

    @Override
    public void removerSequencia(Object elementos) {
        if (elementos == null) {
            System.out.println("A SEQUÊNCIA FORNECIDA É NULA");
            return;
        }

        Object[] seq = elementos instanceof Object[] ? (Object[]) elementos : new Object[0];
        if (seq.length == 0) {
            System.out.println(elementos instanceof Object[] ? "Sequência vazia fornecida." : "Tipo de sequência inválido.");
            return;
        }

        Integer[] numerosParaRemover = new Integer[seq.length];
        try {
            for (int i = 0; i < seq.length; i++) {
                numerosParaRemover[i] = seq[i] instanceof Integer ? (Integer) seq[i] :
                        seq[i] instanceof String ? Integer.parseInt((String) seq[i]) :
                                seq[i] instanceof Number ? ((Number) seq[i]).intValue() : null;
                if (numerosParaRemover[i] == null) throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            System.out.println("OS ELEMENTOS DA SEQUÊNCIA NÃO SÃO NÚMEROS INTEIROS VÁLIDOS");
            return;
        }

        PilhaDinamica pilhaAuxiliar = new PilhaDinamica();
        int totalRemovidos = 0;

        while (!this.estaVazia()) {
            Integer valorAtual = (Integer) this.topo.getConteudo();
            this.topo = this.topo.getProx();
            this.tamanho--;

            if (Arrays.asList(numerosParaRemover).contains(valorAtual)) {
                totalRemovidos++;
            } else {
                pilhaAuxiliar.inserirElemento(valorAtual);
            }
        }

        while (!pilhaAuxiliar.estaVazia()) {
            this.inserirElemento(pilhaAuxiliar.topo.getConteudo());
            pilhaAuxiliar.topo = pilhaAuxiliar.topo.getProx();
            pilhaAuxiliar.tamanho--;
        }

        System.out.println("A SOMA DOS ELEMENTOS REMOVIDOS É: " + totalRemovidos);
    }

    @Override
    public void removerTodasOcorrencias(Object elemento) {
        if (topo == null) {
            System.out.println("A PILHA ESTÁ VAZIA");
            return;
        }

        Integer numeroProcurado;
        try {
            if (elemento instanceof Integer) {
                numeroProcurado = (Integer) elemento;
            } else if (elemento instanceof String) {
                numeroProcurado = Integer.parseInt((String) elemento);
            } else if (elemento instanceof Number) {
                numeroProcurado = ((Number) elemento).intValue();
            } else {
                System.out.println("O ELEMENTO DEVE SER UM NÚMERO INTEIRO VÁLIDO");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("O ELEMENTO DEVE SER UM NÚMERO INTEIRO VÁLIDO");
            return;
        }

        PilhaDinamica pilhaAuxiliar = new PilhaDinamica();
        int contadorRemocoes = 0;

        while (!this.estaVazia()) {
            Integer valorAtual = (Integer) this.topo.getConteudo();
            this.topo = this.topo.getProx();
            this.tamanho--;

            if (!valorAtual.equals(numeroProcurado)) {
                pilhaAuxiliar.inserirElemento(valorAtual);
            } else {
                contadorRemocoes++;
            }
        }

        while (!pilhaAuxiliar.estaVazia()) {
            Integer valorAtual = (Integer) pilhaAuxiliar.topo.getConteudo();
            pilhaAuxiliar.topo = pilhaAuxiliar.topo.getProx();
            pilhaAuxiliar.tamanho--;

            this.inserirElemento(valorAtual);
        }

        System.out.println("FORAM REMOVIDAS " + contadorRemocoes + " OCORRÊNCIAS DO NÚMERO " + numeroProcurado);
    }

    @Override
    public boolean estaVazia() {
        return topo == null;
    }

    @Override
    public boolean buscarElemento(Object elemento) {
        if (elemento == null) {
            return false;
        }

        Integer numeroProcurado;
        try {
            if (elemento instanceof Integer) {
                numeroProcurado = (Integer) elemento;
            } else if (elemento instanceof String) {
                numeroProcurado = Integer.parseInt((String) elemento);
            } else if (elemento instanceof Number) {
                numeroProcurado = ((Number) elemento).intValue();
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }

        No atual = topo;
        while (atual != null) {
            Integer valorAtual = (Integer) atual.getConteudo();
            if (valorAtual.equals(numeroProcurado)) {
                return true;
            }
            atual = atual.getProx();
        }

        return false;
    }

    @Override
    public void ordenarCrescente() {
        if (topo == null || topo.getProx() == null) return;

        List<Integer> lista = new ArrayList<>();
        No atual = topo;
        while (atual != null) {
            lista.add((Integer) atual.getConteudo());
            atual = atual.getProx();
        }

        for (int i = 1; i < lista.size(); i++) {
            int chave = lista.get(i);
            int j = i - 1;
            while (j >= 0 && lista.get(j) > chave) {
                lista.set(j + 1, lista.get(j));
                j--;
            }
            lista.set(j + 1, chave);
        }

        limpar();
        for (int i = lista.size() - 1; i >= 0; i--) {
            inserirElemento(lista.get(i));
        }
    }

    @Override
    public void ordenarDecrescente() {
        if (topo == null || topo.getProx() == null) return;

        List<Integer> lista = new ArrayList<>();
        No atual = topo;
        while (atual != null) {
            lista.add((Integer) atual.getConteudo());
            atual = atual.getProx();
        }

        for (int i = 1; i < lista.size(); i++) {
            int chave = lista.get(i);
            int j = i - 1;
            while (j >= 0 && lista.get(j) < chave) {
                lista.set(j + 1, lista.get(j));
                j--;
            }
            lista.set(j + 1, chave);
        }

        limpar();
        for (int i = lista.size() - 1; i >= 0; i--) {
            inserirElemento(lista.get(i));
        }
    }

    @Override
    public int quantidadeElementos() {
        return tamanho;
    }

    @Override
    public void editarElemento(Object elementoAntigo, Object elementoNovo) {
        if (elementoAntigo == null || elementoNovo == null) {
            System.out.println("OS ELEMENTOS NÃO PODEM SER NULOS");
            return;
        }

        Integer valorAntigo;
        try {
            if (elementoAntigo instanceof Integer) {
                valorAntigo = (Integer) elementoAntigo;
            } else if (elementoAntigo instanceof String) {
                valorAntigo = Integer.parseInt((String) elementoAntigo);
            } else if (elementoAntigo instanceof Number) {
                valorAntigo = ((Number) elementoAntigo).intValue();
            } else {
                System.out.println("O ELEMENTO ANTIGO NÃO É UM NÚMERO INTEIRO VÁLIDO");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("O ELEMENTO ANTIGO NÃO É UM NÚMERO INTEIRO VÁLIDO");
            return;
        }

        Integer valorNovo;
        try {
            if (elementoNovo instanceof Integer) {
                valorNovo = (Integer) elementoNovo;
            } else if (elementoNovo instanceof String) {
                valorNovo = Integer.parseInt((String) elementoNovo);
            } else if (elementoNovo instanceof Number) {
                valorNovo = ((Number) elementoNovo).intValue();
            } else {
                System.out.println("O ELEMENTO NOVO NÃO É UM NÚMERO INTEIRO VÁLIDO");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("O ELEMENTO NOVO NÃO É UM NÚMERO INTEIRO VÁLIDO");
            return;
        }

        No atual = topo;
        boolean encontrado = false;

        while (atual != null) {
            Integer valorAtual = (Integer) atual.getConteudo();
            if (valorAtual.equals(valorAntigo)) {
                atual.setConteudo(valorNovo);
                encontrado = true;
            }
            atual = atual.getProx();
        }

        if (encontrado) {
            System.out.println("O NÚMERO " + valorAntigo + " FOI SUBSTITUIDO POR " + valorNovo);
        } else {
            System.out.println("O NÚMERO " + valorAntigo + " NÃO FOI ENCONTRADO NA PILHA");
        }
    }

    @Override
    public void limpar() {
        topo = null;
        tamanho = 0;
    }

    @Override
    public void exibir() {
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
}