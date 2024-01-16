import DataTier.RockMerceDAO.Guitar.GuitarDAO;
import LogicTier.Entità.Guitar;
import LogicTier.GestioneGuitars.GuitarService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;


public class AddGuitarTest {

    @Test
    public void TestGuitarImageValidation(){
        Guitar guitar=new Guitar();
        guitar.setName("AMERICAN ULTRA STRATOCASTER® HSS");
        guitar.setPrice(2650);
        guitar.setProducer("Fender");
        guitar.setCategory("electric");
        guitar.setSound("https://www.youtube.com/embed/Sagg08DrO5U");
        guitar.setImage("FenderStratocaster.dox");
        guitar.setDescription("American Ultra è la nostra serie più avanzata di chitarre e bassi per musicisti esigenti alla ricerca del massimo in termini di precisione,\n" +
                "prestazioni e timbro. L’American Ultra Stratocaster HSS presenta un esclusivo manico con profilo “Modern D” con tastiera dai bordi arrotondati per garantire ore di comfort nel suonare;\n" +
                "il tacco del manico affusolato consente un agevole accesso al registro più alto.\n");
        guitar.setVisibility("yes");
        guitar.setDisponibility(60);
        guitar.setColor("blue");

        GuitarDAO guitarDaoMock=Mockito.mock(GuitarDAO.class);
        GuitarService guitarService=new GuitarService();
        guitarService.setGuitarDAO(guitarDaoMock);

        when(guitarDaoMock.doInsertNewGuitar(guitar)).thenReturn(true);
        Assert.assertFalse(guitarService.addGuitarValidation(guitar));
    }

    @Test
    public void TestGuitarNameValidation(){
        Guitar guitar=new Guitar();
        guitar.setName("AMERICAN ULTRA STRATOCASTER® HSS678786");
        guitar.setPrice(2650);
        guitar.setProducer("Fender");
        guitar.setCategory("electric");
        guitar.setSound("https://www.youtube.com/embed/Sagg08DrO5U");
        guitar.setImage("FenderStratocaster.png");
        guitar.setDescription("American Ultra è la nostra serie più avanzata di chitarre e bassi per musicisti esigenti alla ricerca del massimo in termini di precisione,\n" +
                "prestazioni e timbro. L’American Ultra Stratocaster HSS presenta un esclusivo manico con profilo “Modern D” con tastiera dai bordi arrotondati per garantire ore di comfort nel suonare;\n" +
                "il tacco del manico affusolato consente un agevole accesso al registro più alto.\n");
        guitar.setVisibility("yes");
        guitar.setDisponibility(60);
        guitar.setColor("blue");

        GuitarDAO guitarDaoMock=Mockito.mock(GuitarDAO.class);
        GuitarService guitarService=new GuitarService();
        guitarService.setGuitarDAO(guitarDaoMock);

        when(guitarDaoMock.doInsertNewGuitar(guitar)).thenReturn(true);
        Assert.assertFalse(guitarService.addGuitarValidation(guitar));
    }
    @Test
    public void TestGuitarProducerValidation(){
        Guitar guitar=new Guitar();
        guitar.setName("AMERICAN ULTRA STRATOCASTER® HSS");
        guitar.setPrice(2650);
        guitar.setProducer("Fender$$");
        guitar.setCategory("electric");
        guitar.setSound("https://www.youtube.com/embed/Sagg08DrO5U");
        guitar.setImage("FenderStratocaster.png");
        guitar.setDescription("American Ultra è la nostra serie più avanzata di chitarre e bassi per musicisti esigenti alla ricerca del massimo in termini di precisione,\n" +
                "prestazioni e timbro. L’American Ultra Stratocaster HSS presenta un esclusivo manico con profilo “Modern D” con tastiera dai bordi arrotondati per garantire ore di comfort nel suonare;\n" +
                "il tacco del manico affusolato consente un agevole accesso al registro più alto.\n");
        guitar.setVisibility("yes");
        guitar.setDisponibility(60);
        guitar.setColor("blue");

        GuitarDAO guitarDaoMock=Mockito.mock(GuitarDAO.class);
        GuitarService guitarService=new GuitarService();
        guitarService.setGuitarDAO(guitarDaoMock);

        when(guitarDaoMock.doInsertNewGuitar(guitar)).thenReturn(true);
        Assert.assertFalse(guitarService.addGuitarValidation(guitar));
    }

    @Test
    public void TestGuitarPriceValidation(){
        Guitar guitar=new Guitar();
        guitar.setName("AMERICAN ULTRA STRATOCASTER® HSS");
        guitar.setPrice(265000);
        guitar.setProducer("Fender");
        guitar.setCategory("electric");
        guitar.setSound("https://www.youtube.com/embed/Sagg08DrO5U");
        guitar.setImage("FenderStratocaster.png");
        guitar.setDescription("American Ultra è la nostra serie più avanzata di chitarre e bassi per musicisti esigenti alla ricerca del massimo in termini di precisione,\n" +
                "prestazioni e timbro. L’American Ultra Stratocaster HSS presenta un esclusivo manico con profilo “Modern D” con tastiera dai bordi arrotondati per garantire ore di comfort nel suonare;\n" +
                "il tacco del manico affusolato consente un agevole accesso al registro più alto.\n");
        guitar.setVisibility("yes");
        guitar.setDisponibility(60);
        guitar.setColor("blue");

        GuitarDAO guitarDaoMock=Mockito.mock(GuitarDAO.class);
        GuitarService guitarService=new GuitarService();
        guitarService.setGuitarDAO(guitarDaoMock);

        when(guitarDaoMock.doInsertNewGuitar(guitar)).thenReturn(true);
        Assert.assertFalse(guitarService.addGuitarValidation(guitar));
    }

    @Test
    public void TestGuitarDescriptionValidation(){
        Guitar guitar=new Guitar();
        guitar.setName("AMERICAN ULTRA STRATOCASTER® HSS");
        guitar.setPrice(2650);
        guitar.setProducer("Fender");
        guitar.setCategory("electric");
        guitar.setSound("https://www.youtube.com/embed/Sagg08DrO5U");
        guitar.setImage("FenderStratocaster.png");
        guitar.setDescription("");
        guitar.setVisibility("yes");
        guitar.setDisponibility(60);
        guitar.setColor("blue");



        GuitarDAO guitarDaoMock=Mockito.mock(GuitarDAO.class);
        GuitarService guitarService=new GuitarService();
        guitarService.setGuitarDAO(guitarDaoMock);

        when(guitarDaoMock.doInsertNewGuitar(guitar)).thenReturn(true);
        Assert.assertFalse(guitarService.addGuitarValidation(guitar));
    }


    @Test
    public void TestGuitarColorValidation(){
        Guitar guitar=new Guitar();
        guitar.setName("AMERICAN ULTRA STRATOCASTER® HSS");
        guitar.setPrice(2650);
        guitar.setProducer("Fender");
        guitar.setCategory("electric");
        guitar.setSound("https://www.youtube.com/embed/Sagg08DrO5U");
        guitar.setImage("FenderStratocaster.png");
        guitar.setDescription("American Ultra è la nostra serie più avanzata di chitarre e bassi per musicisti esigenti alla ricerca del massimo in termini di precisione,\n" +
                "prestazioni e timbro. L’American Ultra Stratocaster HSS presenta un esclusivo manico con profilo “Modern D” con tastiera dai bordi arrotondati per garantire ore di comfort nel suonare;\n" +
                "il tacco del manico affusolato consente un agevole accesso al registro più alto.\n");
        guitar.setVisibility("yes");
        guitar.setDisponibility(60);
        guitar.setColor("b");


        GuitarDAO guitarDaoMock=Mockito.mock(GuitarDAO.class);
        GuitarService guitarService=new GuitarService();
        guitarService.setGuitarDAO(guitarDaoMock);

        when(guitarDaoMock.doInsertNewGuitar(guitar)).thenReturn(true);
        Assert.assertFalse(guitarService.addGuitarValidation(guitar));
    }

    @Test
    public void TestGuitarDisponibilityValidation(){
        Guitar guitar=new Guitar();
        guitar.setName("AMERICAN ULTRA STRATOCASTER® HSS");
        guitar.setPrice(2650);
        guitar.setProducer("Fender");
        guitar.setCategory("electric");
        guitar.setSound("https://www.youtube.com/embed/Sagg08DrO5U");
        guitar.setImage("FenderStratocaster.png");
        guitar.setDescription("American Ultra è la nostra serie più avanzata di chitarre e bassi per musicisti esigenti alla ricerca del massimo in termini di precisione,\n" +
                "prestazioni e timbro. L’American Ultra Stratocaster HSS presenta un esclusivo manico con profilo “Modern D” con tastiera dai bordi arrotondati per garantire ore di comfort nel suonare;\n" +
                "il tacco del manico affusolato consente un agevole accesso al registro più alto.\n");
        guitar.setVisibility("yes");
        guitar.setDisponibility(0);
        guitar.setColor("blue");



        GuitarDAO guitarDaoMock=Mockito.mock(GuitarDAO.class);
        GuitarService guitarService=new GuitarService();
        guitarService.setGuitarDAO(guitarDaoMock);

        when(guitarDaoMock.doInsertNewGuitar(guitar)).thenReturn(true);
        Assert.assertFalse(guitarService.addGuitarValidation(guitar));
    }

    @Test
    public void TestGuitarCategoryValidation(){
        Guitar guitar=new Guitar();
        guitar.setName("AMERICAN ULTRA STRATOCASTER® HSS");
        guitar.setPrice(2650);
        guitar.setProducer("Fender");
        guitar.setCategory("");
        guitar.setSound("https://www.youtube.com/embed/Sagg08DrO5U");
        guitar.setImage("FenderStratocaster.png");
        guitar.setDescription("American Ultra è la nostra serie più avanzata di chitarre e bassi per musicisti esigenti alla ricerca del massimo in termini di precisione,\n" +
                "prestazioni e timbro. L’American Ultra Stratocaster HSS presenta un esclusivo manico con profilo “Modern D” con tastiera dai bordi arrotondati per garantire ore di comfort nel suonare;\n" +
                "il tacco del manico affusolato consente un agevole accesso al registro più alto.\n");
        guitar.setVisibility("yes");
        guitar.setDisponibility(60);
        guitar.setColor("blue");

        GuitarDAO guitarDaoMock=Mockito.mock(GuitarDAO.class);
        GuitarService guitarService=new GuitarService();
        guitarService.setGuitarDAO(guitarDaoMock);

        when(guitarDaoMock.doInsertNewGuitar(guitar)).thenReturn(true);
        Assert.assertFalse(guitarService.addGuitarValidation(guitar));
    }

    @Test
    public void TestGuitarVisibilityValidation(){
        Guitar guitar=new Guitar();
        guitar.setName("AMERICAN ULTRA STRATOCASTER® HSS");
        guitar.setPrice(2650);
        guitar.setProducer("Fender");
        guitar.setCategory("electric");
        guitar.setSound("https://www.youtube.com/embed/Sagg08DrO5U");
        guitar.setImage("FenderStratocaster.png");
        guitar.setDescription("American Ultra è la nostra serie più avanzata di chitarre e bassi per musicisti esigenti alla ricerca del massimo in termini di precisione,\n" +
                "prestazioni e timbro. L’American Ultra Stratocaster HSS presenta un esclusivo manico con profilo “Modern D” con tastiera dai bordi arrotondati per garantire ore di comfort nel suonare;\n" +
                "il tacco del manico affusolato consente un agevole accesso al registro più alto.\n");
        guitar.setVisibility("");
        guitar.setDisponibility(60);
        guitar.setColor("blue");


        GuitarDAO guitarDaoMock=Mockito.mock(GuitarDAO.class);
        GuitarService guitarService=new GuitarService();
        guitarService.setGuitarDAO(guitarDaoMock);

        when(guitarDaoMock.doInsertNewGuitar(guitar)).thenReturn(true);
        Assert.assertFalse(guitarService.addGuitarValidation(guitar));
    }






    @Test
    public void TestGuitarSoundValidation(){
        Guitar guitar=new Guitar();
        guitar.setName("AMERICAN ULTRA STRATOCASTER® HSS");
        guitar.setPrice(2650);
        guitar.setProducer("Fender");
        guitar.setCategory("electric");
        guitar.setSound("");
        guitar.setImage("FenderStratocaster.png");
        guitar.setDescription("American Ultra è la nostra serie più avanzata di chitarre e bassi per musicisti esigenti alla ricerca del massimo in termini di precisione,\n" +
                "prestazioni e timbro. L’American Ultra Stratocaster HSS presenta un esclusivo manico con profilo “Modern D” con tastiera dai bordi arrotondati per garantire ore di comfort nel suonare;\n" +
                "il tacco del manico affusolato consente un agevole accesso al registro più alto.\n");
        guitar.setVisibility("yes");
        guitar.setDisponibility(60);
        guitar.setColor("blue");

        GuitarDAO guitarDaoMock=Mockito.mock(GuitarDAO.class);
        GuitarService guitarService=new GuitarService();
        guitarService.setGuitarDAO(guitarDaoMock);

        when(guitarDaoMock.doInsertNewGuitar(guitar)).thenReturn(true);
        Assert.assertFalse(guitarService.addGuitarValidation(guitar));
    }








    @Test
    public void TestGuitarValidation(){
        Guitar guitar=new Guitar();
        guitar.setName("AMERICAN ULTRA STRATOCASTER® HSS");
        guitar.setPrice(2650);
        guitar.setProducer("Fender");
        guitar.setCategory("electric");
        guitar.setSound("https://www.youtube.com/embed/Sagg08DrO5U");
        guitar.setImage("FenderStratocaster.png");
        guitar.setDescription("American Ultra è la nostra serie più avanzata di chitarre e bassi per musicisti esigenti alla ricerca del massimo in termini di precisione,\n" +
                "prestazioni e timbro. L’American Ultra Stratocaster HSS presenta un esclusivo manico con profilo “Modern D” con tastiera dai bordi arrotondati per garantire ore di comfort nel suonare;\n" +
                "il tacco del manico affusolato consente un agevole accesso al registro più alto.\n");
        guitar.setVisibility("yes");
        guitar.setDisponibility(60);
        guitar.setColor("blue");

        GuitarDAO guitarDaoMock=Mockito.mock(GuitarDAO.class);
        GuitarService guitarService=new GuitarService();
        guitarService.setGuitarDAO(guitarDaoMock);

        when(guitarDaoMock.doInsertNewGuitar(guitar)).thenReturn(true);
        Assert.assertTrue(guitarService.addGuitarValidation(guitar));
    }

}