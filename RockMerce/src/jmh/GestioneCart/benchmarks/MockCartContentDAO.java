package GestioneCart.benchmarks;

import DataTier.RockMerceDAO.CartContent.CartContentDAO;
import LogicTier.Entità.Guitar;
import java.util.ArrayList;

public class MockCartContentDAO extends CartContentDAO {

    // Simula il recupero del contenuto di un carrello
    @Override
    public ArrayList<Guitar> getCartContent(int cartId) {
        ArrayList<Guitar> guitars = new ArrayList<>();

        // Crea CARTE_SIZE chitarre fittizie (simulando un carrello pieno)
        for (int i = 0; i < CartState.CART_SIZE; i++) {
            Guitar g = new Guitar();
            g.setId(i);
            guitars.add(g);
        }
        return guitars;
    }

    // Simula l'inserimento nel DB
    @Override
    public void insertIntoCartContent(int cartId, Guitar guitar) {
        return;
    }

    // Simula la rimozione di una chitarra specifica
    @Override
    public void removeGuitarFromCartContent(int cartId, int guitarId) {
        return;
    }

    // Simula la rimozione multipla di chitarre
    @Override
    public void removeGuitarsFromCartContent(ArrayList<Guitar> guitars,int cartId) {
        return;
    }
}