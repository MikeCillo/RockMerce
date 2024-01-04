package LogicTier.GestioneGuitars;

import DataTier.RockMerceDAO.Guitar.GuitarDAO;
import LogicTier.Entità.Guitar;


import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GuitarService implements GuitarInterface {


    private static final Logger logger = Logger.getLogger(GuitarService.class.getName());
    private Guitar guitar=new Guitar();

    GuitarDAO guitarDAO=new GuitarDAO();

    public Guitar getGuitar() {
        return guitar;
    }

    public void setGuitar(Guitar guitar) {
        this.guitar = guitar;
    }

    public void setGuitarDAO(GuitarDAO guitarDAO) {
        this.guitarDAO = guitarDAO;
    }

    @Override
    public Guitar retrieveGuitarByID(int id) {
        return this.guitarDAO.doRetrieveGuitarById(id);
    }

    @Override
    public ArrayList<Guitar> retrieveGuitars() {
        return this.guitarDAO.doRetrieveGuitars();
    }

    @Override
    public ArrayList<Guitar> retrieveGuitarsByCategory(String category) {
        return this.guitarDAO.doRetrieveGuitarsByCategory(category);
    }

    @Override
    public void deleteGuitar(Guitar guitar) {
        this.guitarDAO.deleteGuitar(guitar);
    }

    @Override
    public void updateGuitar(Guitar guitar) {
        this.guitarDAO.doUpdateGuitar(guitar);
    }

    @Override
    public boolean UpdateGuitarValidation(Guitar guitar){
        this.guitar=guitar;

        if(GuitarNameValidation(guitar.getName()) && GuitarPriceValidation(guitar.getPrice())
                && GuitarProducerValidation(guitar.getProducer()) && GuitarCategoryValidation(guitar.getCategory())
                && GuitarDisponibilityValidation(guitar.getDisponibility()) && GuitarSoundValidation(guitar.getSound())
                && GuitarDescriptionValidation(guitar.getDescription()) && GuitarVisibilityValidation(guitar.getVisibility())
                && GuitarColorValidation(guitar.getColor())){
            updateGuitar(this.guitar);
            return true;
        }
        return false;
    }

    @Override
    public void insertNewGuitar(Guitar guitar) {
        this.guitarDAO.doInsertNewGuitar(guitar);
    }


    public boolean InsertGuitarValidation(Guitar guitar){
        this.guitar=guitar;

        if(GuitarNameValidation(guitar.getName()) && GuitarPriceValidation(guitar.getPrice())
                && GuitarProducerValidation(guitar.getProducer()) && GuitarCategoryValidation(guitar.getCategory())
                && GuitarDisponibilityValidation(guitar.getDisponibility()) && GuitarSoundValidation(guitar.getSound())
                && GuitarImageValidation(guitar.getImage()) && GuitarDescriptionValidation(guitar.getDescription()) &&
                GuitarVisibilityValidation(guitar.getVisibility()) && GuitarColorValidation(guitar.getColor())){
            insertNewGuitar(this.guitar);
            return true;
        }
        return false;
    }




    //GUITAR NAME VALIDATION
    @Override
    public boolean GuitarNameValidation(String name) {
        String reg = "[a-zA-Z\\s]{1,20}";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(name);

        if (!matcher.matches()) {
            logger.log(Level.SEVERE, "ERROR: GUITAR NAME INCORRECT");
            return false;
        } else {
            return true;
        }
    }


    //GUITAR PRODUCER VALIDATION
    @Override
    public boolean GuitarProducerValidation(String producer) {
        String reg = "[a-zA-Z0-9\\s]{1,20}";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(producer);

        if (!matcher.matches()) {
            logger.log(Level.SEVERE, "ERROR: GUITAR PRODUCER INCORRECT");
            return false;
        } else {
            return true;
        }
    }


    //GUITAR PRICE VALIDATION
    @Override
    public boolean GuitarPriceValidation(double price) {
        String reg = "[0-9.]{1,20}";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(String.valueOf(price));

        if (!matcher.matches() || price <= 49.99) {
            logger.log(Level.SEVERE, "ERROR: GUITAR PRICE INCORRECT");
            return false;
        } else {
            return true;
        }
    }


    //GUITAR DESCRIPTION VALIDATION
    @Override
    public boolean GuitarDescriptionValidation(String description) {
        String reg = "[a-zA-Z0-9.,\\s]{1,200}";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(description);

        if (!matcher.matches()) {
            logger.log(Level.SEVERE, "ERROR: GUITAR DESCRIPTION INCORRECT");
            return false;
        } else {
            return true;
        }
    }


    //GUITAR COLOR VALIDATION
    @Override
    public boolean GuitarColorValidation(String color) {
        String reg = "[a-zA-Z\\s]{1,20}";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(color);

        if (!matcher.matches()) {
            logger.log(Level.SEVERE, "ERROR: GUITAR COLOR INCORRECT");
            return false;
        } else {
            return true;
        }
    }


    //GUITAR DISPONIBILITY VALIDATION
    @Override
    public boolean GuitarDisponibilityValidation(int disponibility) {
        String reg = "[0-9]{1,20}";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(String.valueOf(disponibility));

        if (!matcher.matches() || disponibility <= 0) {
            logger.log(Level.SEVERE, "ERROR: GUITAR DISPONIBILITY INCORRECT");
            return false;
        } else {
            return true;
        }
    }


    //GUITAR SOUND VALIDATION
    @Override
    public boolean GuitarSoundValidation(String sound) {
        if (sound.length() <= 35) {
            logger.log(Level.SEVERE, "ERROR: URL INCORRECT");
            return false;
        } else if (sound.substring(0, 24).contains("https://www.youtube.com/")) {
            String givenURL = sound;
            this.guitar.setSound("https://www.youtube.com/embed/" + givenURL.substring(givenURL.lastIndexOf("/") + 1));
            return true;
        }
        logger.log(Level.SEVERE, "ERROR: URL INCORRECT");
        return false;
    }



    //GUITAR IMAGE VALIDATION
    @Override
    public boolean GuitarImageValidation(String image) {
       if(image.contains(".png") || image.contains(".jpeg")){
           return true;
       }
       else {
           logger.log(Level.SEVERE, "ERROR: IMAGE FORMAT INCORRECT");
           return false;
       }
    }

    //GUITAR CATEGORY VALIDATION
    @Override
    public boolean GuitarCategoryValidation(String category) {
        if (category == null) {
            logger.log(Level.SEVERE, "ERROR: CATEGORY INCORRECT");
            return false;
        } else if (category.compareTo("electric") == 0) {
            this.guitar.setCategory("electric");
            return true;
        } else if (category.compareTo("classic") == 0) {
            this.guitar.setCategory("classic");
            return true;
        } else if (category.compareTo("semiAcoustic") == 0) {
            this.guitar.setCategory("semiAcoustic");
            return true;
        }
        return false;
    }


    //GUITAR VISIBILITY VALIDATION

    @Override
    public boolean GuitarVisibilityValidation(String visibility) {
        if (visibility == null) {
            logger.log(Level.SEVERE, "ERROR: VISIBILITY INCORRECT");
            return false;
        } else if (visibility.equals("yes")) {
            this.guitar.setVisibility("yes");
            return true;
        } else if (visibility.equals("no")) {
            this.guitar.setVisibility("no");
            return true;
        }
        return false;
    }


}



























