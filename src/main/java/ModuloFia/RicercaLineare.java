package ModuloFia;

import Model.Videogioco.Videogioco;

import java.util.ArrayList;

/**
 * classe che implementa l'algoritmo di ricerca lineare
 */
public class RicercaLineare {

    /**
     * metodo che effettua la ricerca tramite l'algoritmo "ricerca lineare"
     * @param catalogo
     * @param genere
     * @return ArrayList<Videogioco>
     */

    public ArrayList<Videogioco> ricerca(ArrayList<Videogioco> catalogo, String genere){
        ArrayList<Videogioco> lista= new ArrayList<>();
        if (catalogo.isEmpty())
            return lista;
        int valutazioneMin=3;
        int i=0;
        System.out.println("Ricerca Lineare:\n valutazione Min: "+valutazioneMin);
        while(i<catalogo.size()){
            if(catalogo.get(i).getMediaValutazioni()>=valutazioneMin && catalogo.get(i).getTipologia().equals(genere))
                lista.add(catalogo.get(i));
            i++;
        }
        System.out.println("Numero di Videogiochi trovati:"+lista.size());
        System.out.println("Numero di Iterazioni effettuate:"+i);
        return lista;
    }

}
