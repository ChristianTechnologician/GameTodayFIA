package Model.GeneratoreFia;

import Model.Connessione.ConPool;
import Model.Videogioco.Videogioco;
import Model.Videogioco.VideogiocoExtraction;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class FillDatabase {

    private static Random random = new Random();
    private static int size = 1000;
    private static List<String> titoli = new ArrayList<String>();

    public static void main() {
        generateUtente();
        generateVideogioco();
        findVideogioco();
        generateRecensione();
    }

    private static void generateUtente() {
        try (Connection con = ConPool.getConnection()) {
            String[] tipologia = {"Horror", "Dark Fantasy", "Fantascienza", "Avventura", "Action RPG", "Open World", "Azione"};
            for (int i = 2; i < size; i++) {
                PreparedStatement ps = con.prepareStatement("INSERT INTO utente(UtNickname, UtNome, UtCognome, ValEffettuate, UtEmail, UtPW, UtLike, UtDislike, UtAvatar, UtTipologia) VALUES (?,?,?,?,?,?,?,?,?,?);");
                ps.setString(1, "nickname" + i);
                ps.setString(2, "nome" + i);
                ps.setString(3, "cognome" + i);
                ps.setInt(4, i);
                ps.setString(5, "utente" + i + "@gmail.com");
                ps.setString(6, "Password@" + i);
                ps.setInt(7, i);
                ps.setInt(8, i);
                ps.setInt(9, random.nextInt(19)+1);
                ps.setString(10, tipologia[random.nextInt(7)]);
                if (ps.executeUpdate() != 1) {
                    throw new RuntimeException("INSERT error.");
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static void generateVideogioco() {
        Integer[] pegi = {3, 7, 12, 16, 18};
        Double[] medValutazioni = {1.0, 2.0, 3.0, 4.0, 5.0};
        String[] anno = {"2018","2019","2020","2021","2022"};
        String[] mese = {"01","02","03","04","05","06","07","08","09","10","11","12"};
        String[] piattaforma = {"Playstation 4", "Xbox One", "PC"};
        String[] tipologia = {"Horror", "Dark Fantasy", "Fantascienza", "Avventura", "Action RPG", "Open World", "Azione"};
        try (Connection con = ConPool.getConnection()) {
            for (int i = 2; i < size; i++) {
                String year = anno[random.nextInt(5)];
                String month = mese[random.nextInt(12)];
                int day = random.nextInt(27)+1;
                String str=""+year+"-"+month+"-"+day;
                Date date=Date.valueOf(str);
                PreparedStatement ps = con.prepareStatement("INSERT INTO videogioco(Titolo, Pegi, TotaleVoti, CasaProduttrice, MediaValutazioni, Piattaforma, DataPubblicazione, Tipologia) VALUES (?,?,?,?,?,?,?,?);");
                ps.setString(1, "videogioco" + i);
                ps.setInt(2, pegi[random.nextInt(5)]);
                ps.setInt(3, i);
                ps.setString(4, "casaProduttrice" + i);
                ps.setDouble(5, medValutazioni[random.nextInt(5)]);
                ps.setString(6, piattaforma[random.nextInt(3)]);
                ps.setDate(7, date);
                ps.setString(8, tipologia[random.nextInt(7)]);
                if (ps.executeUpdate() != 1) {
                    throw new RuntimeException("INSERT error.");
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static void findVideogioco() {
        int w = 0;
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("select * from videogioco where not exists ( select recensione.titolo from recensione where videogioco.titolo=recensione.titolo)");
            ResultSet rs = ps.executeQuery();
            List<Videogioco> videogioco = new ArrayList<>();
            VideogiocoExtraction videogiocoExtraction = new VideogiocoExtraction();
            while (rs.next()) {
                videogioco.add(videogiocoExtraction.mapping(rs));
            }
            for (int i = 2; i < size; i++) {
                titoli.add(videogioco.get(w).getTitolo());
                if(w == 497){
                    break;
                }else{
                    w++;
                }
            }
            rs.close();
        } catch (SQLException  ex) {
            throw new RuntimeException(ex);
        }
    }

    private static void generateRecensione() {
        int z = 0;
        Integer[] mese = {1,2,3,4,5,6,7,8,9,10,11,12};
        String[] auNickname = {"IGN_Italia","CoPlaNet"};
        String[] auNome = {"Fabio","Ezio"};
        try (Connection con = ConPool.getConnection()) {
            for (int i = 2; i < size; i++) {
                PreparedStatement ps = con.prepareStatement("INSERT INTO recensione(Codice, AuNickname, RData, RTesto, AuNome, Titolo) VALUES (?,?,?,?,?,?);");
                String s = auNickname[random.nextInt(2)];
                String x = "";
                if(s.equals("IGN_Italia")){
                    x = "Fabio";
                }else{
                    x = "Ezio";
                }
                ps.setString(1,"recensione"+i);
                ps.setString(2, s);
                int month = mese[random.nextInt(12)];
                int day = random.nextInt(27)+1;
                LocalDate date = LocalDate.of(2020,month,day);
                ps.setObject(3, date);
                ps.setString(4, "testo"+i);
                ps.setString(5, x);
                ps.setString(6, titoli.get(z));
                z++;
                if (ps.executeUpdate() != 1) {
                    throw new RuntimeException("INSERT error.");
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}


