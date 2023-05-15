public class SprzetMuzyczny{
    private String producent;
    private String model;
    private String nrSeryjny;
    private CData rokProd;
    private int category = 0;

    public String getProducent(){return this.producent;}

    public String getModel(){return this.model;}

    public String getNrSeryjny(){return this.nrSeryjny;}

    public String getRokProd(){return String.valueOf(this.rokProd.pokazRok());}

    public int getCategory(){
        return this.category;
    }

    public SprzetMuzyczny(String prod, String mod, String nrSer, CData rok){
        this.producent = prod.toLowerCase();
        this.model = mod;
        this.nrSeryjny = nrSer;
        this.rokProd = rok;
    }
}