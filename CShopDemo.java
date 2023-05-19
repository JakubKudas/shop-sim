import java.util.ArrayList;

public class CShopDemo{
    public static void main(String[] args)throws Exception{
        try{
            CShop sklep = new CShop();
            Produkty produkt;
            SprzetMuzyczny sm; 
            sm = new SprzetMuzyczny("Producent", "model", "12344", new CData1(2000));
            produkt = new Produkty(13, "Nazwa1", 350000, 100, sm);
            sklep.addToList(produkt);

            sm = new InstrumentyDete("Producent", "model", "12345", new CData1(2000), true, "Drewno", "Stroik2");
            produkt = new Produkty(15, "Nazwa3", 350000, 100, sm);
            sklep.addToList(produkt);

            sm = new Instrumenty("Producent", "model", "12346", new CData1(2000), true, "Drewno");
            produkt = new Produkty(14, "Nazwa2", 350000, 100, sm);
            sklep.addToList(produkt);

            sklep.visualTable(sklep.getList());

            sklep.fileReader("a.txt");

            System.out.println("\nLista po dodaniu obiektow z pliku");
            sklep.bubblesort();
            sklep.visualTable(sklep.getList());


            ArrayList<Produkty> searchResults = sklep.searchList("2");
            sklep.visualTable(searchResults);

            System.out.println("\n");
            sklep.showList();

            //sklep.visualTable(sklep.searchList("13"));
            //sklep.visualTable(sklep.searchList("14"));
            //sklep.visualTable(sklep.searchList("15"));
            //sklep.edgeOfTheTable(sklep.getList());
            //sklep.showList();

            //produkt = new Produkty(17, "Nazwa4", 350000, 100, sm);
            //sklep.modifyList(13, produkt);

            //sklep.bubblesort();

            //sklep.showList();

            //sklep.removeFromList(13);

            //sklep.showList();

            //sklep.edgeOfTheTable(sklep.getList());

            //sklep.showList(sklep.searchList("Nazwa3"));

        }
        catch(Exception e){ System.out.println(e);}
    }
}