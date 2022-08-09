package Controller;

import Model.Utente.Utente;
import Model.Utente.UtenteDAO;
import Model.Videogioco.Videogioco;
import Model.Videogioco.VideogiocoDAO;
import ModuloFia.Decrescente;
import ModuloFia.RicercaCostoUniforme;
import ModuloFia.RicercaLineare;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Servlet per la gestione del proprio profilo da parte dell'utente
 */

@WebServlet(name = "UtenteProfileServlet", value = "/utenteProfile/*")
public class UtenteProfileServlet extends Controllo {

    /**
     * metodo doGet della Servlet UtenteProfileServlet
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String inputRicerca = request.getParameter("gestioneUtente");
            switch (inputRicerca) {
                case "1":
                    ArrayList<Videogioco> lista1 = (ArrayList<Videogioco>) request.getSession(false).getAttribute("listaRis");
                    ArrayList<Videogioco> videogiochiCinqueStelle1 = new ArrayList<>();
                    for(Videogioco videogioco : lista1){
                        if(videogioco.getMediaValutazioni() == 5.00){
                            videogiochiCinqueStelle1.add(videogioco);
                        }
                    }
                    request.removeAttribute("listaRisultati");
                    request.setAttribute("listaRisultati",videogiochiCinqueStelle1);
                    RequestDispatcher dispatcher001 = request.getRequestDispatcher("/WEB-INF/views/ModuloFIA/paginaConsigliati.jsp");
                    dispatcher001.forward(request, response);
                    break;
                case "2":
                    ArrayList<Videogioco> lista2 = (ArrayList<Videogioco>) request.getSession().getAttribute("listaRis");
                    ArrayList<Videogioco> videogiochiQuattroStelle = new ArrayList<>();
                    for(Videogioco videogioco : lista2){
                        if(videogioco.getMediaValutazioni() >= 4.00 && videogioco.getMediaValutazioni() < 5.00){
                            videogiochiQuattroStelle.add(videogioco);
                        }
                    }
                    request.removeAttribute("listaRisultati");
                    request.setAttribute("listaRisultati",videogiochiQuattroStelle);
                    RequestDispatcher dispatcher002 = request.getRequestDispatcher("/WEB-INF/views/ModuloFIA/paginaConsigliati.jsp");
                    dispatcher002.forward(request, response);
                    break;
                case "3":
                    ArrayList<Videogioco> lista3 = (ArrayList<Videogioco>) request.getSession().getAttribute("listaRis");
                    ArrayList<Videogioco> videogiochiTreStelle = new ArrayList<>();
                    for(Videogioco videogioco : lista3){
                        if(videogioco.getMediaValutazioni() >= 3.00 && videogioco.getMediaValutazioni() < 4.00){
                            videogiochiTreStelle.add(videogioco);
                        }
                    }
                    request.removeAttribute("listaRisultati");
                    request.setAttribute("listaRisultati",videogiochiTreStelle);
                    RequestDispatcher dispatcher003 = request.getRequestDispatcher("/WEB-INF/views/ModuloFIA/paginaConsigliati.jsp");
                    dispatcher003.forward(request, response);
                    break;

                //ModuloFIA
                case "ricercaUniforme" :
                    Utente utFIAUniforme = new Utente();
                    utFIAUniforme = (Utente) request.getSession().getAttribute("userUt");
                    RicercaCostoUniforme ricercaCostoUniforme = new RicercaCostoUniforme();
                    ArrayList<Videogioco> catalogoUniforme = new ArrayList<Videogioco>();
                    ArrayList<Videogioco> listaRisultatiUniforme = new ArrayList<Videogioco>();
                    VideogiocoDAO videogiocoDAOUniforme = new VideogiocoDAO();
                    catalogoUniforme = (ArrayList<Videogioco>) videogiocoDAOUniforme.doRetrieveAllByTitolo();
                    final long startSortTime = System.nanoTime();
                    Collections.sort(catalogoUniforme,new Decrescente());
                    final long endSortTime = System.nanoTime();
                    System.out.println("Sort time: " + (endSortTime - startSortTime)+"\n\n" );
                    //start
                    final long startTime = System.nanoTime();
                    listaRisultatiUniforme = ricercaCostoUniforme.ricerca(catalogoUniforme,utFIAUniforme.getUtTipologia());
                    //end
                    final long endTime = System.nanoTime();
                    //Report
                    System.out.println("Execution time: " + (endTime - startTime)+"\n\n" );
                    request.getSession(false).removeAttribute("listaRis");
                    request.removeAttribute("listaRisultati");
                    request.getSession().setAttribute("listaRis",listaRisultatiUniforme);
                    ArrayList<Videogioco> videogiochiCinqueStelle = new ArrayList<>();
                    for(Videogioco videogioco : listaRisultatiUniforme){
                        if(videogioco.getMediaValutazioni() == 5){
                            videogiochiCinqueStelle.add(videogioco);
                        }
                    }
                    request.setAttribute("listaRisultati",videogiochiCinqueStelle);
                    RequestDispatcher dispatcher00 = request.getRequestDispatcher("/WEB-INF/views/ModuloFIA/paginaConsigliati.jsp");
                    dispatcher00.forward(request, response);
                    break;
                case "ricercaLineare" :
                    Utente utFIALineare = new Utente();
                    utFIALineare = (Utente) request.getSession().getAttribute("userUt");
                    ArrayList<Videogioco> catalogoLineare = new ArrayList<Videogioco>();
                    ArrayList<Videogioco> listaRisultatiLineari = new ArrayList<Videogioco>();
                    VideogiocoDAO videogiocoDAOLineari = new VideogiocoDAO();
                    catalogoLineare = (ArrayList<Videogioco>) videogiocoDAOLineari.doRetrieveAllByTitolo();
                    RicercaLineare ricercaLineare = new RicercaLineare();
                    final long startTime1 = System.nanoTime();
                    listaRisultatiLineari = ricercaLineare.ricerca(catalogoLineare,utFIALineare.getUtTipologia());
                    //end
                    final long endTime1 = System.nanoTime();
                    //Report
                    System.out.println("Execution time: " + (endTime1 - startTime1)+"\n\n" );
                    request.getSession().removeAttribute("listaRis");
                    request.removeAttribute("listaRisultati");
                    request.getSession().setAttribute("listaRis",listaRisultatiLineari);
                    ArrayList<Videogioco> videogiochiCinqueStelleLineari = new ArrayList<>();
                    for(Videogioco videogioco : listaRisultatiLineari){
                        if(videogioco.getMediaValutazioni() == 5){
                            videogiochiCinqueStelleLineari.add(videogioco);
                        }
                    }
                    request.setAttribute("listaRisultati",videogiochiCinqueStelleLineari);
                    RequestDispatcher dispatcher0 = request.getRequestDispatcher("/WEB-INF/views/ModuloFIA/paginaConsigliati.jsp");
                    dispatcher0.forward(request, response);
                    break;
               //

                case "gestioneProfiloUtente":
                    //String id = request.getSession(false).getId();
                    Utente utente = new Utente();
                    utente = (Utente) request.getSession().getAttribute("userUt");
                    UtenteDAO utenteDAO = new UtenteDAO();
                    request.setAttribute("utente", utente);
                    RequestDispatcher dispatcher1 = request.getRequestDispatcher("/WEB-INF/views/utente/gestioneProfilo.jsp");
                    dispatcher1.forward(request, response);
                    break;
                case "modificaProfilo":
                    RequestDispatcher dispatcher2 = request.getRequestDispatcher("/WEB-INF/views/utente/modifica.jsp");
                    dispatcher2.forward(request, response);
                    break;
                case "registrazione" :
                    RequestDispatcher dispatcher5 = request.getRequestDispatcher("/WEB-INF/views/partials/registrazione.jsp");
                    dispatcher5.forward(request, response);
                    break;
                case "eliminaProfilo":
                    HttpSession session2 = request.getSession(false);
                    Utente utente1 = new Utente();
                    utente1 = (Utente) request.getSession().getAttribute("userUt");
                    UtenteDAO utenteDAO1 = new UtenteDAO();
                    utenteDAO1.deleteUtente(utente1.getUtNickname());
                    session2.removeAttribute("userUt");
                    RequestDispatcher dispatcher3 = request.getRequestDispatcher("/WEB-INF/views/partials/redirect.jsp");
                    dispatcher3.forward(request, response);
                    break;
                case "logoutUtente":
                    HttpSession session = request.getSession(false);
                    Utente utente2 = (Utente) session.getAttribute("userUt");
                    session.removeAttribute("userUt");
                    RequestDispatcher dispatcher4 = request.getRequestDispatcher("/WEB-INF/views/partials/redirect.jsp");
                    dispatcher4.forward(request, response);
                    break;
                default:
                    break;
            }

        } catch (ServletException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * metodo doGet della Servlet UserProfileServlet
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String path = getPath(request);
            switch (path) {
                case "/modifica":
                    //String id = request.getSession(false).getId();
                    Utente utenteSessione = new Utente();
                    utenteSessione = (Utente) request.getSession().getAttribute("userUt");
                    UtenteDAO utenteDAO = new UtenteDAO();
                    Utente utente = new Utente();
                    utente.setUtNickname(request.getParameter("UtNickname"));
                    utente.setNome(request.getParameter("UtNome"));
                    utente.setCognome(request.getParameter("UtCognome"));
                    utente.setAvatar(Integer.parseInt(request.getParameter("UtAvatar")));
                    utente.setEmail(request.getParameter("UtEmail"));
                    utente.setPassword(request.getParameter("UtPW"));
                    utente.setValEffettuate(utenteSessione.getValEffettuate());
                    utente.setLike(utenteSessione.getLike());
                    utente.setDislike(utenteSessione.getDislike());
                    utente.setUtTipologia(request.getParameter("UtTipologia"));
                    utenteDAO.updateUtente(utente, utenteSessione.getUtNickname());
                    Utente utente1 = new Utente();
                    utente1 = utenteDAO.doRetrieveUtenteByEmail(utente.getEmail());
                    request.setAttribute("utente", utente1);
                    request.getRequestDispatcher("/WEB-INF/views/utente/gestioneProfilo.jsp").forward(request, response);
                    break;
                case "/registrazione" :
                    Utente utenteRegistrazione = new Utente();
                    utenteRegistrazione.setUtNickname(request.getParameter("UtNickname"));
                    utenteRegistrazione.setNome(request.getParameter("UtNome"));
                    utenteRegistrazione.setCognome(request.getParameter("UtCognome"));
                    utenteRegistrazione.setAvatar(Integer.parseInt(request.getParameter("UtAvatar")));

                    //ModuloFIA
                    utenteRegistrazione.setUtTipologia(request.getParameter("UtTipologia"));
                    //

                    utenteRegistrazione.setEmail(request.getParameter("UtEmail"));
                    utenteRegistrazione.setPassword(request.getParameter("UtPW"));
                    utenteRegistrazione.setValEffettuate(0);
                    utenteRegistrazione.setLike(0);
                    utenteRegistrazione.setDislike(0);
                    UtenteDAO utenteDAO1 = new UtenteDAO();
                    utenteDAO1.createUtente(utenteRegistrazione);
                    request.getSession(true).setAttribute("userUt",utenteRegistrazione);
                    request.getRequestDispatcher("/WEB-INF/views/partials/redirect.jsp").forward(request, response);
                    break;
                default:
                    break;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
