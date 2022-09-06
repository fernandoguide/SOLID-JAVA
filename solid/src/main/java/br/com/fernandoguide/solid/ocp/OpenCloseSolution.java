package br.com.fernandoguide.solid.ocp;

public class OpenCloseSolution {
    public interface TabelaDePreco {
        double calculaDesconto(double valor);
    }
    public interface ServicoDeFrete {
        double calculaFrete(String estado);
    }
    public static class TabelaDePrecoAPrazo implements TabelaDePreco{

        @Override
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
    public static class TabelaDePrecoAVista implements TabelaDePreco{

        @Override
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
    public static class Frete implements ServicoDeFrete{

        @Override
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
    public static class CalculadoraDePrecos {

        private TabelaDePreco tabela;
        private ServicoDeFrete frete;

        public CalculadoraDePrecos(TabelaDePreco tabela, ServicoDeFrete frete) {
            this.tabela = tabela;
            this.frete = frete;
        }

        public double calcula(Produto produto) {
            double desconto = tabela.calculaDesconto(produto.getValor());
            double valorFrete = frete.calculaFrete(produto.getEstado());
            return produto.getValor() * (1 - desconto) + valorFrete;
        }
    }


//    Em resumo, esse princípio fala sobre manter boas abstrações.
//    Além disso, ao utilizar o padrão de projeto STRATEGY, estamos obedecendo o OCP.
    public static void main(String[] args) {

        TabelaDePreco tabela1 = new TabelaDePrecoAVista();
        ServicoDeFrete entrega1 = new Frete();

        CalculadoraDePrecos calculadora1 = new CalculadoraDePrecos(tabela1, entrega1);
        double preco1 = calculadora1.calcula(new Produto("Notebook", 1200.0, "minas gerais"));

        System.out.println(preco1);

        TabelaDePreco tabela2 = new TabelaDePrecoAPrazo();
        ServicoDeFrete entrega2 = new Frete();

        CalculadoraDePrecos calculadora2 = new CalculadoraDePrecos(tabela2, entrega2);
        double preco2 = calculadora2.calcula(new Produto("Monitor 21'", 719.0, "sao paulo"));

        System.out.println(preco2);
    }
}
