package ModuloFia;

import Model.Videogioco.Videogioco;

import java.util.Comparator;

/**
 * classe che effettua la comparazione decrescente dei videogiochi
 */

public class Decrescente implements Comparator<Videogioco> {
    @Override
    public int compare(Videogioco e1, Videogioco e2) {
        if (Double.compare(e1.getMediaValutazioni(), e2.getMediaValutazioni())==0)
            return 0;
        if (Double.compare(e1.getMediaValutazioni(), e2.getMediaValutazioni())==-1)
            return 1;
        else
            return -1;
    }
}

