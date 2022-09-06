package br.com.fernandoguide.solid.ocp;

public class OpenCloseProblem {

    public static class CalculadoraDePrecos {

        public double calcula(Produto produto) {

            Frete frete = new Frete();
            double desconto = 0d;
            int regra = produto.getMeioPagamento();

            switch(regra) {
                case 1:
                    System.out.println("Venda à vista");
                    TabelaDePrecoAVista tabela1 = new TabelaDePrecoAVista();
                    desconto = tabela1.calculaDesconto(produto.getValor());
                    break;
                case 2:
                    System.out.println("Venda à prazo");
                    TabelaDePrecoAPrazo tabela2 = new TabelaDePrecoAPrazo();
                    desconto = tabela2.calculaDesconto(produto.getValor());
                    break;
            }

            double valorFrete = frete.calculaFrete(produto.getEstado());
            return produto.getValor() * (1 - desconto) + valorFrete;
        }
    }

    public static class TabelaDePrecoAVista {

        public double calculaDesconto(double valor) {
            if(valor < 100.0) {
                return 0.05;
            }else if(valor > 100.0 && valor < 500.0) {
                return 0.07;
            }else if(valor > 500.0 && valor < 1000.0) {
                return 0.10;
            }else {
                return 0d;
            }
        }

    }
    public static class TabelaDePrecoAPrazo {

        public double calculaDesconto(double valor) {
            if(valor < 100.0) {
                return 0.01;
            }else if(valor > 100.0 && valor < 500.0) {
                return 0.02;
            }else if(valor > 500.0 && valor < 1000.0) {
                return 0.05;
            }else {
                return 0d;
            }
        }

    }
    public static class Frete {

        public double calculaFrete(String estado) {

            if("SAO PAULO".equalsIgnoreCase(estado)) {
                return 7.5;
            }else if("MINAS GERAIS".equalsIgnoreCase(estado)){
                return 12.5;
            }else if("RIO DE JANEIRO".equalsIgnoreCase(estado)) {
                return 10.5;
            }else {
                return 20.0;
            }
        }

    }
}
