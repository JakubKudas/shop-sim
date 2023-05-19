public class SprzetMuzyczny{
    private String producent; //8 znakow
    private String model;     //5 znakow
    private String nrSeryjny; //4 znaki
    private CData1 rokProd;   //4 znaki
    private int category = 0;

    public String getProducent(){
        return this.producent;
    }

    public String getModel(){
        return this.model;
    }

    public String getNrSeryjny(){
        return this.nrSeryjny;
    }

    public String getRokProd(){
        return String.valueOf(this.rokProd.getInitialYear());
    }

    public int getCategory(){
        return this.category;
    }
    
    public String toString(){
        return String.format("%-3s | %-9s | %-5s | %-5s | %-5s |",
                             "SM", producent, model, nrSeryjny, getRokProd());
    }

    public SprzetMuzyczny(String prod, String mod, String nrSer, CData1 rok)throws Exception{
        this.producent = prod;
        this.model = mod;
        this.nrSeryjny = nrSer;
        this.rokProd = rok;
    }

    public SprzetMuzyczny(){
        this.producent = "";
        this.model = "";
        this.nrSeryjny = "";
        this.rokProd = new CData1();
    }
}