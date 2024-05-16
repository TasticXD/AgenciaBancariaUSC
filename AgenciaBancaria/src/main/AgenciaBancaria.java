package main;

import java.util.ArrayList;
import java.util.Scanner;
import model.Pessoa;
import model.Conta;


public class AgenciaBancaria {

    static Scanner input = new Scanner(System.in);
    static ArrayList<Conta> contasBancarias;

    public static void main(String[] args) {
        contasBancarias = new ArrayList<Conta>();
        operacoes();
    }

    public static void operacoes() {

        System.out.println("------------------------------------------------------");
        System.out.println("-------------Bem vindos a nossa Agência---------------");
        System.out.println("------------------------------------------------------");
        System.out.println("***** Selecione uma operação que deseja realizar *****");
        System.out.println("------------------------------------------------------");
        System.out.println("|   Opção 1 - Criar conta   |");
        System.out.println("|   Opção 2 - Depositar     |");
        System.out.println("|   Opção 3 - Sacar         |");
        System.out.println("|   Opção 4 - Transferir    |");
        System.out.println("|   Opção 5 - Listar        |");
        System.out.println("|   Opção 6 - Sair          |");

        int operacao = input.nextInt();;

        switch (operacao) {
            case 1:
                criarConta();
                break;

            case 2:
                depositar();
                break;

            case 3:
                sacar();
                break;

            case 4:
                transferir();
                break;

            case 5:
                listarContas();
                break;

            case 6:
                System.out.println("Flw é nóis!");
                System.exit(0); // para o sistema

            default:
                System.out.println("Opção inválida!");
                operacoes();
                break;
        }
    }

    public static void criarConta() {
        System.out.println("\nNome: ");
        String nome = input.next();

        System.out.println("\nCPF: ");
        String cpf = input.next();

        System.out.println("\nEmail: ");
        String email = input.next();

        System.out.println("Tipo de conta (1 para Corrente, 2 para Poupança): ");
        int tipoConta = input.nextInt();

        Pessoa cliente = new Pessoa(nome, cpf, email);

        Conta conta;
        if (tipoConta == 1) {
            conta = new Conta(cliente, "Corrente");
        } else if (tipoConta == 2) {
            conta = new Conta(cliente, "Poupança");
        } else {
            System.out.println("Tipo de conta inválido!");
            operacoes();
            return;
        }

        contasBancarias.add(conta);
        System.out.println("--- Sua conta foi criada com sucesso! ---");

        operacoes();

    }
    
     
    

    private static Conta encontrarConta(int numeroConta) {
        Conta conta = null;
        if(contasBancarias.size() > 0) {
            for(Conta contaa : contasBancarias) {
                if(contaa.getNumeroConta() == numeroConta) {
                    conta = contaa;
                }
            }
        }
        return conta;
    }

    public static void depositar() {
        System.out.println("Número da conta: ");
        int numeroConta = input.nextInt();
        Conta conta = encontrarConta(numeroConta);

        if(conta != null) {
            System.out.println("Qual valor deseja depositar? ");
            Double valorDeposito = input.nextDouble();

            conta.depositar(valorDeposito);
        }else {
            System.out.println("--- Conta não encontrada ---");
        }

        operacoes();

    }

    public static void sacar() {
        System.out.println("Número da conta: ");
        int numeroConta = input.nextInt();

        Conta conta = encontrarConta(numeroConta);

        if(conta != null) {
            System.out.println("Qual valor deseja sacar? ");
            Double valorSaque = input.nextDouble();

            conta.sacar(valorSaque);
            System.out.println("--- Saque realizado com sucesso! ---");
        }else {
            System.out.println("--- Conta não encontrada ---");
        }

        operacoes();

    }

    public static void transferir() {
    
    	System.out.println("Número da conta que vai enviar a transferência: ");
    	int numeroContaRemetente = input.nextInt();

        Conta contaRemetente = encontrarConta(numeroContaRemetente);
    	
    	
      if(contaRemetente != null) {
            System.out.println("Escolha o tipo de transferência:");
            System.out.println("1 - Transferência com PIX");
            System.out.println("2 - Transferência com TED");
            System.out.println("3 - Transferência padrão");
            int tipoTransferencia = input.nextInt();

          
            
            
            switch (tipoTransferencia) {
                case 1:
                    System.out.println("Transferência com PIX. Código: A194NNABN19O8JE8Y61A");
                    break;
                case 2:
                    transferenciaTED(contaRemetente);
                    break;
                case 3:
                    transferenciaPadrao(contaRemetente);
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }

        }else {
            System.out.println("--- Conta para transferência não encontrada ---");
        }
        operacoes();
    }

    public static void transferenciaTED(Conta contaRemetente) {
        System.out.println("Transferência com TED");
        System.out.println("Nome completo do beneficiário:");
        String nomeBeneficiario = input.next();
        System.out.println("CPF do beneficiário:");
        String cpfBeneficiario = input.next();
        System.out.println("Agência bancária do beneficiário:");
        String agenciaBeneficiario = input.next();
        System.out.println("Digite o valor da transferencia: ");
        String transTed = input.next();
        System.out.println("TED realizado com sucesso!");
        
    }

    public static void transferenciaPadrao(Conta contaRemetente) {
        System.out.println("Valor da trasferencia: ");
        Double valorTrans = input.nextDouble();
        System.out.println("Coloque sua senha: ");
        Double senha = input.nextDouble();
        System.out.println("Transferencia realizada com sucesso!");
        
        
    }

    public static void listarContas() {
        if(contasBancarias.size() > 0) {
            for(Conta conta: contasBancarias) {
                System.out.println(conta);
            }
        }else {
            System.out.println("--- Não há contas cadastradas ---");
        }

        operacoes();
    }
}