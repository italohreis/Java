package exercises.exerciseStock.aplicattion;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import exercises.exerciseStock.entities.Product;

public class Main {

    /*
     * Uma loja de eletr�nicos mant�m o controle de seu estoque de produtos.
     * A loja possui diversos produtos, cada um com um c�digo, descri��o, pre�o e
     * quantidade em estoque.
     * A loja deseja criar um programa para gerenciar esse estoque.
     * 
     * Crie um programa em C que permita ao usu�rio realizar as seguintes opera��es:
     * 
     * Inserir um novo produto no estoque. O usu�rio deve fornecer o c�digo,
     * descri��o,
     * pre�o e quantidade em estoque do produto.
     * 
     * Atualizar a quantidade em estoque de um produto existente. O usu�rio deve
     * fornecer o c�digo do produto e
     * a quantidade a ser adicionada (ou subtra�da) do estoque.
     * 
     * Exibir o valor total em estoque de um determinado produto. O usu�rio deve
     * fornecer o c�digo do produto,
     * e o programa deve calcular o valor total em estoque considerando o pre�o e a
     * quantidade em estoque.
     * 
     * Exibir a lista de todos os produtos em estoque, incluindo c�digo, descri��o,
     * pre�o e quantidade.
     * 
     * Calcular e mostrar o valor total em estoque de todos os produtos da loja.
     * 
     * editar produto, o usuario deve fornecer o codigo do produto e informar o que
     * deseja editar (exceto quantidade)
     * 
     * Excluir produto, usuario deve fornecer o codigo do produto.
     * 
     * Sair do programa.
     * O programa deve ser executado em um loop, permitindo ao usu�rio realizar as
     * opera��es
     * acima quantas vezes desejar at� que escolha sair.
     * 
     * Lembre-se de validar as entradas do usu�rio e tratar poss�veis erros
     * (por exemplo, produtos com o mesmo c�digo n�o devem ser inseridos).
     */

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        Scanner input = new Scanner(System.in);
        List<Product> list = new ArrayList<>();
        Product products = null;

        while (true) {
            // menu
            System.out.println("\nO que deseja fazer ?\n");

            System.out.println(" 1 - Inserir produto.\n 2 - Atualizar quantidade em estoque.");
            System.out.println(" 3 - Exibir valor total em estoque de um produto.\n 4 - Listar todos os produtos");
            System.out.println(
                    " 5 - Calcular valor total em estoque.\n 6 - Editar produto.\n 7 - Excluir produto.\n 8 - Finalizar");
            int resp = input.nextInt();

            switch (resp) {

                case 1:
                    insertProduct(list, input, products); // função para inserir produto
                    break;
                case 2:
                    updateQuantity(list, input, products); // função para atualizar quantidade
                    break;
                case 3:
                    showTotalValueProduct(list, input, products); // valor total de um produto em estoque
                    break;
                case 4:

                    listAllProducts(list, input, products); // função para listar todos os produtos em estoque
                    break;
                case 5:

                    calculateTotalValue(list, input, products); // calcula valor total de todos os produtos
                    break;
                case 6:
                    editProduct(list, input, products);
                    break;
                case 7:

                    deleteProduct(list, input, products); // função para excluir produto
                    break;
                case 8:

                    exitProgram(); // função para finalizar o programa
                    input.close();
                    break;
                default:
                    System.out.println(RED + "\nResposta inválida, tente novamente." + RESET);
                    break;
            }
        }
    }

    public static void insertProduct(List<Product> list, Scanner input, Product products) {
        int code = 0;
        System.out.println(BLUE + "\n\nInserir Produto" + RESET);
        boolean validInput = false;
        do {
            while(!validInput) {
                System.out.print("\n\tCódigo do produto --> ");
                
                try {   // tenta converter a entrada do teclado para inteiro
                    code = input.nextInt();
                    validInput = true;
                } catch (InputMismatchException e) { // caso não consiga converter
                    System.out.println(RED + "\n\tCódigo inválido, tente novamente." + RESET);
                    input.nextLine();   // limpar buffer do teclado com a entrada invalida
                }
            }
            validInput = false;  // resetar validInput para false
        } while (Product.hasCode(list, code) != null); //verifica se o codigo existe

        System.out.print("\n\tDescrição do produto --> ");
        input.nextLine(); // limpar buffer do teclado
        String description = input.nextLine();

        double price = 0;
        while(!validInput) {
            System.out.print("\n\tPreço do produto --> ");

            try{
                price = input.nextDouble();
                validInput = true;
            } catch (InputMismatchException e){
                System.out.println(RED + "\n\tPreço inválido, tente novamente." + RESET);
                input.nextLine();
            }
        }

        validInput = false;
        int quantity = 0;
        while (!validInput) {
            System.out.print("\n\tQuantidade do produto --> ");

            try {
                quantity = input.nextInt();
                validInput = true;

                if (quantity < 0) {
                    System.out.println(RED + "\n\tQuantidade inválida, tente novamente." + RESET);
                    validInput = false;
                }
            } catch (InputMismatchException e) {
                System.out.println(RED + "\n\tQuantidade inválida, tente novamente." + RESET);
                input.nextLine();
            }
        }
       
        // instancia o objeto
        products = new Product(code, description, price, quantity);

        // adiciona o objeto na lista
        list.add(products);

        System.out.println(GREEN + "\nProduto adicionado com sucesso !" + RESET);

    }

    public static void updateQuantity(List<Product> list, Scanner input, Product products) {

        System.out.println(BLUE + "\nAtualizar quantidade em estoque.\n" + RESET);

        System.out.print("\n\tInforme o código do produto --> ");
        int code = input.nextInt();

        // verificar se o código existe, se não retorna null
        Product idExist = Product.hasCode(list, code);

        char action = ' ';
        if (idExist != null) {
            while (action != 'a' && action != 'A' && action != 'r' && action != 'R') {
                System.out.print("\n\tDeseja adicionar (A) ou Remover (R) quantidade em estoque ? --> ");
                action = input.next().charAt(0);

                if (action != 'a' && action != 'A' && action != 'r' && action != 'R') {
                    System.out.println(RED + "\n\tOpção invalida, tente novamente." + RESET);
                }
            }

            if (action == 'a' || action == 'A') {
                System.out.print("\n\tInforme a quantidade a ser adicionada no estoque --> ");
                int quantity = input.nextInt();

                idExist.enterQuantity(quantity); // função para adicionar quantidade
            } else {
                System.out.print("\n\tInforme a quantidade a ser removida do estoque --> ");
                int quantity = input.nextInt();

                if(quantity > idExist.getQuantity()) {
                    System.out.println(RED + "\n\tQuantidade indisponível, tente novamente." + RESET);
                    return;
                }

                idExist.removeQuantity(quantity); // função para remover quantidade
            }

            System.out.println(GREEN + "\nQuantidade atualizada com sucesso!\n" + RESET);
        } else { // caso idExist seja null
            System.out.println(RED + "\nCódigo não encontrado, tente novamente.\n" + RESET);
            return;
        }
    }

    public static void showTotalValueProduct(List<Product> list, Scanner input, Product products) {

        System.out.println(BLUE + "\nValor total em estoque de um produto." + RESET);

        System.out.print("\nDigite o código do produto que deseja consultar --> ");
        int code = input.nextInt();

        // verifica se o código existe, se não retorna null
        Product idExist = Product.hasCode(list, code);

        if (idExist != null) {
            System.out.println("\n\tValor total em estoque do produto " + idExist.getDescription() + " é de R$ "
                    + idExist.valueTotalProduct());
        } else {
            System.out.println(RED + "\n\tCódigo não encontrado, tente novamente." + RESET);
        }

    }

    public static void listAllProducts(List<Product> list, Scanner input, Product products) {
        System.out.println(BLUE + "\nLista de produto em estoque.\n" + RESET);

        for (Product product : list) {
            System.out.println("\n\t----------------------------------");

            System.out.println(product.toString()); // printa as informações do produto
        }
        System.out.println("\n\t----------------------------------\n\n");
    }

    public static void calculateTotalValue(List<Product> list, Scanner input, Product products) {
        double totalValue = 0;

        for (Product product : list) {
            totalValue += product.valueTotalProduct(); // soma o valor total de todos os produtos
        }

        System.out.println("\n\tValor total em estoque de todos os produtos --> R$ " + totalValue);
    }

    public static void editProduct(List<Product> list, Scanner input, Product products) {
        System.out.println(BLUE + "\nEditar produto.\n" + RESET);

        System.out.print("\n\tInforme o código do produto que deseja editar --> ");
        int code = input.nextInt();

        Product idExist = Product.hasCode(list, code);

        if (idExist != null) {
            boolean invalidResp = true;

            System.out.println(idExist.toString());
            do {
                invalidResp = true;
                System.out.println("\n\tO que deseja editar ?\n");
                System.out.println("\t 1 - Código.\n\t 2 - Descrição.\n\t 3 - Preço");
                int op = input.nextInt();

                switch (op) {
                    case 1:
                        System.out.print("\n\tInforme o novo código --> ");
                        int newCode = input.nextInt();

                        idExist.setCode(newCode);
                        break;
                    case 2:
                        System.out.print("\n\tInforme a nova descrição --> ");
                        input.nextLine();
                        String newDescription = input.nextLine();

                        idExist.setDescription(newDescription);
                        break;
                    case 3:
                        System.out.print("\n\tInforme o novo preço --> ");
                        double newPrice = input.nextDouble();

                        idExist.setPrice(newPrice);
                        break;
                    default:
                        System.out.println(RED + "\n\tOpção invalida, tente novamente.\n" + RESET);
                        invalidResp = false;
                        break;
                }

            } while (!invalidResp);

            System.out.println(GREEN + "\n\tProduto editado com sucesso !" + RESET);

        } else {
            System.out.println(RED + "\nCódigo não encontrado, tente novamente.\n" + RESET);
        }
    }

    public static void deleteProduct(List<Product> list, Scanner input, Product products) {
        System.out.println(BLUE + "\nExcluir produto.\n" + RESET);

        System.out.print("\n\tInforme o código do produto que deseja excluir --> ");
        int code = input.nextInt();

        Product idExist = Product.hasCode(list, code);

        if (idExist != null) {
            System.out.println(idExist.toString());

            char resp = ' ';
            while (resp != 's' && resp != 'S' && resp != 'n' && resp != 'N') {
                System.out.println("\n\tDeseja realmente excluir o produto ? (s/n) --> ");
                resp = input.next().charAt(0);

                if (resp != 's' && resp != 'S' && resp != 'n' && resp != 'N') {
                    System.out.println(RED + "\n\tOpção invalida, tente novamente.\n" + RESET);
                }
            }

            if (resp =='s' || resp == 'S'){
                list.remove(idExist);
                System.out.println(GREEN + "\n\tProduto excluido com sucesso !" + RESET);
            } else {
                System.out.println(RED + "\n\tProduto não excluido." + RESET);
            }
        } else {
            System.out.println(RED + "\n\tCódigo não encontrado, tente novamente." + RESET);
        }
    }

    public static void exitProgram() {
        System.out.print("\n\nFinalizando programa");
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(1200);
                System.out.print(".");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.exit(0);
    }

}