package test.logback.springboot;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.turbo.TurboFilter;
import ch.qos.logback.core.spi.FilterReply;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;


public class CRLFTurboFilter extends TurboFilter {

    private static final String MARKER = "REPLACE_MARKER";

    @Override
    public FilterReply decide(Marker marker, Logger logger, Level level, String s, Object[] objects, Throwable throwable) {

        if (marker != null && marker.equals (MarkerFactory.getMarker (MARKER))) {
            return FilterReply.ACCEPT;
        }

        boolean containRN = false;

        if ((objects != null && objects.length > 0)) {
            for (int i = 0; i < objects.length; i++) {
                String o = objects[i].toString ();
                if (o.contains ("\r") || o.contains ("\n")) {
                    objects[i] = o.replaceAll ("[\r\n]", "");
                    containRN = true;
                }
            }
        }
        if (s != null && (s.contains ("\r") || s.contains ("\n"))) {
            logger.log (MarkerFactory.getMarker (MARKER), "", level.toInt () / 1000, s.replaceAll ("[\r\n]", ""), objects, throwable);
            containRN = true;
        }

        if (containRN) {
            return FilterReply.DENY;
        }

        return FilterReply.NEUTRAL;
    }
}
