public class Produkty{
    private SprzetMuzyczny produkt;
    //private Instrumenty instrument;
    //private InstrumentyDete instrumentDety;
    private int ilosc;
    private int cena;
    private int id;
    private int category;

    public void setIlosc(int ilosc){
        this.ilosc = ilosc;
    }

    public void setCena(int cena){
        this.cena = cena;
    }

    public void setID(int id){
        this.id = id;
    }

    public void setProdukt(SprzetMuzyczny sprzet){
        this.produkt = sprzet;
    }

    /*public void setInstrument(Instrumenty sprzet){
        this.instrument = sprzet;
    }

    public void setInstrumentDety(InstrumentyDete sprzet){
        this.instrumentDety = sprzet;
    }*/

    public int getIlosc(){
        return this.ilosc;
    }

    public int getCena(){
        return this.cena;
    }

    public int getID(){
        return this.id;
    }

    public SprzetMuzyczny getProdukt(){
        return this.produkt;
    }

    /*public Instrumenty getInstrument(){
        return this.instrument;
    }

    public InstrumentyDete getInstrumentDety(){
        return this.instrumentDety;
    }*/

    public int getCategory(){
        return this.category;
    }

    public Produkty(int id, int cena, int ilosc, SprzetMuzyczny sprzet){
        setID(id);
        setCena(cena);
        setIlosc(ilosc);
        setProdukt(sprzet);
        this.category = sprzet.getCategory();
    }

    public Produkty(int id, int cena, int ilosc, Instrumenty sprzet){
        setID(id);
        setCena(cena);
        setIlosc(ilosc);
        setProdukt(sprzet);
        this.category = sprzet.getCategory();
    }

    public Produkty(int id, int cena, int ilosc, InstrumentyDete sprzet){
        setID(id);
        setCena(cena);
        setIlosc(ilosc);
        setProdukt(sprzet);
        this.category = sprzet.getCategory();
    }
}