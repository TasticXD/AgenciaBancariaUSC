package model;

import utils.Utils;

public class Conta {

    private static int accountCounter = 1;
    private int numeroConta;
    private Pessoa pessoa;
    private Double saldo = 0.0;
    private String tipoConta; // Adicionando o campo tipoConta

    public Conta(Pessoa pessoa, String tipoConta) {
        this.numeroConta = Conta.accountCounter;
        this.pessoa = pessoa;
        this.tipoConta = tipoConta;
        this.updateSaldo();
        Conta.accountCounter += 1;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public Pessoa getClient() {
        return pessoa;
    }

    public void setClient(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public void updateSaldo() {
        this.saldo = this.getSaldo();
    }

    public String getTipoConta() { // Getter para tipoConta
        return tipoConta;
    }

    public String toString() {
        return "\nBank account: " + this.getNumeroConta() +
                "\nCliente: " + this.pessoa.getName() +
                "\nCPF: " + this.pessoa.getCpf() +
                "\nEmail: " + this.pessoa.getEmail() +
                "\nTipo de conta: " + this.getTipoConta() + 
                "\nSaldo: " + Utils.doubleToString(this.getSaldo()) +
                "\n ";
    }

    public void depositar(Double valor) {
        if (valor > 0) {
            setSaldo(getSaldo() + valor);
            System.out.println("Seu depósito foi realizado com sucesso!");
        } else {
            System.out.println("Não foi possivel realizar o saque!");
        }
    }

    public void sacar(Double valor) {
        if (valor > 0 && this.getSaldo() >= valor) {
            setSaldo(getSaldo() - valor);
            System.out.println("Saque realizado com sucesso!");
        } else {
            System.out.println("Não foi possivel realizar o saque!");
        }
    }

    public void transferencia(Conta contaParaDeposito, Double valor) {
        if (valor > 0 && this.getSaldo() >= valor) {
            setSaldo(getSaldo() - valor);
            contaParaDeposito.saldo = contaParaDeposito.getSaldo() + valor;
            System.out.println("Transferência realizada com sucesso!");
        } else {
            System.out.println("Não foi possivel realizar a transferência!");
        }
    }
}