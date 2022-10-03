package br.com.fernandoguide.solid.isp;

public class InterfaceSegregationSolution {

    public interface Carro {
        void abrirPorta();
    }
     public interface Eletrico extends Carro {
         void recarregarBateria();

     }
    public interface Combustivel extends Carro {
        void abastecerCombustivel();
    }

    public static class CarroCombustao implements Combustivel {
        @Override
        public void abrirPorta() {
            //abre a porta
        }
        @Override
        public void abastecerCombustivel() {
            // abastece o tanque de combustivel.
        }
    }
    public static class CarroEletrico implements Eletrico {
        @Override
        public void recarregarBateria() {
            // recarrega bateria
        }
        @Override
        public void abrirPorta() {
            // abre a porta
        }
    }
}
