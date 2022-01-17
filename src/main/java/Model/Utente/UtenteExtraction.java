package Model.Utente;

import Model.Autore.Autore;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UtenteExtraction {
    public Utente mapping(ResultSet rs) throws SQLException
    {
        Utente utente= new Utente();
        utente.setUtNickname(rs.getString(1));
        utente.setNome(rs.getString(2));
        utente.setCognome(rs.getString(3));
        utente.setAvatar(rs.getInt(4));
        utente.setEmail(rs.getString(5));
        utente.setPassword(rs.getString(6));
        utente.setValEffettuate(rs.getInt(7));
        utente.setLike(rs.getInt(8));
        utente.setDislike(rs.getInt(9));

        return utente;
    }
}