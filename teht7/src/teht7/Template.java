/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teht7;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Terrorist
 */
public class Template {

    private Map<String, String> muuttujat;
    String teksti;

    public Template(String teksti) {
        this.teksti = teksti;
        this.muuttujat = new HashMap<>();
    }

    public void korvaa(String muuttuja, String arvo) {
        this.muuttujat.put(muuttuja, arvo);
    }

    public String evaluoi() {
        String tulos = teksti;
        for (Map.Entry<String, String> entry : muuttujat.entrySet()) {
            String korvattava = "\\$\\{" + entry.getKey() + "\\}";
            tulos = tulos.replaceAll(korvattava, entry.getValue());
        }
        tarkastaPuuttuvat(tulos);
        return tulos;
    }

    private void tarkastaPuuttuvat(String teksti) {
        Matcher m = Pattern.compile(".*\\$\\{.+\\}.*").matcher(teksti);
        if (m.find()) {
            throw new PuuttuvaArvoException("Muuttujalla " + m.group()
                    + " ei ole arvoa.");
        }
    }
}
