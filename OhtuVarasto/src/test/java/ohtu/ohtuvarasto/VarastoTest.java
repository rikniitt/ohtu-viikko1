package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void konstr() {
        varasto = new Varasto(-1);
        varasto = new Varasto(0);
        varasto = new Varasto(1,1);
        varasto = new Varasto(1,2);
        varasto = new Varasto(-1,2);
        varasto = new Varasto(-1,-1);
        varasto.toString();
    }
    
    
    
    
    
    
    
    @Test
    public void konstruktoriLuoTyhjanVarastonNegatiivisellaTilavuudella() {
        varasto = new Varasto(-5.00);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }
   
    @Test
    public void konstruktoriLuoVarastonAlkuSaldolla() {
        varasto = new Varasto(5.00, 2.5);
        assertEquals(2.5, varasto.getSaldo(), vertailuTarkkuus);
    }
   
    @Test
    public void konstruktoriLuoVarastonAlkuSaldollaJokaEiMahduTilavuuteen() {
        varasto = new Varasto(5.00, 666.0);
        assertEquals(5.0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenLisaysEiVaikutaSaldoon() {
        varasto.lisaaVarastoon(3.0);
        
        double saldo = varasto.getSaldo();
        
        varasto.lisaaVarastoon(-5.0);
        
        assertEquals(saldo, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastoEiYlivuoda() {
        varasto.lisaaVarastoon(12.0);    
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void ottaminenTyhjastaVarastostaEiPalautaMitaan() {
        double saatu = varasto.otaVarastosta(5.0);
        assertEquals(0, saatu, vertailuTarkkuus);
    }
    
    @Test
    public void liikaaOttaminenTyhjentaaVaraston() {
        varasto.lisaaVarastoon(3.0);
        double saatu = varasto.otaVarastosta(5.0);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void liikaaOttaminenAntaaSaatavat() {
        varasto.lisaaVarastoon(3.0);
        double saatu = varasto.otaVarastosta(5.0);
        assertEquals(3.0, saatu, vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenOttaminenEiPalautaMitaan() {
        varasto.lisaaVarastoon(3.0);
        double saatu = varasto.otaVarastosta(-5.0);
        assertEquals(0, saatu, vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenOttaminenEiVaikutaSaldoon() {
        varasto.lisaaVarastoon(3.0);
        double saatu = varasto.otaVarastosta(-5.0);
        assertEquals(3.0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void merkkiEsitysOikeanMuotoinen() {
        varasto.lisaaVarastoon(3.0);
        
        assertEquals("saldo = 3.0, vielä tilaa 7.0", varasto.toString());
    }
}

