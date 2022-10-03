package br.com.fernandoguide.solid.lsp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LiskovSubstitutionSolution {
    public abstract static class GerenciadorDeContas {
         double saldo;
        public abstract void deposita(double valor);
        public abstract void saca(double valor);
        public abstract double getSaldo();

    }
    public static class ContaCorrenteComum extends GerenciadorDeContas {

        public void rende(double taxa){
            this.saldo = this.saldo + (this.saldo*taxa);
        }

        @Override
        public void deposita(double valor) {
            this.saldo += valor;
        }

        @Override
        public void saca(double valor) {
            if(valor <= this.saldo) {
                this.saldo -= valor;
            } else {
                throw new IllegalArgumentException("Saldo insuficiente.");
            }
        }
        @Override
        public double getSaldo() {
            return saldo;
        }

        @Override
        public String toString() {
            return "Saldo conta corrente -> " + this.getSaldo();
        }
    }
    public static class ContaSalario  extends GerenciadorDeContas {

        @Override
        public String toString() {
            return "Saldo conta salario -> " + this.getSaldo();
        }

        @Override
        public void deposita(double valor) {
            this.saldo += valor;
        }

        @Override
        public void saca(double valor) {
            if(valor <= this.saldo) {
                this.saldo -= valor;
            }else{
                throw new IllegalArgumentException("Saldo insuficiente.");
            }
        }
        @Override
        public double getSaldo() {
            return saldo;
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

        List<GerenciadorDeContas> gerenciadorDeContas = new ArrayList<>();

        for (ContaCorrenteComum contaCorrenteComum : listaDeContasComum) {
            contaCorrenteComum.rende(0.02);
            gerenciadorDeContas.add(contaCorrenteComum);
        }
        gerenciadorDeContas.addAll(listaDeContasSalario);
        gerenciadorDeContas.forEach(System.out::println);
    }

}
