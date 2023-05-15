public class InstrumentyDete extends Instrumenty{
    private String stroik;
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

    public InstrumentyDete(String prod, String mod, String nrSer, CData rok, Boolean elek, String mat, String stroik){
        super(prod, mod, nrSer, rok, elek, mat);
        setStroik(stroik);
    }
}