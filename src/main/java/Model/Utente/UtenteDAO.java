package Model.Utente;

import Model.Connessione.ConPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * classe che utilizza i metodi di interrogazione del database riguardanti l'utente
 */

public class UtenteDAO
{

    /**
     * questo metodo restituisce tutti gli utenti
     * @return lista di utenti
     * @throws SQLException
     */

    public List<Utente> doRetrieveAll() throws SQLException
    {
        try(Connection connection= ConPool.getConnection())
        {
            try(PreparedStatement ps= connection.prepareStatement("SELECT * FROM utente")){
                ResultSet rs= ps.executeQuery();
                List<Utente> utente=new ArrayList<>();
                UtenteExtraction utenteExtraction=new UtenteExtraction();
                while(rs.next())
                {
                    utente.add(utenteExtraction.mapping(rs));
                }
                rs.close();
                return utente;
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * questo metodo crea un nuovo utente
     * @param utente
     * @return boolean
     * @throws SQLException
     */

    public Boolean createUtente(Utente utente) throws SQLException
    {
        try(Connection connection = ConPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement("INSERT INTO utente(UtNickname, UtNome, UtCognome, ValEffettuate, UtEmail, UtPW, UtLike, UtDislike, UtAvatar, UtTipologia) VALUES (?,?,?,?,?,?,?,?,?,?);")){
                ps.setString(1,utente.getUtNickname());
                ps.setString(2, utente.getNome());
                ps.setString(3, utente.getCognome());
                ps.setInt(4, utente.getValEffettuate());
                ps.setString(5, utente.getEmail());
                ps.setString(6, utente.getPassword());
                ps.setInt(7, utente.getLike());
                ps.setInt(8, utente.getDislike());
                ps.setInt(9, utente.getAvatar());
                ps.setString(10, utente.getUtTipologia());
                int rows = ps.executeUpdate();
                return rows == 1;
            }

        }
    }

    /**
     * questo metodo aggiorna l'utente tramite il nickname
     * @param utente
     * @param utNickname
     * @return boolean
     * @throws SQLException
     */

    public Boolean updateUtente(Utente utente, String utNickname) throws SQLException
    {
        try(Connection connection = ConPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement("UPDATE Utente SET UtNickname=?, UtNome=?, UtCognome=?, ValEffettuate=?, UtEmail=?, UtPW=?, UtLike=?, UtDislike=?, UtAvatar=?, UtTipologia=? WHERE UtNickname = ?")){
                ps.setString(1,utente.getUtNickname());
                ps.setString(2, utente.getNome());
                ps.setString(3, utente.getCognome());
                ps.setInt(4, utente.getValEffettuate());
                ps.setString(5, utente.getEmail());
                ps.setString(6, utente.getPassword());
                ps.setInt(7, utente.getLike());
                ps.setInt(8, utente.getDislike());
                ps.setInt(9, utente.getAvatar());
                ps.setString(10, utente.getUtTipologia());
                ps.setString(11, utNickname);
                int rows = ps.executeUpdate();
                return rows == 1;
            }

        }
    }

    /**
     * questo metodo elimina un utente tramite il nickname
     * @param utNickname
     * @return boolean
     * @throws SQLException
     */

    public Boolean deleteUtente(String utNickname) throws SQLException
    {
        try(Connection con = ConPool.getConnection()) {
            try(PreparedStatement ps = con.prepareStatement("DELETE FROM utente WHERE UtNickname = ?")){
                ps.setString(1, utNickname);
                int rows = ps.executeUpdate();
                return rows == 1;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * questo metodo permette all'utente di effettuare il login
     * @param email
     * @param password
     * @return utente
     * @throws SQLException
     */

    public Utente loginUtente(String email, String password) throws SQLException
    {
        Utente utente=new Utente();
        try(Connection con = ConPool.getConnection()) {
            try(PreparedStatement ps = con.prepareStatement("SELECT *  FROM utente WHERE UtEmail=? AND UtPW=?")){
                ps.setString(1, email);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    utente = new UtenteExtraction().mapping(rs);
                    return utente;
                }
            }
            return null;
        }
    }

    /**
     * questo metodo conta il numero di utenti
     * @return N° di utenti
     * @throws SQLException
     */

    public int countAll() throws SQLException
    {
        try(Connection con = ConPool.getConnection()){
            try(PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM utente")){
                ResultSet rs = ps.executeQuery();
                int size = 0;
                if(rs.next()){
                    size = rs.getInt(1);
                }
                return  size;
            }
        }
    }

    /**
     * questo metodo restituisce un utente tramite l'email
     * @param email
     * @return utente
     * @throws SQLException
     */

    public Utente doRetrieveUtenteByEmail(String email) throws SQLException
    {
        try(Connection connection= ConPool.getConnection())
        {
            try(PreparedStatement ps= connection.prepareStatement("SELECT * FROM utente WHERE UtEmail = ?")){
                ps.setString(1, email);
                ResultSet rs= ps.executeQuery();
                Utente utente=new Utente();
                UtenteExtraction utenteExtraction=new UtenteExtraction();
                if(rs.next()){
                    utente = utenteExtraction.mapping(rs);
                }
                rs.close();
                return utente;
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * questo metodo restituisce un utente tramite il nickname
     * @param utNickname
     * @return utente
     * @throws SQLException
     */

    public Utente doRetrieveUtenteByNickname(String utNickname) throws SQLException{
        try(Connection connection= ConPool.getConnection())
        {
            try(PreparedStatement ps= connection.prepareStatement("SELECT * FROM utente WHERE UtNickname = ?")){
                ps.setString(1,utNickname);
                ResultSet rs= ps.executeQuery();
                Utente utente=new Utente();
                UtenteExtraction utenteExtraction= new UtenteExtraction();
                if(rs.next())
                {
                    utente = utenteExtraction.mapping(rs);
                }
                rs.close();
                return utente;
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
    }
}
