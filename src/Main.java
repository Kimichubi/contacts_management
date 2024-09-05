import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    private static String userName;
    private static ArrayList<Contact> contacts = new ArrayList<>();
    private static Scanner input = new Scanner(System.in);
    private static int nextId = 0;

    public static void main(String[] args) {
        System.out.println("Por favor insira seu nome: ");
        userName = input.nextLine();

        int option;
        do {
            option = showMenu();
            handleMenuOption(option);
        } while (option != 5);

        System.out.println("Saindo, obrigado por usar o Software de Kimas...");
    }

    private static int showMenu() {
        System.out.println("Olá " + userName + ", o que devemos fazer hoje?");
        System.out.println("1: Adicionar contato");
        System.out.println("2: Visualizar contatos");
        System.out.println("3: Editar contato");
        System.out.println("4: Remover contato");
        System.out.println("5: Sair");
        System.out.print("Escolha uma opção: ");
        return input.nextInt();
    }

    private static void handleMenuOption(int option) {
        switch (option) {
            case 1 -> addContact();
            case 2 -> viewContacts();
            case 3 -> editContact();
            case 4 -> removeContact();
            case 5 -> System.out.println("Finalizando...");
            default -> System.out.println("Opção incorreta, tente novamente.");
        }
    }

    private static void addContact() {
        input.nextLine();  // Limpar o buffer
        System.out.print("Por favor, digite o nome do contato: ");
        String contactName = input.nextLine();

        System.out.print("Por favor, digite o número do contato: ");
        int contactNumber = getValidNumber();

        System.out.println("Informações do contato:");
        System.out.println("Nome: " + contactName);
        System.out.println("Telefone: " + contactNumber);
        System.out.println("Está correto? (1 para sim, 0 para não): ");
        int confirm = input.nextInt();

        if (confirm == 1) {
            contacts.add(new Contact(contactName, contactNumber, nextId++));
            System.out.println("Contato adicionado com sucesso!");
        } else {
            System.out.println("Operação cancelada.");
        }
    }

    private static void viewContacts() {
        if (contacts.isEmpty()) {
            System.out.println("Nenhum contato salvo.");
            return;
        }
        for (Contact contact : contacts) {
            System.out.println("Id: " + contact.id);
            System.out.println("Nome: " + contact.name);
            System.out.println("Número: " + contact.number);
            System.out.println("-------------------------");
        }
    }

    private static void editContact() {
        System.out.print("Digite o ID do contato que deseja editar: ");
        int editContactId = input.nextInt();

        if (editContactId < 0 || editContactId >= contacts.size()) {
            System.out.println("ID inválido!");
            return;
        }

        Contact contact = contacts.get(editContactId);

        System.out.println("O que você deseja mudar no contato:");
        System.out.println("1: Nome");
        System.out.println("2: Telefone");
        System.out.println("3: Ambos");
        System.out.print("Escolha uma opção: ");
        int option = input.nextInt();

        input.nextLine();  // Limpar buffer
        switch (option) {
            case 1 -> {
                System.out.print("Digite o novo nome: ");
                contact.name = input.nextLine();
            }
            case 2 -> {
                System.out.print("Digite o novo número: ");
                contact.number = getValidNumber();
            }
            case 3 -> {
                System.out.print("Digite o novo nome: ");
                contact.name = input.nextLine();
                System.out.print("Digite o novo número: ");
                contact.number = getValidNumber();
            }
            default -> System.out.println("Opção inválida.");
        }
        System.out.println("Contato atualizado com sucesso!");
    }

    private static void removeContact() {
        System.out.print("Digite o ID do contato que deseja remover: ");
        int deleteId = input.nextInt();

        boolean contactFound = false;
        Iterator<Contact> iterator = contacts.iterator();

        while (iterator.hasNext()) {
            Contact contact = iterator.next();

            if (contact.id == deleteId) {
                contactFound = true;
                System.out.println("Deseja excluir o contato: " + contact.name + " (Telefone: " + contact.number + ")?");
                System.out.println("1 para sim, 0 para não: ");
                int confirm = input.nextInt();

                if (confirm == 1) {
                    iterator.remove();  // Remove o contato de forma segura
                    System.out.println("Contato removido com sucesso.");
                } else {
                    System.out.println("Operação cancelada.");
                }
                break;  // Interrompe o loop após encontrar o contato
            }
        }

        if (!contactFound) {
            System.out.println("Contato com ID " + deleteId + " não encontrado.");
        }
    }


    private static int getValidNumber() {
        while (!input.hasNextInt()) {
            System.out.print("Número inválido, tente novamente: ");
            input.next();
        }
        return input.nextInt();
    }

    public static class Contact {
        public String name;
        public int number;
        public int id;

        Contact(String name, int number, int id) {
            this.name = name;
            this.number = number;
            this.id = id;
        }
    }
}
