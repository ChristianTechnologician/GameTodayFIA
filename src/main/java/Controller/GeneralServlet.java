package Controller;

import Model.Commento.CommentoDAO;
import Model.GeneratoreFia.FillDatabase;
import Model.Recensione.Recensione;
import Model.Recensione.RecensioneDAO;
import Model.Utente.Utente;
import Model.Utente.UtenteDAO;
import Model.Videogioco.Videogioco;
import Model.Videogioco.VideogiocoDAO;
import ModuloFia.RicercaCostoUniforme;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

/**
 * Questa Servlet viene utilizzata per operazioni generali necessarie per l'utilizzo dell'architettura web
 */

@WebServlet(name = "GeneralServlet", value = "/General")
public class GeneralServlet extends HttpServlet {

    /**
     * metodo doGet della Servlet GeneralServlet
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{

            ArrayList<Utente> utentes = new ArrayList<Utente>();
            UtenteDAO utenteDAO = new UtenteDAO();
            utentes = (ArrayList<Utente>) utenteDAO.doRetrieveAll();
            boolean flag = true;
            for(Utente ut : utentes){
                if(ut.getNome().contains("nome")){
                    flag = false;
                    break;
                }
            }
            if(flag) {

                //Modulo Fia
                FillDatabase fillDatabase = new FillDatabase();
                fillDatabase.main();
                //

            }

            List<Videogioco> videogiocoList = new ArrayList<>();
            Recensione recensioneCommentata = new Recensione();
            Recensione recensioneUltima = new Recensione();
            Recensione recensionePrima = new Recensione();
            RecensioneDAO recensioneDAO = new RecensioneDAO();
            VideogiocoDAO videogiocoDAO = new VideogiocoDAO();
            CommentoDAO commentoDAO = new CommentoDAO();
            videogiocoList = videogiocoDAO.doRetrieveByData();
            recensioneUltima = recensioneDAO.doRetrieveByDataUltima();
            recensionePrima = recensioneDAO.doRetrieveByDataPrima();
            String codice = commentoDAO.doRetrieveCodiceByMaxCommenti();
            recensioneCommentata = recensioneDAO.doRetrieveByCodice(codice);
            request.setAttribute("listaVideogiochi",videogiocoList);
            request.setAttribute("recensioneUltima",recensioneUltima);
            request.setAttribute("recensionePrima",recensionePrima);
            request.setAttribute("recensioneCommentata",recensioneCommentata);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/partials/HomePage.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    /**
     * metodo doPost della Servlet GeneralServlet
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String home = request.getParameter("home");
        String features = request.getParameter("features");
        String aboutUs = request.getParameter("aboutus");
        if (home != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/partials/redirect.jsp");
            dispatcher.forward(request, response);
        }
        if (features != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/partials/features.jsp");
            dispatcher.forward(request, response);
        }
        if (aboutUs != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/partials/aboutUs.jsp");
            dispatcher.forward(request, response);
        }
    }
}