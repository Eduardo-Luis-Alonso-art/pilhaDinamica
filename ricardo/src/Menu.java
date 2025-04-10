import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        PilhaDinamica pilha = new PilhaDinamica();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("============================================");
            System.out.println("// MENU:                                  //");
            System.out.println("// 1. INSERIR ELEMENTO                    //");
            System.out.println("// 2. INSERIR SEQUÊNCIA DE ELEMENTOS      //");
            System.out.println("// 3. REMOVER ELEMENTO                    //");
            System.out.println("// 4. REMOVER SEQUÊNCIA DE ELEMENTOS      //");
            System.out.println("// 5. REMOVER TODAS AS OCORRÊNCIAS        //");
            System.out.println("// 6. VERIFICAR SE A PILHA ESTÁ VAZIA     //");
            System.out.println("// 7. BUSCAR ELEMENTO                     //");
            System.out.println("// 8. ORDENAR A PILHA CRESCENTE           //");
            System.out.println("// 9. ORDENAR A PILHA DECRESCENTE         //");
            System.out.println("// 10. QUANTIDADE DE ELEMENTOS            //");
            System.out.println("// 11. EDITAR ELEMENTO                    //");
            System.out.println("// 12. LIMPAR PILHA                       //");
            System.out.println("// 13. EXIBIR ELEMENTOS                   //");
            System.out.println("// 14. MOSTRAR PRIMEIRO ELEMENTO          //");
            System.out.println("// 15. MOSTRAR ÚLTIMO ELEMENTO            //");
            System.out.println("// 16. SAIR                               //");
            System.out.println("============================================");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("DIGITE O ELEMENTO A INSERIR: ");
                    Object elemento = scanner.next();
                    pilha.inserirElemento(elemento);
                    break;
                case 2:
                    System.out.print("DIGITE A SEQUÊNCIA DE ELEMENTOS (SEPARADOS POR VIRGULAS): ");
                    scanner.nextLine();
                    String entrada = scanner.nextLine();
                    String[] elementos = entrada.split(",");
                    pilha.inserirSequencia(elementos);
                    break;
                case 3:
                    System.out.print("DIGITE O ELEMENTO A REMOVER: ");
                    Object remover = scanner.next();
                    pilha.removerElemento(remover);
                    break;
                case 4:
                    System.out.print("DIGITE A SEQUÊNCIA DE ELEMENTOS A REMOVER (SEPARADOS POR VIRGULAS): ");
                    scanner.nextLine();
                    String entradaRemover = scanner.nextLine();
                    String[] elementosRemover = entradaRemover.split(",");
                    pilha.removerSequencia(elementosRemover);
                    break;
                case 5:
                    System.out.print("DIGITE O ELEMENTO PARA REMOVER TODAS AS OCORRÊNCIAS: ");
                    Object removerOcorrencias = scanner.next();
                    pilha.removerTodasOcorrencias(removerOcorrencias);
                    break;
                case 6:
                    if (pilha.estaVazia()) {
                        System.out.println("A PILHA ESTÁ VAZIA");
                    } else {
                        System.out.println("A PILHA NÃO ESTÁ VAZIA");
                    }
                    break;
                case 7:
                    System.out.print("DIGITE O ELEMENTO PARA A BUSCA: ");
                    Object buscar = scanner.next();
                    if (pilha.buscarElemento(buscar)) {
                        System.out.println("ELEMENTO ENCONTRADO");
                    } else {
                        System.out.println("ELEMENTO NÃO ENCONTRADO");
                    }
                    break;
                case 8:
                    pilha.ordenarCrescente();
                    System.out.println("PILHA ORDENADA EM ORDEM CRESCENTE");
                    break;
                case 9:
                    pilha.ordenarDecrescente();
                    System.out.println("PILHA ORDENADA EM ORDEM DECRESCENTE");
                    break;
                case 10:
                    System.out.println("A QUANTIDADE DE ELEMENTOS É DE: " + pilha.quantidadeElementos()+" ELEMENTOS");
                    break;
                case 11:
                    System.out.print("DIGITE O ELEMENTO ANTIGO: ");
                    Object antigo = scanner.next();
                    System.out.print("DIGITE O NOVO ELEMENTO: ");
                    Object novo = scanner.next();
                    pilha.editarElemento(antigo, novo);
                    break;
                case 12:
                    pilha.limpar();
                    System.out.println("A PILHA FOI LIMPA");
                    break;
                case 13:
                    pilha.exibir();
                    break;
                case 14:
                    No primeiro = pilha.obterPrimeiroElemento();
                    if (primeiro != null) {
                        System.out.println("O PRIMEIRO ELEMENTO É: " + primeiro.getConteudo());
                    } else {
                        System.out.println("A PILHA ESTÁ VAZIA");
                    }
                    break;
                case 15:
                    No ultimo = pilha.obterUltimoElemento();
                    if (ultimo != null) {
                        System.out.println("O ÚLTIMO ELEMENTO É: " + ultimo.getConteudo());
                    } else {
                        System.out.println("A PILHA ESTÁ VAZIA");
                    }
                    break;
                case 16:
                    System.out.println("SAINDO...");
                    System.out.println("TCHAUUU!! :)");
                    break;
                default:
                    System.out.println("ESSA OPÇÃO NÃO EXISTE");
            }
        } while (opcao != 16);

        scanner.close();
    }
}