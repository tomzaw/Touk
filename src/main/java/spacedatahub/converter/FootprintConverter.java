package spacedatahub.converter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.AttributeConverter;
import org.springframework.stereotype.Component;
import spacedatahub.struct.Footprint;

@Component
public class FootprintConverter implements AttributeConverter<Footprint, String> {

    private static final String SEPARATOR = ";";

    public FootprintConverter() {
    }

    @Override
    public String convertToDatabaseColumn(Footprint f) {

        if (f == null) {
            return null;
        }

        return "x1=" + f.getX1() + ";x2=" + f.getX2() + ";y1=" + f.getY1() + ";y2=" + f.getY2();
    }

    @Override
    public Footprint convertToEntityAttribute(String s) {

        if (s == null || s.isEmpty()) {
            return null;
        }

        String[] pieces = s.split(SEPARATOR);

        if (pieces.length < 4) {
            return null;
        }

        Pattern p = Pattern.compile("(x1|x2|y1|y2)=(\\d+||\\d+\\.\\d+)");
        Matcher m;

        double[] coordinates = new double[4];
        for (int i = 0; i < pieces.length; i++) {

            m = p.matcher(pieces[i]);

            if (m.matches()) {

                coordinates[i] = Double.parseDouble(m.group(2));
            }
        }

        return new Footprint(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);
    }
}
