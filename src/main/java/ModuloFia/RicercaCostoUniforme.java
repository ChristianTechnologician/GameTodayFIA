package ModuloFia;

import Model.Videogioco.Videogioco;

import java.util.ArrayList;

/**
 * classe che implementa l'algoritmo di ricerca a costo uniforme
 */

public class RicercaCostoUniforme {

    /**
     * metodo che effettua la ricerca tramite l'algoritmo "ricerca costo uniforme"
     * @param catalogo
     * @param genere
     * @return ArrayList<Videogioco>
     */

    public ArrayList<Videogioco> ricerca(ArrayList<Videogioco> catalogo, String genere){
        ArrayList<Videogioco> lista= new ArrayList<>();
        if (catalogo.isEmpty())
            return lista;
        int valutazioneMin = 3;
        int i=0;
        boolean flag=false;
        System.out.println("Ricerca a Costo Uniforme:\n valutazioneMin: "+valutazioneMin);
        while(i < catalogo.size() && !flag){
            if (catalogo.get(i).getMediaValutazioni()>=valutazioneMin && catalogo.get(i).getTipologia().equals(genere))
                lista.add(catalogo.get(i));
            if (catalogo.get(i).getMediaValutazioni()<valutazioneMin)
                flag=true;
            i++;
        }
        System.out.println("Numero di Videogiochi trovati:"+lista.size());
        System.out.println("Numero di Iterazioni effettuate:"+i);
        return lista;
    }

}

