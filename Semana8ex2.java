import java.util.Random;
import java.util.Scanner;

public class Semana8ex2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Arvore2 arvore = new Arvore2();
        Node raiz = null;

        while (true) {
            int decisao = 0;
            System.out.println("\tDigite 1 para inserir elementos na árvore");
            System.out.println("\t2 - Execução PosOrdem");
            System.out.println("\t3 - Execução InOrdem");
            System.out.println("\t4 - Execução PreOrdem");
            System.out.println("\t5 - Remover o menor");
            System.out.println("\t6 - Remover o maior");
            System.out.println("\t7 - Remover um número de sua escolha");
            System.out.println("\t8 - Buscar um número na árvore");
            System.out.println("\t0 - Sair");
            decisao = scan.nextInt();

            switch (decisao) {
                case 1:
                    System.out.println("Digite a quantidade de números aleatórios que deseja inserir na árvore:");
                    int quantidadeInserir = scan.nextInt();
                    Random rand = new Random();
                    for (int i = 0; i < quantidadeInserir; i++) {
                        int numAleatorio = rand.nextInt(10000); // Gere números aleatórios entre 0 e 9999
                        raiz = arvore.inserir(raiz, numAleatorio, raiz);
                    }
                    System.out.println(quantidadeInserir + " números aleatórios foram inseridos na árvore.");
                    break;
                case 2:
                    arvore.posOrdem(raiz);
                    break;
                case 3:
                    arvore.inOrdem(raiz);
                    break;
                case 4:
                    arvore.preOrdem(raiz);
                    break;
                case 5:
                    arvore.removerMenor(raiz, raiz);
                    break;
                case 6:
                    arvore.removerMaior(raiz, raiz);
                    break;
                case 7:
                    System.out.println("Digite qual número deseja remover:");
                    int numeroRemover = scan.nextInt();
                    raiz = arvore.removerElemento(raiz, numeroRemover);
                    System.out.println("Número removido!");
                    arvore.inOrdem(raiz);
                    break;
                case 8:
                    System.out.println("Digite o número que deseja buscar na árvore:");
                    int numeroBusca = scan.nextInt();
                    arvore.buscarNumero(raiz, numeroBusca);
                    break;
                case 0:
                    System.out.println("Encerrando o programa.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }
}
