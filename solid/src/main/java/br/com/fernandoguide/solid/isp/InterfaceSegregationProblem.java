package br.com.fernandoguide.solid.isp;

public class InterfaceSegregationProblem {

    public interface Car {
        void recarregarBateria();
        void abrirPorta();

        void abastecerCombustivel();
    }

    public static class Fiesta implements Car {
        @Override
        public void recarregarBateria() {
            throw new UnsupportedOperationException("Este carro é a combustão !!");
        }

        @Override
        public void abrirPorta() {
            //abre a porta
        }

        @Override
        public void abastecerCombustivel() {
            // abastece o tanque de combustivel.
        }
    }

    public static class FocusElectric implements Car {

        @Override
        public void recarregarBateria() {
            // recarrega bateria
        }

        @Override
        public void abrirPorta() {
            // abre a porta
        }

        @Override
        public void abastecerCombustivel() {
            throw new UnsupportedOperationException("Este carro é elétrico , nao possui combustível!!");
        }
    }
}
