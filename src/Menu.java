import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        PilhaDinamica pilha = new PilhaDinamica();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("============================================");
            System.out.println("// MENU:                                  //");
            System.out.println("// 1. INSERIR NÚMERO                      //");
            System.out.println("// 2. INSERIR SEQUÊNCIA DE NÚMEROS        //");
            System.out.println("// 3. REMOVER NÚMERO                      //");
            System.out.println("// 4. REMOVER SEQUÊNCIA DE NÚMEROS        //");
            System.out.println("// 5. REMOVER TODAS AS OCORRÊNCIAS        //");
            System.out.println("// 6. VERIFICAR SE A PILHA ESTÁ VAZIA     //");
            System.out.println("// 7. BUSCAR NÚMERO                       //");
            System.out.println("// 8. ORDENAR A PILHA CRESCENTE           //");
            System.out.println("// 9. ORDENAR A PILHA DECRESCENTE         //");
            System.out.println("// 10. QUANTIDADE DE NÚMEROS              //");
            System.out.println("// 11. EDITAR NÚMERO                      //");
            System.out.println("// 12. LIMPAR PILHA                       //");
            System.out.println("// 13. EXIBIR NÚMEROS                     //");
            System.out.println("// 14. MOSTRAR PRIMEIRO NÚMERO            //");
            System.out.println("// 15. MOSTRAR ÚLTIMO NÚMERO              //");
            System.out.println("// 16. SAIR                               //");
            System.out.println("============================================");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("DIGITE O NÚMERO A INSERIR: ");
                    Object elemento = scanner.next();
                    pilha.inserirElemento(elemento);
                    break;
                case 2:
                    System.out.print("DIGITE A SEQUÊNCIA DE NÚMEROS (SEPARADOS POR VIRGULAS): ");
                    scanner.nextLine();
                    String entrada = scanner.nextLine();
                    String[] elementos = entrada.split(",");
                    pilha.inserirSequencia(elementos);
                    break;
                case 3:
                    System.out.print("DIGITE O NÚMERO A REMOVER: ");
                    Object remover = scanner.next();
                    pilha.removerElemento(remover);
                    break;
                case 4:
                    System.out.print("DIGITE A SEQUÊNCIA DE NÚMEROS A REMOVER (SEPARADOS POR VIRGULAS): ");
                    scanner.nextLine();
                    String entradaRemover = scanner.nextLine();
                    String[] elementosRemover = entradaRemover.split(",");
                    pilha.removerSequencia(elementosRemover);
                    break;
                case 5:
                    System.out.print("DIGITE O NÚMERO PARA REMOVER TODAS AS OCORRÊNCIAS: ");
                    Object removerOcorrencias = scanner.next();
                    pilha.removerTodasOcorrencias(removerOcorrencias);
                    break;
                case 6:
                    if (pilha.estaVazia()) {
                        System.out.println("A PILHA ESTÁ VAZIA.");
                    } else {
                        System.out.println("A PILHA NÃO ESTÁ VAZIA.");
                    }
                    break;
                case 7:
                    System.out.print("DIGITE O NÚMERO PARA A BUSCA: ");
                    Object buscar = scanner.next();
                    if (pilha.buscarElemento(buscar)) {
                        System.out.println("NÚMERO ENCONTRADO.");
                    } else {
                        System.out.println("NÚMERO NÃO ENCONTRADO.");
                    }
                    break;
                case 8:
                    pilha.ordenarCrescente();
                    break;
                case 9:
                    pilha.ordenarDecrescente();
                    break;
                case 10:
                    System.out.println("A QUANTIDADE DE NÚMEROS É DE: " + pilha.quantidadeElementos()+" NÚMEROS");
                    break;
                case 11:
                    System.out.print("DIGITE O NÚMERO ANTIGO: ");
                    Object antigo = scanner.next();
                    System.out.print("DIGITE O NOVO NÚMERO: ");
                    Object novo = scanner.next();
                    pilha.editarElemento(antigo, novo);
                    break;
                case 12:
                    pilha.limpar();
                    break;
                case 13:
                    pilha.exibir();
                    break;
                case 14:
                    No primeiro = pilha.obterPrimeiroElemento();
                    if (primeiro != null) {
                        System.out.println("O PRIMEIRO NÚMERO É O: " + primeiro.getConteudo() + ".");
                    } else {
                        System.out.println("A PILHA ESTÁ VAZIA.");
                    }
                    break;
                case 15:
                    No ultimo = pilha.obterUltimoElemento();
                    if (ultimo != null) {
                        System.out.println("O ÚLTIMO NÚMERO É O: " + ultimo.getConteudo() + ".");
                    } else {
                        System.out.println("A PILHA ESTÁ VAZIA.");
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