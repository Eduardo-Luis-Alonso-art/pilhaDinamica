public class PilhaDinamica implements IEstruturaDinamica {
    private No noTopo;
    private int quantidadeElementos;

    public PilhaDinamica() {
        noTopo = null;
        quantidadeElementos = 0;
    }

    @Override
    public void inserirElemento(Object elemento) {
        Integer valorInformado = converterParaInteiro(elemento);
        while (valorInformado == null) {
            System.out.println("O NÚMERO DEVE SER UM NÚMERO INTEIRO.");
            System.out.print("DIGITE UM NÚMERO INTEIRO: ");
            try {
                String entrada = new java.util.Scanner(System.in).nextLine();
                valorInformado = converterParaInteiro(entrada);
            } catch (Exception ignored) {}
        }

        No noNovo = new No(valorInformado);
        noNovo.setProx(noTopo);
        if (noTopo != null) {
            noTopo.setAnterior(noNovo);
        }
        noTopo = noNovo;
        quantidadeElementos++;
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

        Integer valorProcurado = converterParaInteiro(elemento);
        if (valorProcurado == null) {
            System.out.println("O NÚMERO DEVE SER UM NÚMERO INTEIRO.");
            return false;
        }

        No noAtual = noTopo;

        while (noAtual != null) {
            if (valorProcurado.equals(noAtual.getConteudo())) {
                if (noAtual == noTopo) {
                    noTopo = noAtual.getProx();
                    if (noTopo != null) {
                        noTopo.setAnterior(null);
                    }
                } else {
                    if (noAtual.getAnterior() != null) {
                        noAtual.getAnterior().setProx(noAtual.getProx());
                    }
                    if (noAtual.getProx() != null) {
                        noAtual.getProx().setAnterior(noAtual.getAnterior());
                    }
                }

                quantidadeElementos--;
                System.out.println("O NÚMERO " + valorProcurado + " FOI REMOVIDO.");
                return true;
            }
            noAtual = noAtual.getProx();
        }

        System.out.println("O NÚMERO " + valorProcurado + " NÃO FOI ENCONTRADO NA PILHA.");
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

        PilhaDinamica pilhaAux = new PilhaDinamica();
        int contadorElementosRemovidos = 0;

        boolean[] elementosRemovidos = new boolean[elementos.length];

        while (!estaVazia()) {
            Integer valorAtual = (Integer) noTopo.getConteudo();
            noTopo = noTopo.getProx();
            quantidadeElementos--;

            boolean encontrado = false;

            for (int i = 0; i < elementos.length; i++) {
                Integer valorProcurado = converterParaInteiro(elementos[i]);
                if (valorProcurado != null && valorProcurado.equals(valorAtual) && !elementosRemovidos[i]) {
                    contadorElementosRemovidos++;
                    elementosRemovidos[i] = true;
                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                No novoNo = new No(valorAtual);
                novoNo.setProx(pilhaAux.noTopo);
                pilhaAux.noTopo = novoNo;
                pilhaAux.quantidadeElementos++;
            }
        }

        while (!pilhaAux.estaVazia()) {
            No noAux = pilhaAux.noTopo;
            pilhaAux.noTopo = pilhaAux.noTopo.getProx();
            noAux.setProx(noTopo);
            noTopo = noAux;
            pilhaAux.quantidadeElementos--;
            quantidadeElementos++;
        }

        System.out.println("A SOMA DE NÚMEROS REMOVIDOS É DE: " + contadorElementosRemovidos + " NÚMEROS.");  // Usando nome solicitado
    }

    @Override
    public void removerTodasOcorrencias(Object elemento) {
        if (estaVazia()) {
            System.out.println("A PILHA ESTÁ VAZIA.");
            return;
        }

        Integer valorProcurado = converterParaInteiro(elemento);
        if (valorProcurado == null) {
            System.out.println("O NÚMERO DEVE SER UM NÚMERO INTEIRO VÁLIDO.");
            return;
        }

        PilhaDinamica pilhaAux = new PilhaDinamica();
        int quantidadeRemovida = 0;

        while (!estaVazia()) {
            Integer noAtualValor = (Integer) noTopo.getConteudo();
            noTopo = noTopo.getProx();
            quantidadeElementos--;

            if (!noAtualValor.equals(valorProcurado)) {
                pilhaAux.inserirElemento(noAtualValor);
            } else {
                quantidadeRemovida++;
            }
        }

        while (!pilhaAux.estaVazia()) {
            inserirElemento(pilhaAux.noTopo.getConteudo());
            pilhaAux.noTopo = pilhaAux.noTopo.getProx();
            pilhaAux.quantidadeElementos--;
        }

        System.out.println("FORAM REMOVIDAS " + quantidadeRemovida + " OCORRÊNCIAS DO NÚMERO " + valorProcurado + ".");
    }

    @Override
    public void ordenarCrescente() {
        if (estaVazia()) {
            System.out.println("A PILHA ESTÁ VAZIA.");
            return;
        }else if(noTopo.getProx() == null){
            System.out.println("A PILHA SÓ TEM UM ELEMENTO.");
            return;
        }else{
            System.out.println("PILHA ORDENADA EM ORDEM CRESCENTE.");
        }

        PilhaDinamica pilhaAux = new PilhaDinamica();
        while (!estaVazia()) {
            Integer valorInformado = (Integer) noTopo.getConteudo();
            noTopo = noTopo.getProx();
            quantidadeElementos--;

            No verificandoNo = pilhaAux.noTopo;
            No noAnterior = null;

            while (verificandoNo != null && valorInformado < (Integer) verificandoNo.getConteudo()) {
                noAnterior = verificandoNo;
                verificandoNo = verificandoNo.getProx();
            }

            No noNovo = new No(valorInformado);
            if (noAnterior == null) {
                noNovo.setProx(pilhaAux.noTopo);
                pilhaAux.noTopo = noNovo;
            } else {
                noNovo.setProx(verificandoNo);
                noAnterior.setProx(noNovo);
            }
        }

        while (!pilhaAux.estaVazia()) {
            inserirElemento(pilhaAux.noTopo.getConteudo());
            pilhaAux.noTopo = pilhaAux.noTopo.getProx();
            pilhaAux.quantidadeElementos--;
        }
    }

    @Override
    public void ordenarDecrescente() {
        if (estaVazia()) {
            System.out.println("A PILHA ESTÁ VAZIA.");
            return;
        }else if(noTopo.getProx() == null){
            System.out.println("A PILHA SÓ TEM UM ELEMENTO.");
            return;
        }else{
            System.out.println("PILHA ORDENADA EM ORDEM CRESCENTE.");
        }

        PilhaDinamica pilhaAux = new PilhaDinamica();
        while (!estaVazia()) {
            Integer valorInformado = (Integer) noTopo.getConteudo();
            noTopo = noTopo.getProx();
            quantidadeElementos--;

            No verificandoNo = pilhaAux.noTopo;
            No noAnterior = null;

            while (verificandoNo != null && valorInformado > (Integer) verificandoNo.getConteudo()) {
                noAnterior = verificandoNo;
                verificandoNo = verificandoNo.getProx();
            }

            No noNovo = new No(valorInformado);
            if (noAnterior == null) {
                noNovo.setProx(pilhaAux.noTopo);
                pilhaAux.noTopo = noNovo;
            } else {
                noNovo.setProx(verificandoNo);
                noAnterior.setProx(noNovo);
            }
        }

        while (!pilhaAux.estaVazia()) {
            inserirElemento(pilhaAux.noTopo.getConteudo());
            pilhaAux.noTopo = pilhaAux.noTopo.getProx();
            pilhaAux.quantidadeElementos--;
        }
    }

    @Override
    public boolean estaVazia() {
        return noTopo == null;
    }

    @Override
    public boolean buscarElemento(Object elemento) {
        Integer valorProcurado = converterParaInteiro(elemento);
        if (valorProcurado == null) return false;

        No noAtual = noTopo;
        while (noAtual != null) {
            if (valorProcurado.equals(noAtual.getConteudo())) return true;
            noAtual = noAtual.getProx();
        }
        return false;
    }

    @Override
    public int quantidadeElementos() {
        return quantidadeElementos;
    }

    @Override
    public void editarElemento(Object antigo, Object novoValor) {
        if (antigo == null || novoValor == null) {
            System.out.println("OS NÚMEROS NÃO PODEM SER NULOS.");
            return;
        }

        Integer valorAntigo = converterParaInteiro(antigo);
        Integer valorNo = converterParaInteiro(novoValor);

        if (valorAntigo == null || valorNo == null) {
            System.out.println("NÚMERO INVÁLIDO PARA EDITAR.");
            return;
        }

        No noAtual = noTopo;
        boolean encontrado = false;

        while (noAtual != null) {
            if (valorAntigo.equals(noAtual.getConteudo())) {
                noAtual.setConteudo(valorNo);
                encontrado = true;
            }
            noAtual = noAtual.getProx();
        }

        if (encontrado) {
            System.out.println("O NÚMERO " + valorAntigo + " FOI SUBSTITUIDO POR " + valorNo + ".");
        } else {
            System.out.println("O NÚMERO " + valorAntigo + " NÃO FOI ENCONTRADO NA PILHA.");
        }
    }

    @Override
    public void limpar() {
        if (estaVazia()) {
            System.out.println("A PILHA ESTÁ VAZIA.");
            return;
        }else {
            System.out.println("A PILHA FOI LIMPA.");
            noTopo = null;
            quantidadeElementos = 0;
        }
    }

    @Override
    public void exibir() {
        if (estaVazia()) {
            System.out.println("A PILHA ESTÁ VAZIA.");
            return;
        }

        System.out.println("MOSTRANDO LISTA:");
        No noAtual = noTopo;
        while (noAtual != null) {
            System.out.println(noAtual.getConteudo());
            noAtual = noAtual.getProx();
        }
    }

    @Override
    public No obterPrimeiroElemento() {
        return noTopo;
    }

    @Override
    public No obterUltimoElemento() {
        No noAtual = noTopo;
        while (noAtual != null && noAtual.getProx() != null) {
            noAtual = noAtual.getProx();
        }
        return noAtual;
    }

    private Integer converterParaInteiro(Object obj) {
        try {
            return Integer.parseInt(obj.toString());
        } catch (Exception e) {
            return null;
        }
    }
}