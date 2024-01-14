package LogicTier.Utils.GuitarUtils;

import DataTier.RockMerceDAO.Guitar.GuitarDAO;
import LogicTier.Entità.Guitar;
import LogicTier.Utils.SignUpUtils.SignUpService;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GuitarService implements LogicTier.GestioneGuitars.GuitarInterface {



    private static final Logger logger = Logger.getLogger(SignUpService.class.getName());
    private Guitar guitar = new Guitar();

    GuitarDAO guitarDAO = new GuitarDAO();

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
    public ArrayList<Guitar> adminRetrieveGuitars() {
        return this.guitarDAO.admindoRetrieveGuitars();
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
    public void customGuitar(Guitar guitar) {
        this.guitarDAO.doUpdateGuitar(guitar);
    }

    @Override
    public boolean customGuitarValidation(Guitar guitar) {
        this.guitar = guitar;
        if (guitar.getName().length() <= 0 || guitar.getName().length() > 35) {
            logger.log(Level.SEVERE, "ERROR: GUITAR NAME");
            return false;
        } else {
            String reg = "[a-zA-Z0-9òàùè\\s]{1,20}";
            Pattern pattern = Pattern.compile(reg);
            Matcher matcher = pattern.matcher(guitar.getProducer());

            if (!matcher.matches()) {
                logger.log(Level.SEVERE, "ERROR: GUITAR PRODUCER ");
                return false;
            } else {

                if (guitar.getPrice() < 50 || guitar.getPrice() > 10000) {
                    logger.log(Level.SEVERE, "ERROR: GUITAR PRICE ");
                    return false;
                } else {
                    if (guitar.getDescription().length() <= 0 || guitar.getDescription().length() > 2000) {
                        logger.log(Level.SEVERE, "ERROR: GUITAR DESCRIPTION ");
                        return false;
                    } else {
                        reg = "[a-zA-Z\\s]{2,20}";
                        pattern = Pattern.compile(reg);
                        matcher = pattern.matcher(guitar.getColor());

                        if (!matcher.matches()) {
                            logger.log(Level.SEVERE, "ERROR: GUITAR COLOR");
                            return false;
                        } else {
                            reg = "[0-9]{1,5}";
                            pattern = Pattern.compile(reg);
                            matcher = pattern.matcher(String.valueOf(guitar.getDisponibility()));

                            if (!matcher.matches() || guitar.getDisponibility() <= 0) {
                                logger.log(Level.SEVERE, "ERROR: GUITAR DISPONIBILITY ");
                                return false;
                            } else {
                                if (guitar.getSound().length() <= 24) {
                                    logger.log(Level.SEVERE, "ERROR: URL INCORRECT");
                                    return false;
                                } else if (guitar.getSound().substring(0, 24).contains("https://www.youtube.com/")) {
                                    this.guitar.setSound("https://www.youtube.com/embed/" + guitar.getSound().substring(guitar.getSound().lastIndexOf("/") + 1));

                                } else {
                                    if (guitar.getCategory() == null) {
                                        logger.log(Level.SEVERE, "ERROR: CATEGORY ");
                                        return false;
                                    } else if (guitar.getCategory().compareTo("electric") != 0) {

                                        logger.log(Level.SEVERE, "ERROR: CATEGORY ");
                                        return false;
                                    } else if (guitar.getCategory().compareTo("classic") != 0) {

                                        logger.log(Level.SEVERE, "ERROR: CATEGORY ");
                                        return false;
                                    } else if (guitar.getCategory().compareTo("semiAcoustic") != 0) {

                                        logger.log(Level.SEVERE, "ERROR: CATEGORY ");
                                        return false;
                                    } else {
                                        if (guitar.getVisibility() == null) {
                                            logger.log(Level.SEVERE, "ERROR: VISIBILITY");
                                            return false;
                                        } else if (!guitar.getVisibility().equals("yes")) {

                                            logger.log(Level.SEVERE, "ERROR: VISIBILITY ");
                                            return false;
                                        } else if (!guitar.getVisibility().equals("no")) {

                                            logger.log(Level.SEVERE, "ERROR: VISIBILITY ");
                                            return false;
                                        } else {
                                            this.guitarDAO.doUpdateGuitar(this.guitar);
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        this.guitarDAO.doUpdateGuitar(this.guitar);
        return true;
    }

    @Override
    public void addGuitar(Guitar guitar) {
        this.guitarDAO.doInsertNewGuitar(guitar);
    }

    @Override
    public boolean addGuitarValidation(Guitar guitar) {
        this.guitar = guitar;
        String reg = ".{1,35}";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(guitar.getName());
        if (!matcher.matches()) {
            logger.log(Level.SEVERE, "ERROR: GUITAR NAME");
            return false;
        }

        reg = "[a-zA-Z0-9òàùèì\\s]{1,20}";
        pattern = Pattern.compile(reg);
        matcher = pattern.matcher(guitar.getProducer());

        if (!matcher.matches()) {
            logger.log(Level.SEVERE, "ERROR: GUITAR PRODUCER ");
            return false;
        }

        if (guitar.getPrice() < 50 || guitar.getPrice() > 10000) {
            logger.log(Level.SEVERE, "ERROR: GUITAR PRICE ");
            return false;
        }

        if (guitar.getDescription().length() <= 0 || guitar.getDescription().length() > 2000) {
            logger.log(Level.SEVERE, "ERROR: GUITAR DESCRIPTION ");
            return false;
        }

        reg = "[a-zA-Z\\s]{2,20}";
        pattern = Pattern.compile(reg);
        matcher = pattern.matcher(guitar.getColor());

        if (!matcher.matches()) {
            logger.log(Level.SEVERE, "ERROR: GUITAR COLOR");
            return false;
        }


        if (guitar.getDisponibility() <= 0) {
            logger.log(Level.SEVERE, "ERROR: GUITAR DISPONIBILITY ");
            return false;
        }

        if (guitar.getSound().length() <= 24) {
            logger.log(Level.SEVERE, "ERROR: URL INCORRECT");
            return false;
        }

        reg = "(jpeg|png)";
        pattern = Pattern.compile(reg);
        matcher = pattern.matcher(guitar.getImage());

        if (!guitar.getImage().contains("png") && !guitar.getImage().contains("jpeg")) {
            logger.log(Level.SEVERE, "ERROR: IMAGE FORMAT ");
            return false;
        }

        if (guitar.getCategory().length() == 0) {
            logger.log(Level.SEVERE, "ERROR: CATEGORY ");
            return false;
        } else if (guitar.getCategory().compareTo("electric") == 1) {

            logger.log(Level.SEVERE, "ERROR: CATEGORY ");
            return false;
        } else if (guitar.getCategory().compareTo("classic") == 1) {

            logger.log(Level.SEVERE, "ERROR: CATEGORY ");
            return false;
        } else if (guitar.getCategory().compareTo("semiAcoustic") == 1) {

            logger.log(Level.SEVERE, "ERROR: CATEGORY ");
            return false;
        }

        String yes="yes";
        if (guitar.getVisibility().length() == 0 ) {
            logger.log(Level.SEVERE, "ERROR: VISIBILITY");
            return false;
        }

        this.guitarDAO.doInsertNewGuitar(this.guitar);
        return true;
    }
}



















