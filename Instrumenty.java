public class Instrumenty extends SprzetMuzyczny{
    private Boolean elektryczny;    //3 znaki
    private String material;        //8 znakow
    private int category = 1;

    public Boolean getElektryczny(){
        return this.elektryczny;
    }

    public String getMaterial(){
        return this.material;
    }
    
    public int getCategory(){
        return this.category;
    }

    public String ElektrycznyYN(){
        if(elektryczny){
            return "Tak";
        }
        else{
            return "Nie";
        }
    }

    public String toString(){
        return String.format("%-3s | %-9s | %-5s | %-5s | %-5s | %-4s | %-8s |",
                             "I", getProducent(), getModel(), getNrSeryjny(), getRokProd(), ElektrycznyYN(), material);
    }

    public Instrumenty(String prod, String mod, String nrSer, CData1 rok, Boolean elek, String mat)throws Exception{
        super(prod, mod, nrSer, rok);
        this.elektryczny = elek;
        this.material = mat;
    }

    public Instrumenty(){
        super();
        this.elektryczny = false;
        this.material = "";
    }
}