package br.com.fernandoguide.solid.isp;

public class InterfaceSegregationSolution {

    public interface Carrro {
        void abrirPorta();
    }
     public interface Eletrico extends Carrro {
         void recarregarBateria();

     }
    public interface Combustivel extends Carrro {
        void abastecerCombustivel();
    }

    public static class Fiesta implements Combustivel {
        @Override
        public void abrirPorta() {
            //abre a porta
        }
        @Override
        public void abastecerCombustivel() {
            // abastece o tanque de combustivel.
        }
    }
    public static class FocusElectric implements Eletrico {
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
