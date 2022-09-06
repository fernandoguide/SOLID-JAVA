package br.com.fernandoguide.solid.lsp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LiskovSubstitutionProblem {

    public static class ContaCorrenteComum {
        protected double saldo;
        public ContaCorrenteComum() {
            this.saldo = 0;
        }
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
        public void rende() throws Exception {
            this.saldo*= 0.02;
        }
    }
    public static class ContaSalario extends ContaCorrenteComum {

        @Override
        public void rende() throws Exception {
            throw new Exception("Essa conta não possui rendimento");
        }
    }


    public static void main(String[] args) throws Exception {

        List<ContaCorrenteComum> listaDeContas = new ArrayList<>();
        listaDeContas.add(new ContaCorrenteComum());
        listaDeContas.add(new ContaSalario());

        for (ContaCorrenteComum conta : listaDeContas) {
            conta.rende();
            System.out.println("Novo Saldo:");
            System.out.println(conta.getSaldo());
        }
    }
}
