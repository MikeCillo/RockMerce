Drop database if exists RockMerceDB;
Create database RockMerceDB;
use  RockMerceDB;




create table CreditCard(
id int(5) primary key AUTO_INCREMENT,
cardNumber varchar (16) not null,
owner varchar (40) not null,
expireDate varchar(30) not null,
cvv integer(3) not null
);





create table Guitar (
id int(5) primary key AUTO_INCREMENT,
name varchar(50) not null,
price double  not null default 0.00,
producer varchar (20) not null,
category varchar (15)  CHECK (category in ('classic','electric','semiAcoustic')),
disponibility integer(5),
sound varchar(200) ,
image varchar(200) ,
description varchar (2000),
visibility varchar (3) not null CHECK(visibility in('yes','no')),
color varchar(20)
);


create table Cart(
id int (5) primary key auto_increment,
tempTotal double not null default 0.00,
numGuitars integer not null
);


create table CartContent(
id int(5) ,
cart int (5),
quantity integer(5),
price double  not null default 0.00,
Foreign key (cart) references Cart(id),
Foreign Key (id) references Guitar(id),
CONSTRAINT CartCon_Key PRIMARY KEY (id,cart)
);



create table Customer(
username varchar (20),
email varchar (30),
name varchar(20) not null,
surname varchar(20) not null,
password varchar (30) not null,
phone varchar (10) not null,
country varchar (20) not null,
city varchar (20) not null,
address varchar(30) not null,
cardId int (5),
cartId int(5),
FOREIGN KEY (cartId) REFERENCES Cart(id),
FOREIGN KEY (cardId) REFERENCES CreditCard(id),
CONSTRAINT User_Key PRIMARY KEY (username,email)
);


create table Admin(
username varchar (20),
email varchar (30),
name varchar(20) not null,
surname varchar(20) not null,
password varchar (30) not null,
CONSTRAINT User_Key PRIMARY KEY (username,email)
);



create table Checkout(
id int(5) primary key auto_increment,
total double not null,
sendDate varchar(20) not null,
orderDate varchar(20) not null,
cartId int(5),
FOREIGN KEY (cartId) REFERENCES Cart(id)
);


create table CheckoutContent(
idGuitar int(5) primary key auto_increment,
name varchar(50) not null,
quantity integer(5),
price double  not null,
producer varchar (20) not null,
category varchar (15) not null CHECK (category in ('classic','electric','semiAcoustic')),
color varchar(20),
idCheckout int (5),
Foreign key (idCheckout) references Checkout(id)
);






insert into Guitar (id,name,price,producer,category,disponibility,description,sound,image,visibility,color)
	values (1,"AMERICAN ULTRA STRATOCASTER HSS",2650.00,"FENDER","electric","60",
    "American Ultra è la nostra serie più avanzata di chitarre e bassi per musicisti esigenti alla ricerca del massimo in termini di precisione,
prestazioni e timbro. L’American Ultra Stratocaster HSS presenta un esclusivo manico con profilo “Modern D” con tastiera dai bordi arrotondati per garantire ore di comfort nel suonare;
il tacco del manico affusolato consente un agevole accesso al registro più alto.
Una veloce tastiera con raggio di curvatura variabile 10”-14” con 22 tasti medium-jumbo permette un’esecuzione di assolo senza fatica e precisa,
mentre i pickup single-coil Ultra Noiseless™ Hot e il pickup humbucker Double Tap, insieme alle opzioni di cablaggio avanzate, 
offrono possibilità timbriche infinite senza ronzio. Questo versatile strumento allo stato dell’arte vi ispirerà a spingere la vostra musica","https://www.youtube.com/embed/wHO2XKfe4L4",
"GuitarsPic/FAusBlue.png","no","blue"),

(2,"AMERICAN ULTRA STRATOCASTER HSS",2650.00,"FENDER","electric","0",
    "American Ultra è la nostra serie più avanzata di chitarre e bassi per musicisti esigenti alla ricerca del massimo in termini di precisione,
prestazioni e timbro. L’American Ultra Stratocaster HSS presenta un esclusivo manico con profilo “Modern D” con tastiera dai bordi arrotondati per garantire ore di comfort nel suonare;
il tacco del manico affusolato consente un agevole accesso al registro più alto.
Una veloce tastiera con raggio di curvatura variabile 10”-14” con 22 tasti medium-jumbo permette un’esecuzione di assolo senza fatica e precisa,
mentre i pickup single-coil Ultra Noiseless™ Hot e il pickup humbucker Double Tap, insieme alle opzioni di cablaggio avanzate, 
offrono possibilità timbriche infinite senza ronzio. Questo versatile strumento allo stato dell’arte vi ispirerà a spingere la vostra musica","https://www.youtube.com/embed/wHO2XKfe4L4","GuitarsPic/AusTang.png","yes","tangerine"),

(3,"LES PAUL 70S DELUXE",2799.00,"GIBSON","electric","50",
    "Rilasciato per la prima volta nel 1969, il Deluxe ha visto l'introduzione del Mini Humbucker™ nella gamma Les Paul™. 
I Mini Humbucker mantengono le prestazioni prive di ronzio dei loro cugini a grandezza naturale,
ma con una tonalità un po' più chiara e brillante. La nuova Deluxe ha caratteristiche ispirate a quei primi modelli degli anni '70,
con un corpo in mogano senza peso in rilievo e top in acero rilegato, un manico in mogano rilegato con un profilo a C arrotondato,
meccaniche Keystone in stile vintage, un capotasto Graph Tech® e un tradizionale layout di controllo Les Paul con 2 controlli Volume e 2 Tone cablati a mano con condensatori Orange Drop®.",
"https://www.youtube.com/embed/3T_FY2lnIq4","GuitarsPic/LesPaulGold.png","yes","gold"),

(4,"LES PAUL 70S DELUXE",2799.00,"GIBSON","electric","50",
    "Rilasciato per la prima volta nel 1969, il Deluxe ha visto l'introduzione del Mini Humbucker™ nella gamma Les Paul™. 
I Mini Humbucker mantengono le prestazioni prive di ronzio dei loro cugini a grandezza naturale,
ma con una tonalità un po' più chiara e brillante. La nuova Deluxe ha caratteristiche ispirate a quei primi modelli degli anni '70,
con un corpo in mogano senza peso in rilievo e top in acero rilegato, un manico in mogano rilegato con un profilo a C arrotondato,
meccaniche Keystone in stile vintage, un capotasto Graph Tech® e un tradizionale layout di controllo Les Paul con 2 controlli Volume e 2 Tone cablati a mano con condensatori Orange Drop®.",
"https://www.youtube.com/embed/3T_FY2lnIq4","GuitarsPic/LesPaulRed.png","yes","red"),

(5,"CAMARO VR 2-90 BLUE SPARKLE",280.00,"EKO","electric","100",
    "La chitarra elettrica Camaro VR 2-90 di Eko Guitars nella colorazione Blue Sparkle spicca per la configurazione dei Pickup P-90 gestibili con il selettore toggle a tre vie 
e per la paletta abbinata alla tonalità blue sparkle del corpo. Le moderne Camaro di Eko Guitars, ispirate dallo storico modello anni settanta,
si presentano in tutte le colorazioni disponibili, con corpo in Pioppo, legno apprezzato per la sua leggerezza, manico in Acero e tastiera in South American Roupanà.
Nei modelli Cream e Blue Sparkle i due pickup P-90 e la paletta abbinata al corpo, donano alla Camaro un look credibile dal profumo seventies. 
La finitura Vintage Burst è dotata di pickup Wilkinson configurati nella formula HSS con l'humbucker splittabile tramite interruttore push/push posizionato sul potenziomentro del Tono.","https://www.youtube.com/embed/EeU2K4q1pHk",
"GuitarsPic/EkoCamBlue.png","yes","blue"),

(6,"CAMARO VR 2-90 BLUE SPARKLE",280.00,"EKO","electric","100",
    "La chitarra elettrica Camaro VR 2-90 di Eko Guitars nella colorazione Blue Sparkle spicca per la configurazione dei Pickup P-90 gestibili con il selettore toggle a tre vie 
e per la paletta abbinata alla tonalità blue sparkle del corpo. Le moderne Camaro di Eko Guitars, ispirate dallo storico modello anni settanta,
si presentano in tutte le colorazioni disponibili, con corpo in Pioppo, legno apprezzato per la sua leggerezza, manico in Acero e tastiera in South American Roupanà.
Nei modelli Cream e Blue Sparkle i due pickup P-90 e la paletta abbinata al corpo, donano alla Camaro un look credibile dal profumo seventies. 
La finitura Vintage Burst è dotata di pickup Wilkinson configurati nella formula HSS con l'humbucker splittabile tramite interruttore push/push posizionato sul potenziomentro del Tono.","https://www.youtube.com/embed/EeU2K4q1pHk",
"GuitarsPic/EkoCream.png","yes","cream"),

(7,"EKO GUITARS - VIBRA 100 NATURAL",160.00,"EKO","classic","100",
    "La Vibra 100 Natural è la chitarra classica della serie Vibra di Eko Guitars con top in Cedro,fasce e fondo in Mogano laminato, manico in Mogano e tastiera in South American Roupanà. 
È ideale per chi vuole iniziare lo studio della chitarra con uno strumento strutturalmente sicuro,realizzato con legni selezionati e seguendo la tradizione liuteristica spagnola, ad un costo contenuto.",
"https://www.youtube.com/embed/2Zq8ulNUp80","GuitarsPic/EkoVibraClassic.png","yes","mahogany"),

(8,"ALHAMBRA LINEA PROFESIONALAL",2869.00,"ALHAMBRA","classic","60",
    "Un modello dalle splendide doti di affidabilità e comfort, che riesce a coprire tutte le aspettative di soddisfazione.
Con il modello Línea Profesional di Alhambra Guitars, ci troviamo di fronte a una chitarra che riesce sempre 
a soddisfare quei chitarristi che vogliono continuare il loro miglioramento con uno strumento di alta qualità.","https://www.youtube.com/embed/rB9NMz0ESwo","GuitarsPic/AlhambraClassic.png","yes","natural"),

(9,"CORDOBA GK STUDIO NEGRA",680.00,"CORDOBA","classic","50",
    "Costruita con un top in abete europeo massiccio, fondo e fasce in palissandro, soft cutaway e preamplificatore Fishman Presys Blend,
la GK Studio Negra è lo strumento da concerto per eccellenza e un best seller di Córdoba. Nata per il palcoscenico, 
la GK Studio Negra è costruita con una profondità del corpo, un manico e una larghezza del capotasto leggermente più sottili rispetto a una chitarra spagnola tradizionale.
Il manico è progettato per avere un rilievo minimo o nullo,offrendo un'action bassa per facilitare il comfort e la rapidità di esecuzione. 
I suonatori di flamenco scelgono questo modello per un tono più basso e robusto, in contrasto con il suono brillante e scattante della chitarra flamenca ''blanca''. 
Tuttavia, qualsiasi chitarrista alla ricerca di un nuovo timbro si sentirà a casa. Come ogni chitarra Córdoba, questo modello è dotato di truss rod a due vie integrato nel manico. 
Altre caratteristiche di pregio sono la finitura lucida, la placca trasparente in stile flamenco e le corde Savarez.","https://www.youtube.com/embed/Hc8OM_WA1T4","GuitarsPic/CordobaClassic.png","yes","cream"),


(10,"KEB M0 3.0 12 TASTI J-45",4299.00,"GIBSON","semiAcoustic","30","Gibson è orgogliosa di annunciare il terzo modello per artisti realizzato in collaborazione con il poliedrico chitarrista,
cantante, cantautore e cinque volte vincitore del premio Grammy® Keb Mo: il Keb' Mo' 3.0 12-Fret J-45 .
Questo nuovo modello di base è ideale per un'ampia varietà di composizioni musicali e stili esecutivi e presenta un top in abete Sitka invecchiato
termicamente, fondo e fasce in mogano e un manico in mogano con scala da 25 con tastiera in palissandro, profilo Keb Mo personalizzato
e Meccaniche Grover® aperte. Il manico si unisce al corpo al 12° tasto, conferendo a questa chitarra il tono più rotondo,
più corposo e l'eccezionale comfort di gioco che solo un manico a 12 tasti può fornire.			
Dotato di elettronica LR Baggs, è pronto per lo studio e il palco, nel momento in cui lo estrai dalla custodia rigida inclusa.","https://www.youtube.com/embed/XusoVhOU2x0","GuitarsPic/kebSemiAcoustic.png","yes","Flash of vintage sun"),

(11,"CD-60SCE",300.00,"FENDER","semiAcoustic","60","Combinando una potente elettronica di bordo, incluso un sintonizzatore integrato, con un grande tono
e una facile giocabilità, il CD-60SCE è ideale per i giocatori di livello intermedio che sono pronti a collegarsi.
Caratterizzato da un corpo scollato veneziano per un facile accesso al tasto superiore,
un solido top in abete rosso per un volume maggiore e un suono nitido, collo facile da riprodurree schiena e fianchi
in mogano, il CD-60SCE è perfetto per il divano, il falò o il caffè-ovunque si desidera classica giocabilità Fender e suono.","https://www.youtube.com/embed/1Gm6GifxMSs","GuitarsPic/cdSemAcoustic.png","yes","black - natural"),

(12,"MIA IV SA VINTAGE SUNBURST",420.00,"EKO","semiAcoustic","80","La MIA IV SA (SemiAcustica) rappresenta il rientro di una chitarra Eko di grande successo. Una hollow body ispirata alla storica Eko Barracuda,
ma in una versione leggermente alleggerita: più sottile il profilo, più stiloso lo shape.
Una SemiAcustica in legno Massello, resa leggera dalla scelta della Paulonia ; il top è in acero fiammato mentre il manico è in mogano.
La tastiera è in Parinari Campestris, legno che la Eko Guitars ha scelto per sostituire il Palissandro, dopo il suo inserimento tra le specie protette.
La paletta della MIA IV SA permette alle corde di avere una traiettoria perfettamente lineare dalle meccaniche fino al ponte,
eliminando punti di frizione sul nut tipici delle semiacustiche e permettendo di avere uno strumento che resta più accordato e con ottime caratteristiche di sustain.
Lo stile vintage dei pickup nasconde due humbucker (in Alnico 5) decisamente potenti ed equilibrati
che trovano in un potenziometro push-push la possibilità di split aumentando la versatilità di questo strumento.","https://www.youtube.com/embed/8N60ZBLLuN4","GuitarsPic/ekoVintageSemiAcoustic.png","yes","Sunburst");


insert into CreditCard(id,cardNumber,owner,expireDate,cvv)
values(1,"8907456743249384","Giovanni Cuomo","11/2030","999"),
(2,"8301928342810283","Michele Cillo","01/2030","144"),
(3,"4653009812837643","Guido Meda","01/2029","464"),
(4,"4653009812837621","Barack Obabma","01/2031","764"),
(5,"9853009812837643","Jack D'angel","01/2030","101");




insert into Cart(id,tempTotal,numGuitars)
values(1,"0.00",0),
(2,"0.00",0),
(3,"3709.00",3),
(4,"300.00",1),
(5,"0.00",0);

insert into CartContent(id,cart,quantity,price)
values(5,3,1,280.00),
      (3,3,1,2799.00),
      (11,4,1,300.00);


insert into Customer (username,email,name,surname,password,phone,country,city,address,cardId,cartId)
values("TheWall","backslash@gmail.com","David","Gilmour","cdkCDK999!","8372937493","England","London","Cambridge",3,3),
("TheBeatles","mimmo@gmail.com","Paul","McCartney","letITBE2020!","7630287632","England","Liverpool","Abbey road",4,4),
("Nirvana","smell@gmail.com","Kurt","Kobain","SMELL10spirits!","9382716273","United States","Portland","vedi vidi vici 10",5,5);


insert into Admin (username,email,name,surname,password)
values("GionnyBeGood1999","gionny@rockmerce.com","Giovanni","Cuomo","Aa123456."),
("MikeCillo","mike@rockmerce.com","Michele","Cillo","milanMILAN1!"),
("Rosso","matteo@rockmerce.com","Matteo","Magliulo","matteomagliulo01.!");

insert into Checkout(id,total,sendDate,orderDate,cartId)
values(1,5300.00,"15/06/2023","02/06/2023",5),
(2,1160.00,"10/04/2023","12/05/2023",5),
(3,420,"10/03/2022","14/04/2022",4);



insert into CheckoutContent(idGuitar,name,quantity,price,producer,category,color,idCheckout)
values(1,"AMERICAN ULTRA STRATOCASTER HSS",2,5300.00,"FENDER","electric","blue",1),
(2,"EKO GUITARS - VIBRA 100 NATURAL",3,480.00,"EKO","classic","mahogany",2),
(3,"CORDOBA GK STUDIO NEGRAL",1,680.00,"CORDOBA","classic","cream",2),
(4,"MIA IV SA VINTAGE SUNBURST",1,420.00,"EKO","semiAcoustic","Sunburst",3);





