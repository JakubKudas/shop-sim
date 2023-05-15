public class Instrumenty extends SprzetMuzyczny{
    private Boolean elektryczny;
    private String material;
    private int category = 1;

    public Boolean getElektryczny(){return this.elektryczny;}
    public String getMaterial(){return this.material;}
    
    public int getCategory(){
        return this.category;
    }

    public Instrumenty(String prod, String mod, String nrSer, CData rok, Boolean elek, String mat){
        super(prod, mod, nrSer, rok);
        this.elektryczny = elek;
        this.material = mat;
    }
}