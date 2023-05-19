public class Produkty{
    private SprzetMuzyczny produkt;
    private int ilosc;      //3 znaki
    private int cena;       //7 znakow
    private int id;         //5 znakow
    private String nazwa;   //10 znakow
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

    public void setNazwa(String nazwa){
        this.nazwa = nazwa;
    }

    public int getIlosc(){
        return this.ilosc;
    }

    public int getCena(){
        return this.cena;
    }

    public String[] getCenaDetailed(){
        String zlote = Integer.toString(this.cena / 100);
        String grosze = String.format("%02d", this.cena % 100);
        String[] cena = {zlote, grosze};
        return cena;
    }

    public int getID(){
        return this.id;
    }

    public SprzetMuzyczny getProdukt(){
        return this.produkt;
    }

    public String getNazwa(){
        return this.nazwa;
    }

    public int getCategory(){
        return this.category;
    }

    public String toString(){
        String a = String.format("| %-5s | %-10s | %5s,%-2s | %-3s | ",
                              getID(), getNazwa(), getCenaDetailed()[0], getCenaDetailed()[1], getIlosc());
        String b = getProdukt().toString();
        return a + b;
    }

    public Produkty(int id, String nazwa, int cena, int ilosc, SprzetMuzyczny sprzet)throws Exception{
        setID(id);
        setNazwa(nazwa);
        setCena(cena);
        setIlosc(ilosc);
        setProdukt(sprzet);
        this.category = sprzet.getCategory();
    }

    public Produkty(){
        setID(0);
        setNazwa("");
        setCena(0);
        setIlosc(0);
        SprzetMuzyczny sprzet = new SprzetMuzyczny();
        setProdukt(sprzet);
        this.category = sprzet.getCategory();
    }
}