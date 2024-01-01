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
        List<Product> list = new ArrayList<>(); // lista de produtos
        Product products = null;    // objeto produto

        System.out.println(BLUE +"\n* CASO QUEIRA VOLTAR AO MENU PRINCIPAL DIGITE '/' *\n\n" + RESET);
        menu(list, input, products);
    }


    public static void menu(List<Product> list, Scanner input, Product products) {
         while (true) {
            // menu
            clearScreen();  //limpar a tela
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("\n\nO que deseja fazer ?\n");

            System.out.println(" 1 - Inserir produto.\n 2 - Atualizar quantidade em estoque.");
            System.out.println(" 3 - Exibir valor total em estoque de um produto.\n 4 - Listar todos os produtos");
            System.out.println( " 5 - Calcular valor total em estoque.\n 6 - Editar produto.\n 7 - Excluir produto.\n 0 - Finalizar");
            
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
                    listAllProducts(list, input); // função para listar todos os produtos em estoque
                    break;
                case 5:
                    calculateTotalValue(list, input); // calcula valor total de todos os produtos
                    break;
                case 6:
                    editProduct(list, input, products);
                    break;
                case 7:
                    deleteProduct(list, input, products); // função para excluir produto
                    break;
                case 0:
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
        clearScreen();

        System.out.println(BLUE + "\n\nInserir Produto" + RESET);
        int code = 0;
        boolean validInput = false;
        while(Product.hasCode(list, code) != null || code == 0) {

            System.out.print("\n\tCódigo do produto --> ");
            code = validInput(input, list, products);
            
            Product.hasCode(list, code);    // verifica se o código existe, se não retorna null
            
            if (Product.hasCode(list, code) != null) {
                System.out.println(RED + "\n\tCódigo já cadastrado, tente novamente." + RESET);
            }
        }
       
        System.out.print("\n\tDescrição do produto --> ");
        input.nextLine(); // limpar buffer do teclado
        String description = input.nextLine();

        if (description.equals("/")) {  // caso o usuário digite /, volta ao menu
            menu(list, input, products);
        }

        double price = 0;
        validInput = false;
        while(!validInput) {
            System.out.print("\n\tPreço do produto --> ");

            try{    //tenta converter a entrada para double
                price = input.nextDouble();
                validInput = true;
            } catch (InputMismatchException e){ //caso não seja possível, retorna mensagem de erro
                char codeChar = input.next().charAt(0); //pega o primeiro caractere da entrada

                if(codeChar == '/') {   // caso o usuário digite /, volta ao menu
                    menu(list, input, products);
                }

                System.out.println(RED + "\n\tPreço inválido, tente novamente." + RESET);
                input.nextLine();   //limpar buffer do teclado
            }
        }

        validInput = false;
        int quantity = 0;
        while(quantity < 0 || quantity == 0) {
            System.out.print("\n\tQuantidade do produto --> ");
            quantity = validInput(input, list, products);
            
            if (quantity < 0) {
                System.out.println(RED + "\n\tQuantidade inválida, tente novamente." + RESET);
            } 
        }
        // instancia o objeto
        products = new Product(code, description, price, quantity);

        // adiciona o objeto na lista
        list.add(products);

        System.out.println(GREEN + "\nProduto adicionado com sucesso !" + RESET);
        
        pause(input);   //pausa o programa
    }

    public static void updateQuantity(List<Product> list, Scanner input, Product products) {
        clearScreen();
        System.out.println(BLUE + "\nAtualizar quantidade em estoque.\n" + RESET);
        
        int code = 0;
        while(code == 0) {
            System.out.print("\n\tInforme o código do produto --> ");
            code = validInput(input, list, null);
        }
        
        // verificar se o código existe, se não retorna null
        Product idExist = Product.hasCode(list, code);

        char action = ' ';
        if (idExist != null) {
            System.out.println(idExist.toString());
            while (action != 'a' && action != 'A' && action != 'r' && action != 'R') {
                System.out.print("\n\tDeseja adicionar (A) ou Remover (R) quantidade em estoque ? --> ");
                action = input.next().charAt(0);

                if (action == '/') {    // caso o usuário digite /, volta ao menu
                    menu(list, input, products);

                }else if (action != 'a' && action != 'A' && action != 'r' && action != 'R') {
                    System.out.println(RED + "\n\tOpção invalida, tente novamente." + RESET);
                }
            }

            int quantity = 0;
            if (action == 'a' || action == 'A') {
                while(quantity < 0 || quantity == 0) {
                    System.out.print("\n\tInforme a quantidade a ser adicionada ao estoque --> ");
                    
                    quantity = validInput(input, list, products);

                    if (quantity < 0) {
                        System.out.println(RED + "\n\tQuantidade inválida, tente novamente." + RESET);
                    }
                }

                idExist.enterQuantity(quantity); // adiciona a quantidade informada ao estoque

            }else {
                while(quantity < 0 || quantity == 0) {
                    System.out.print("\n\tInforme a quantidade a ser removida do estoque --> ");
                    
                    quantity = validInput(input, list, products);

                    if (quantity < 0) {
                        System.out.println(RED + "\n\tQuantidade inválida, tente novamente." + RESET);
                    } else if (quantity > idExist.getQuantity()) {
                        System.out.println(RED + "\n\tQuantidade maior que a disponível em estoque, tente novamente." + RESET);
                        quantity = 0;
                    }
                }
                idExist.removeQuantity(quantity); // remove a quantidade informada do estoque
            }

            System.out.println(GREEN + "\nQuantidade atualizada com sucesso!\n" + RESET);
            
        } else { // caso idExist seja null
            System.out.println(RED + "\nCódigo não encontrado, tente novamente.\n" + RESET);
        }

        pause(input);
    }

    public static void showTotalValueProduct(List<Product> list, Scanner input, Product products) {
        clearScreen();
        System.out.println(BLUE + "\nValor total em estoque de um produto." + RESET);

        int code = 0;
        while(code == 0) {
            System.out.print("\n\tDigite o código do produto que deseja consultar --> ");
            code = validInput(input, list, products);
        }
        
        // verifica se o código existe, se não retorna null
        Product idExist = Product.hasCode(list, code);

        if (idExist != null) {
            System.out.println("\n\tValor total em estoque do produto " + idExist.getDescription() + " é de R$ "
                    + idExist.valueTotalProduct());
        } else {
            System.out.println(RED + "\n\tCódigo não encontrado, tente novamente." + RESET);
        }

        pause(input);
    }

    public static void listAllProducts(List<Product> list, Scanner input) {
        clearScreen();
        System.out.println(BLUE + "\nLista de produto em estoque.\n" + RESET);

        for (Product product : list) {
            System.out.println(product.toString()); // printa as informações do produto
        }
        
        pause(input);
    }

    public static void calculateTotalValue(List<Product> list, Scanner input) {
        clearScreen();
        
        double totalValue = 0;
        for (Product product : list) {
            totalValue += product.valueTotalProduct(); // soma o valor total de todos os produtos
        }

        System.out.println("\n\tValor total em estoque de todos os produtos --> R$ " + totalValue);
        pause(input);
    }

    public static void editProduct(List<Product> list, Scanner input, Product products) {
        clearScreen();
        System.out.println(BLUE + "\nEditar produto.\n" + RESET);
        int code = 0;
        while(code == 0){
            System.out.print("\n\tInforme o código do produto que deseja editar --> ");
            code = validInput(input, list, products);
        }
        
        Product idExist = Product.hasCode(list, code);
        
        boolean invalidInput = false;
        if (idExist != null) {
            System.out.println(idExist.toString()); // printa as informações do produto
            int op = 0;
            while(!invalidInput || op == 0) {

                invalidInput = true;
                System.out.println("\n\tO que deseja editar ?\n");
                System.out.println("\t 1 - Código.\n\t 2 - Descrição.\n\t 3 - Preço");
                op = validInput(input, list, products); // verifica se a entrada é válida
                
                switch (op) {
                    case 1:
                        System.out.print("\n\tInforme o novo código --> ");
                        int newCode = input.nextInt();
                    
                        idExist.setCode(newCode);   //altera o código
                        break;
                    case 2:
                        System.out.print("\n\tInforme a nova descrição --> ");
                        input.nextLine();
                        String newDescription = input.nextLine();
                        
                        idExist.setDescription(newDescription); //altera a descrição
                        break;
                    case 3:
                        System.out.print("\n\tInforme o novo preço --> ");
                        double newPrice = input.nextDouble();
                    
                        idExist.setPrice(newPrice); //altera o preço
                        break;
                    default:
                        invalidInput = false;   //caso a opção seja inválida
                        break;
                }
            }
            System.out.println(GREEN + "\n\tProduto editado com sucesso !" + RESET);

        } else {
            System.out.println(RED + "\nCódigo não encontrado, tente novamente.\n" + RESET);
        }
        pause(input);
    }

    public static void deleteProduct(List<Product> list, Scanner input, Product products) {
        clearScreen();
        System.out.println(BLUE + "\nExcluir produto.\n" + RESET);

        int code = 0;
        while(code == 0) {
            System.out.print("\n\tInforme o código do produto que deseja excluir --> ");
            code = validInput(input, list, products);
        }
        // verifica se o código existe, se não retorna null
        Product idExist = Product.hasCode(list, code);

        if (idExist != null) {
            System.out.println(idExist.toString()); // printa as informações do produto

            char resp = ' ';
            while (resp != 's' && resp != 'S' && resp != 'n' && resp != 'N') {
                System.out.print("\n\tDeseja realmente excluir o produto ? (s/n) --> ");
                resp = input.next().charAt(0);

                if (resp != 's' && resp != 'S' && resp != 'n' && resp != 'N') {
                    System.out.println(RED + "\n\tOpção invalida, tente novamente.\n" + RESET);
                }
            }

            if (resp =='s' || resp == 'S'){
                list.remove(idExist);   //remove o produto da lista
                System.out.println(GREEN + "\n\tProduto excluido com sucesso !" + RESET);
            } else {
                System.out.println(BLUE + "\n\tProduto não excluido." + RESET);
            }
        } else {
            System.out.println(RED + "\n\tCódigo não encontrado, tente novamente." + RESET);
        }

        pause(input);
    }

    //função para finalizar o programa
    public static void exitProgram() {
        System.out.print("\n\nFinalizando programa");
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(1000);
                System.out.print(".");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.exit(0);
    }

    //função para validar a entrada do usuário
    public static int validInput(Scanner input, List<Product> list, Product products) {
        int entry = 0;
       
        try {   //tenta converter a entrada para int
            entry = input.nextInt();
        } catch (InputMismatchException e) {    //caso não seja possível
            char codeChar = input.next().charAt(0); //pega o primeiro caractere da entrada

            if(codeChar == '/') {   // caso o usuário digite '/', volta ao menu
                menu(list, input, products);
            }

            //caso não converteu a entrada para int e o usuario não digitou '/', retorna mensagem de erro
            System.out.print(RED + "\n\tEntrada inválida, tente novamente.\n" + RESET);
            input.nextLine();
        }
        
        return entry;
    }

    //clear screen
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush(); 
    }

    //system pause  
    public static void pause(Scanner input) {
        System.out.println("\n\nPressione Enter para continuar...");
        input.nextLine();
        input.nextLine();
    }

}