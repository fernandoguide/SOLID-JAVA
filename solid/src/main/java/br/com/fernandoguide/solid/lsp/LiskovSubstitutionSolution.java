package br.com.fernandoguide.solid.lsp;

import java.util.Arrays;
import java.util.List;

public class LiskovSubstitutionSolution {
    public static class GerenciadorDeContas {
        private double saldo;
        public void deposita(double valor) {
            this.saldo += valor;
        }
        public void saca(double valor) {
            if(valor <= this.saldo) {
                this.saldo -= valor;
            }else{
                throw new IllegalArgumentException("Saldo insuficiente.");
            }
        }
        public double getSaldo() {
            return saldo;
        }
        public void rende(double taxa){
            this.saldo = this.saldo + (this.saldo*taxa);
        }
    }
    public static class ContaCorrenteComum {

        private final GerenciadorDeContas gerenciador;

        public ContaCorrenteComum() {
            this.gerenciador = new GerenciadorDeContas();
        }

        public void deposita(double valor) {
            this.gerenciador.deposita(valor);
        }

        public void saca(double valor) {
            this.gerenciador.saca(valor);
        }

        public double getSaldo() {
            return this.gerenciador.getSaldo();
        }

        public void rende() {
            this.gerenciador.rende(0.02);
        }

        @Override
        public String toString() {
            return "Saldo conta corrente -> " + this.getSaldo();
        }
    }
    public static class ContaSalario {

        private final GerenciadorDeContas gerenciador;

        public ContaSalario() {
            this.gerenciador = new GerenciadorDeContas();
        }

        public void deposita(double valor) {
            this.gerenciador.deposita(valor);
        }

        public void saca(double valor) {
            this.gerenciador.saca(valor);
        }

        public double getSaldo() {
            return this.gerenciador.getSaldo();
        }

        @Override
        public String toString() {
            return "Saldo conta salario -> " + this.getSaldo();
        }
    }

    private static List<ContaCorrenteComum> populaListaContaCorrenteComum() {

        ContaCorrenteComum conta1 = new ContaCorrenteComum();
        conta1.deposita(680.10);

        ContaCorrenteComum conta2 = new ContaCorrenteComum();
        conta2.deposita(855.45);

        ContaCorrenteComum conta3 = new ContaCorrenteComum();
        conta3.deposita(246.87);

        return Arrays.asList(conta1, conta2, conta3);
    }
    private static List<ContaSalario> populaListaContaSalario() {

        ContaSalario contaSalario1 = new ContaSalario();
        contaSalario1.deposita(469.30);

        ContaSalario contaSalario2 = new ContaSalario();
        contaSalario2.deposita(148.90);

        ContaSalario contaSalario3 = new ContaSalario();
        contaSalario3.deposita(200.00);

        return Arrays.asList(contaSalario1, contaSalario2, contaSalario3);
    }

    public static void main(String[] args)  {

        List<ContaCorrenteComum> listaDeContasComum = populaListaContaCorrenteComum();
        List<ContaSalario> listaDeContasSalario = populaListaContaSalario();

        listaDeContasComum.forEach(ContaCorrenteComum::rende);
        listaDeContasComum.forEach(System.out::println);

        listaDeContasSalario.forEach(System.out::println);
    }


}
