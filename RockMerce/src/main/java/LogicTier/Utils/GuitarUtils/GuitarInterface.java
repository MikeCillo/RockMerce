package LogicTier.GestioneGuitars;
import LogicTier.Entità.Guitar;
import java.util.ArrayList;

public interface GuitarInterface {

    Guitar retrieveGuitarByID(int id);
    ArrayList<Guitar> retrieveGuitars();
    ArrayList<Guitar> retrieveGuitarsByCategory(String category);

    ArrayList<Guitar> adminRetrieveGuitars();
    void customGuitar(Guitar guitar);
    boolean customGuitarValidation(Guitar guitar);
    void deleteGuitar(Guitar guitar);
    void addGuitar(Guitar guitar);
    boolean addGuitarValidation(Guitar guitar);




}
