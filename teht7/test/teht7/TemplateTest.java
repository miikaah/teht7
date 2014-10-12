/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teht7;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Terrorist
 */
public class TemplateTest {

    private Template template;

    public TemplateTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        template = new Template("${yy}, ${kaa}, ${koo}, ${nee}");
        template.korvaa("yy", "1");
        template.korvaa("kaa", "2");
        template.korvaa("koo", "3");
    }

    @After
    public void tearDown() {
    }

    @Test (expected = PuuttuvaArvoException.class)
    public void montaMuuttujaa() {
        assertEquals("1, 2, 3, ${nee}", template.evaluoi());
    }

    @Test
    public void tuntematonMuuttuja() {
        template.korvaa("nee", "4");
        template.korvaa("olematon", "Tätä ei käytetä");
        assertEquals("1, 2, 3, 4", template.evaluoi());
    }

    @Test
    public void puuttuvastaPoikkeus() {
        try {
            new Template("${olematon}").evaluoi();
            fail("evaluoi()-metodin tulisi heittää poikkeus, jos "
                    + "muuttujalle ei ole asetettu arvoa.");
        } catch (PuuttuvaArvoException expected) {
            assertEquals("Muuttujalla ${olematon} ei ole arvoa.",
                    expected.getMessage());
        }
    }
}
