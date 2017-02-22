package com.mcr.mcr_galeri;

import java.util.ArrayList;
import java.util.List;

public class Modeller {
    final String[] list_alfaRomeo=new String[]{"33", "75", "145", "146", "147", "155", "156", "159", "164", "166", "Brera", "Giulietta", "GT", "GTV", "MiTo", "Spider"};
    final String[] list_astonMartin = new String[] {"DB7", "DB9", "DBS", "Rapide", "Vanquish", "Vantage"};
    final String[] list_audi = new String[] {"A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "R8", "RS", "S Serisi", "TT", "80 Serisi", "90 Serisi", "100 Serisi", "200 Serisi"};
    final String[] list_bentley = new String[] {"Arnage", "Continental", "Mulsanne"};
    final String[] list_bmw = new String[]{"116", "118i", "120", "123d", "130i", "316", "318", "320", "323","324", "325", "328", "330",
            "335", "418i", "420", "428", "518i", "520", "523i", "524", "525", "528", "530", "535", "540i", "550xd", "630", "635", "640", "645Ci", "650", "728", "730", "735",
            "740", "745", "750", "760", "840", "850", "i8", "M1", "M3", "M4", "M5", "M6", "Z3", "Z4", "Z8" };
    final String[] list_chery = new String[] {"Alia","Chance","Kimo","Niche"};
    final String[] list_chevrolet = new String[] {"Aveo","Camaro","Caprice","Cavalier","Celebrity","Corvette","Cruze","Epica","Evanda","Geo Storm","Impala","Kalos","Lacetti","Lumina","Malibu","Monte Carlo","Rezzo","Spark"};
    final String[] list_Chrysler = new String[] {"300 C","300 M","Concorde","Crossfire","Le Baron","LHS","Neon","PT Cruiser","Sebring","Stratus"};
    final String[] list_Citroen = new String[] {"AX","BX","C1","C2","C3","C3 Picasso","C4","C4 Picasso","C5","C6","C-Elysée","DS3","DS4","DS5","Evasion","Saxo","Xantia","XM","Xsara","ZX"};
    final String[] list_Dacia = new String[] {"1310","Lodgy","Logan","Nova","Sandero","Solenza"};
    final String[] list_Daewoo = new String[] {"Chairman","Espero","Lanos","Leganza","Matiz","Nexia","Nubira","Racer","Super Saloon","Tico"};
    final String[] list_Daihatsu = new String[] {"Applause","Charade","Copen","Cuore","Materia","Move","Sirion","YRV"};
    final String[] list_Dodge = new String[] {"Avenger","Challenger","Charger","Intrepid","Magnum","Viper"    };
    final String[] list_Eagle = new String[] {"Talon"};
    final String[] list_Fiat = new String[] {"126 Bis","500 Ailesi","Albea","Brava","Bravo","Coupe","Croma","Idea","Linea","Marea","Mirafiori","Palio","Panda","Punto","Siena","Stilo","Tempra","Tipo","Uno"};
    final String[] list_Ford = new String[] {"B-Max","C-Max","Escort","Festiva","Fiesta","Focus","Fusion","Granada","Ka","Mondeo","Mustang","Orion","Probe","Scorpio","Sierra","S-Max","Taunus","Taurus","Thunderbird"};
    final String[] list_Honda = new String[] {"Accord","City","Civic","CRX","CR-Z","Integra","Jazz","Legend","Prelude","S2000","Shuttle","Stream"};
    final String[] list_Hyundai = new String[] {"Accent","Accent Blue","Accent Era","Atos","Coupe","Elantra","Excel","Genesis","Getz","Grandeur","i10","i20","i20 Troy","i30","i40","iX20","Matrix","S-Coupe","Sonata","Trajet"};
    final String[] list_Infiniti = new String[] {"G","I30","M","Q30"};
    final String[] list_Isuzu = new String[] {"Gemini"};
    final String[] list_Kia = new String[] {"Capital","Carens","Cee'd","Cerato","Clarus","Magentis","Opirus","Optima","Picanto","Pride","Pro Cee'd","Rio","Sephia","Shuma","Soul","Venga"};
    final String[] list_Lada = new String[]{"Kalina", "Priora", "Samara", "Tavria", "VAZ", "Vega"};
    final String[] list_Mazda = new String[] {"2","3","5","6","121","323","626","929","Lantis","MX","Premacy","RX","Xedos"};
    final String[] list_Mercedes = new String[] {"A","AMG GT","B","C","CL","CLA","CLC","CLK","CLS","E","Maybach S","R","S","SL","SLK","SLS AMG","190","200","220","230","240","250","260","280","300","320","350","380","420","500","560","600"};
    final String[] list_Mitsubishi = new String[] {"3000GT","Attrage","Carisma","Colt","Eclipse","Galant","Grandis","Lancer","Lancer Evolution","Space Star"};
    final String[] list_Nissan = new String[] {"200 SX","300 ZX","350 Z","Almera","Altima","Bluebird","Maxima","Micra","Note","NX Coupe","Primera","Skyline GT-R","Sunny"};
    final String[] list_Opel = new String[] {"Agila","Ascona","Astra","Calibra","Cascada","Corsa","GT (Roadster)","Insignia","Kadett","Manta","Meriva","Omega","Rekord","Senator","Signum","Tigra","Vectra","Zafira"};
    final String[] list_Peugeot = new String[] {"106","107","205","206","207","208","301","305","306","307","308","309","405","406","407","508","605","607","806","5008","RCZ"};
    final String[] list_Pontiac = new String[] {"Firebird","Solstice","Sunbird"};
    final String[] list_Proton = new String[] {"218","313","315","316","413","415","416","418","420","Gen-2","Saga","Savvy","Waja"};
    final String[] list_Renault = new String[] {"Clio","Espace","Fluence","Laguna","Latitude","Megane","Modus","R 5","R 9","R 11","R 12","R 19","R 21","R 25","Safrane","Scenic","Grand Scenic","Symbol","Twingo","Vel Satis"};
    final String[] list_Rover = new String[] {"25","45","75","111","200","214","216","218","220","400","414","416","420","620","623","820","827","Streetwise"};
    final String[] list_Seat = new String[] {"Altea","Cordoba","Exeo","Ibiza","Leon","Marbella","Toledo"};
    final String[] list_Skoda = new String[] {"Citigo","Fabia","Favorit","Felicia","Forman","Octavia","Rapid","Roomster","Superb"};
    final String[] list_Subaru = new String[] {"BRZ","Impreza","Justy","Legacy","Leone","Vivio"};
    final String[] list_Suzuki = new String[] {"Alto","Baleno","Liana","Maruti","Splash","Swift","SX4 S-Cross"};
    final String[] list_Tata = new String[] {"Indica","Indigo","Manza","Vista"};
    final String[] list_Tofaş = new String[] {"Doğan","Kartal","Murat","Serçe","Şahin"};
    final String[] list_Toyota = new String[] {"Auris","Avensis","Camry","Carina","Celica","Corolla","Corona","Cressida","Crown","GT 86","MR2","Paseo","Prius","Starlet","Supra","Tercel","Urban Cruiser","Verso","Yaris"};
    final String[] list_Volkswagen = new String[] {"Bora","Corrado","EOS","Golf","Jetta","Lupo","New Beetle","Passat","Passat Variant","Phaeton","Polo","Santana","Scirocco","Touran","Vento","VW CC"};
    final String[] list_Volvo = new String[] {"C30","C70","S40","S60","S70","S80","S90","V40","V40 Cross Country","V50","V60","V70","240","244","340","360","440","460","480","740","760","850","940","960"};

    public final List<String[]> list_modeller=new ArrayList<>();
    public Modeller()
    {
        list_modeller.add(list_alfaRomeo);
        list_modeller.add(list_astonMartin);
        list_modeller.add(list_audi);
        list_modeller.add(list_bentley);
        list_modeller.add(list_bmw);
        list_modeller.add(list_chery);
        list_modeller.add(list_chevrolet);
        list_modeller.add(list_Chrysler);
        list_modeller.add(list_Citroen);
        list_modeller.add(list_Dacia);
        list_modeller.add(list_Daewoo);
        list_modeller.add(list_Daihatsu);
        list_modeller.add(list_Dodge);
        list_modeller.add(list_Eagle);
        list_modeller.add(list_Fiat);
        list_modeller.add(list_Ford);
        list_modeller.add(list_Honda);
        list_modeller.add(list_Hyundai);
        list_modeller.add(list_Infiniti);
        list_modeller.add(list_Isuzu);
        list_modeller.add(list_Kia);
        list_modeller.add(list_Lada);
        list_modeller.add(list_Mazda);
        list_modeller.add(list_Mercedes);
        list_modeller.add(list_Mitsubishi);
        list_modeller.add(list_Nissan);
        list_modeller.add(list_Opel);
        list_modeller.add(list_Peugeot);
        list_modeller.add(list_Pontiac);
        list_modeller.add(list_Proton);
        list_modeller.add(list_Renault);
        list_modeller.add(list_Rover);
        list_modeller.add(list_Seat);
        list_modeller.add(list_Skoda);
        list_modeller.add(list_Subaru);
        list_modeller.add(list_Suzuki);
        list_modeller.add(list_Tata);
        list_modeller.add(list_Tofaş);
        list_modeller.add(list_Toyota);
        list_modeller.add(list_Volkswagen);
        list_modeller.add(list_Volvo);
    }
}
