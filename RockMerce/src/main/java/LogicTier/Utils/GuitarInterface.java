package LogicTier.Utils;
import LogicTier.Entità.Guitar;
import java.util.ArrayList;

public interface GuitarInterface {

    Guitar retrieveGuitarByID(int id);
    ArrayList<Guitar> retrieveGuitars();
    ArrayList<Guitar> retrieveGuitarsByCategory(String category);
    void updateGuitar(Guitar guitar);
    boolean UpdateGuitarValidation(Guitar guitar);
    void deleteGuitar(Guitar guitar);
    void insertNewGuitar(Guitar guitar);
    boolean InsertGuitarValidation(Guitar guitar);

    //FIELDS VALIDATION
    boolean GuitarNameValidation(String name);
    boolean GuitarProducerValidation(String producer);
    boolean GuitarPriceValidation(double price);
    boolean GuitarDescriptionValidation(String description);
    boolean GuitarColorValidation(String color);
    boolean GuitarDisponibilityValidation(int disponibility);
    boolean GuitarSoundValidation(String sound);
    boolean GuitarImageValidation(String image);
    boolean GuitarCategoryValidation(String category);
    boolean GuitarVisibilityValidation(String visibility);

}
