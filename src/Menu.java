import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        PilhaDinamica pilha = new PilhaDinamica();
        Scanner scanner = new Scanner(System.in);
        int opcaoMenu;

        do {
            System.out.println("============================================");
            System.out.println("// MENU PRINCIPAL:                        //");
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

            System.out.print("DIGITE SUA OPÇÃO: ");
            opcaoMenu = scanner.nextInt();

            switch (opcaoMenu) {
                case 1:
                    System.out.print("DIGITE O NÚMERO A INSERIR: ");
                    Object numeroInserir = scanner.next();
                    pilha.inserirElemento(numeroInserir);
                    break;

                case 2:
                    System.out.print("DIGITE A SEQUÊNCIA DE NÚMEROS (SEPARADOS POR VÍRGULA): ");
                    scanner.nextLine(); // Limpar buffer
                    String entradaSequencia = scanner.nextLine();
                    String[] numerosInserir = entradaSequencia.split(",");
                    pilha.inserirSequencia(numerosInserir);
                    break;

                case 3:
                    System.out.print("DIGITE O NÚMERO A REMOVER: ");
                    Object numeroRemover = scanner.next();
                    pilha.removerElemento(numeroRemover);
                    break;

                case 4:
                    System.out.print("DIGITE A SEQUÊNCIA DE NÚMEROS A REMOVER (SEPARADOS POR VÍRGULA): ");
                    scanner.nextLine(); // Limpar buffer
                    String entradaRemocao = scanner.nextLine();
                    String[] numerosRemover = entradaRemocao.split(",");
                    pilha.removerSequencia(numerosRemover);
                    break;

                case 5:
                    System.out.print("DIGITE O NÚMERO PARA REMOVER TODAS AS OCORRÊNCIAS: ");
                    Object numeroRemoverOcorrencias = scanner.next();
                    pilha.removerTodasOcorrencias(numeroRemoverOcorrencias);
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
                    Object numeroBuscar = scanner.next();
                    if (pilha.buscarElemento(numeroBuscar)) {
                        System.out.println("NÚMERO ENCONTRADO NA PILHA.");
                    } else {
                        System.out.println("NÚMERO NÃO ENCONTRADO NA PILHA.");
                    }
                    break;

                case 8:
                    pilha.ordenarCrescente();
                    break;

                case 9:
                    pilha.ordenarDecrescente();
                    break;

                case 10:
                    System.out.println("QUANTIDADE DE NÚMEROS NA PILHA: " + pilha.quantidadeElementos());
                    break;

                case 11:
                    System.out.print("DIGITE O NÚMERO ANTIGO: ");
                    Object numeroAntigo = scanner.next();
                    System.out.print("DIGITE O NOVO NÚMERO: ");
                    Object numeroNovo = scanner.next();
                    pilha.editarElemento(numeroAntigo, numeroNovo);
                    break;

                case 12:
                    pilha.limpar();
                    break;

                case 13:
                    pilha.exibir();
                    break;

                case 14:
                    No primeiroElemento = pilha.obterPrimeiroElemento();
                    if (primeiroElemento != null) {
                        System.out.println("O PRIMEIRO NÚMERO DA PILHA É: " + primeiroElemento.getConteudo());
                    } else {
                        System.out.println("A PILHA ESTÁ VAZIA.");
                    }
                    break;

                case 15:
                    No ultimoElemento = pilha.obterUltimoElemento();
                    if (ultimoElemento != null) {
                        System.out.println("O ÚLTIMO NÚMERO DA PILHA É: " + ultimoElemento.getConteudo());
                    } else {
                        System.out.println("A PILHA ESTÁ VAZIA.");
                    }
                    break;

                case 16:
                    System.out.println("SAINDO...");
                    System.out.println("TCHAUU!! :)");
                    break;

                default:
                    System.out.println("OPÇÃO INVÁLIDA! DIGITE UM NÚMERO ENTRE 1 E 16.");
            }

        } while (opcaoMenu != 16);
    }
}