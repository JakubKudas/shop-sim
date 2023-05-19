public class InstrumentyDete extends Instrumenty{
    private String stroik;      //9 znakow
    private int category = 2;

    private void setStroik(String stroik){
        this.stroik = stroik;
    }

    public String getStroik(){
        return this.stroik;
    }

    public int getCategory(){
        return this.category;
    }

    public String toString(){
        return String.format("%-3s | %-9s | %-5s | %-5s | %-5s | %-4s | %-8s | %-9s |",
                             "IDe", getProducent(), getModel(), getNrSeryjny(), getRokProd(), ElektrycznyYN(), getMaterial(), stroik);
    }

    public InstrumentyDete(String prod, String mod, String nrSer, CData1 rok, Boolean elek, String mat, String stroik)throws Exception{
        super(prod, mod, nrSer, rok, elek, mat);
        setStroik(stroik);
    }

    public InstrumentyDete(){
        super();
        setStroik("");
    }
}