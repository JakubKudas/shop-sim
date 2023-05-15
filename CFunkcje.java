import java.util.ArrayList;
import java.util.Scanner;

public class CFunkcje{
    //?Funkcja do konwertowania danych z obiektu na string -> takich funkcji musi być tyle ile klas -> Ta funkcje powinna być w def klasy klasie
    //?Funkcja do konwertowania danych ze stringu na obiekt -> może byc potrzebna do zpaisywania/odczytywania
    //wyjątki

    ArrayList<ArrayList<String>> produkty = new ArrayList<>();
    ArrayList<ArrayList<String>> produktySorted = new ArrayList<>();

    public ArrayList<ArrayList<String>> getList(){
        return this.produkty;
    }

    public ArrayList<ArrayList<String>> getSortedList(){
        return this.produktySorted;
    }

    private String toString(int x){
        return String.valueOf(x);
    }

    private String toString(Boolean x){
        return String.valueOf(x);
    }

    private SprzetMuzyczny getPointer(Produkty produkt){
        /*if(produkt.getCategory() == 0){
            return produkt.getProdukt();
        }
        else if(produkt.getCategory() == 1){
            return produkt.getInstrument();
        }
        else if(produkt.getCategory() == 2){
            return produkt.getInstrumentDety();
        }*/
        return produkt.getProdukt();//ta funkcja jest chyba niepotrzebna bo w produkty.java zmienilem i wszystko odbywa sie za pomoca getProdukty() ktory jest castowany na odpowiednia klase
    }

    private void daneSprzet(Produkty produkt, ArrayList<String> lista){//zamien getPointer na produkt.getProdukt()
        lista.add(toString(produkt.getCategory()));
        lista.add(toString(produkt.getID()));
        lista.add(toString(produkt.getCena()));
        lista.add(toString(produkt.getIlosc()));
        lista.add(getPointer(produkt).getProducent());
        lista.add(getPointer(produkt).getModel());
        lista.add(getPointer(produkt).getNrSeryjny());
        lista.add(getPointer(produkt).getRokProd());
    }

    private void daneInstrument(Produkty produkt, ArrayList<String> lista){//zamien getPointer na produkt.getProdukt()
        lista.add(toString(((Instrumenty) getPointer(produkt)).getElektryczny()));
        lista.add(((Instrumenty) getPointer(produkt)).getMaterial()); 
    }

    public void addToList(Produkty produkt){
            ArrayList<String> lista = new ArrayList<>();
            daneSprzet(produkt, lista);
            if(produkt.getCategory() == 1){
                daneInstrument(produkt, lista);
            }
            else if(produkt.getCategory() == 2){
                daneInstrument(produkt, lista);
                lista.add(((InstrumentyDete) getPointer(produkt)).getStroik());//zamien getPointer na produkt.getProdukt()
            }
            produkty.add(lista);
        //dodac jeszcze reszte dodawania dla innych klas
        //w csv ta funkcja powinna byc w petli
    }

    public void removeFromList(String searchVar){
        for(int i = 0; i < produkty.size(); i++){
            if(searchVar.equals(produkty.get(i).get(1))){//szukanie po id
                produkty.remove(i);
            }
        }
        //Tutaj najpierw przeszukiwanie bedzie, potem jak znajdzie to dopiero usuwa listę o odpowiednim indeksie
    }

    public void modifyList(String id, ArrayList<String> lista){
        for(int i = 0; i < produkty.size(); i++){
            if(id.equals(produkty.get(i).get(1))){//szukanie po id
                produkty.remove(i);
                produkty.add(lista);
            }
        }
        //Przeszukiwanie, potem scanner na wszystkie rzeczy jakie istnieją w tym konkretnym obiekcie
    }

    public void searchList(String searchVar){
        for(int i = 0; i < produkty.size(); i++){
            if(searchVar.equals(produkty.get(i).get(1))){//szukanie po id
                System.out.println(produkty.get(i));
            }
            if(searchVar.equals(produkty.get(i).get(0))){//szukanie po kategorii
                System.out.println(produkty.get(i));
            }
            if(searchVar.equals(produkty.get(i).get(4))){//szukanie po nazwie
                System.out.println(produkty.get(i));
            }
        }
        //przez nazwe -> lista.get(i).get(4) da nazwe(model niech bedzie nazwa)
    }

    public void searchList2(String searchVar){//przeszukiwanie z ArrayList, przeszukuje wszystkie informajce
        for(int i = 0; i < produkty.size(); i++){
            if(produkty.get(i).contains(searchVar)){
                System.out.println(produkty.get(i));
            }
        }
    }

    public int searchListForIndex(String searchVar){
        for(int i = 0; i < produkty.size(); i++){
            if(produkty.get(i).contains(searchVar)){//szukanie po id
                return i;
            }
        }
        return -1;

    }

    public void sortyByCategory(){//zmienic nazwe na sort
        //sortuje list kategoriami po najnizszych klasach -> odczytuje cala liste i tworzy nowa. Pierw algorytm sprawdza czy categoria jest najmniejsza a potem przepisuje na nastepna liste.
        // w momencie w ktorym id jest takie samo sprawdza i wybiera najnizsze id
        //bubblesort
        int n = produkty.size() - 1;
        boolean swapped;
        for(int i = 0; i < n; i++){
            swapped = false;
            for(int j = 0; j < n - i; j++){ // normalnie powinno byc po prostu n(size - 1), ale dodatkowo -i powoduje ze jest to zoptymalizowane -> po każdej iteracji wewnetrznej pętli, jeden z elementów powinien byc na odp miejscu dlateog - i pomija sprawdzanie tego elementu
                if(Integer.valueOf(produkty.get(j).get(0)) > Integer.valueOf(produkty.get(j+1).get(0))){
                ArrayList<String> temp = produkty.get(j);
                produkty.set(j, produkty.get(j+1));
                produkty.set(j+1, temp);
                swapped = true;
                }
            }
            if(!swapped){
                break;
            }
        }
    }
    
    public void menu(){
        try(Scanner scanner = new Scanner(System.in)){
            while(true){
                System.out.print("1: Show All \n2: Search \n3: Edit \n4: Add \n5: Delete \n0: Exit \nChoose: ");
                int scan = scanner.nextInt();
                String scan1;
                scanner.nextLine();// do scan jest przypisana wartośc z nextInt ale nextInt generuje też \n po wartości, trzeba uzyc scanner.nextLine() zeby pozbyć sie \n inaczej pozniejsze scannery beda to zczytywac
                if(scan == 0){
                    break;
                }
                else if(scan == 1){
                    System.out.print("\033[H\033[2J");
                    show();

                    System.out.print("Do u want to go back? Y/N\n");
                    scan1 = scanner.nextLine();
                    if(scan1.equalsIgnoreCase("N")){
                        break;
                    }

                }
                else if(scan == 2){
                    System.out.print("\033[H\033[2J");
                    System.out.print("Type your phrase: ");
                    scan1 = scanner.nextLine();
                    searchList(scan1);

                    System.out.print("Do u want to go back? Y/N\n");
                    scan1 = scanner.nextLine();
                    if(scan1.equalsIgnoreCase("N")){
                        break;
                    }
                    
                }
                //else if dodawanie -> najpierw powie nam jaka chce kategorie, potem tutaj w ifie bedzie String scanner, w zaleznosci od kategorii, scaner uruchomi sie tyle razy w petli ile bedzie okreslone w zmiennej
                //trzeba dodatkowa funkcje na dodawanie, bo ta ktora zrobilem bedzie do czytania z csv
                System.out.print("\033[H\033[2J");  
            }
        }
    }

    public void showList(ArrayList<ArrayList<String>> produktyx){
        for(int i = 0; i < produktyx.size(); i++){
            if(Integer.parseInt(produktyx.get(i).get(0)) == 0){
                System.out.println("SprzetMuzyczny   " + produktyx.get(i));
            }
            else if(Integer.parseInt(produktyx.get(i).get(0)) == 1){
                System.out.println("Instrumenty      " + produktyx.get(i));
            }
            else if(Integer.parseInt(produktyx.get(i).get(0)) == 2){
                System.out.println("Instrumenty Dety " + produktyx.get(i));
            }
        }
        //Sortowanie trzeba dodac po kategorii
    }

    public void show(){
        for(ArrayList<String> elem : getList()){
            StringBuilder line = new StringBuilder();
            for(int i = 1; i < elem.size(); i++){
                line.append(elem.get(i)).append(" ");
            }
            System.out.print("[" + line.toString().trim() + "]\n");
        }
    }

    public static void main(String[] args)throws Exception{
        CFunkcje cf = new CFunkcje();
        SprzetMuzyczny sm = new SprzetMuzyczny("Producent", "model", "nrSeryjny", new CData(2000));
        Instrumenty instrument = new Instrumenty("Producent", "model", "nrSeryjny", new CData(2000), true, "Drewno");
        InstrumentyDete instrumentDety = new InstrumentyDete("Producent", "model", "nrSeryjny", new CData(2000), true, "Drewno", "kurwa stroik zajebisty ze fest");
        Produkty x3 = new Produkty(10 ,12, 13, instrument);
        Produkty x1 = new Produkty(15 ,12, 13, sm);
        Produkty x2 = new Produkty(20 ,12, 13, instrumentDety);
        Produkty x4 = new Produkty(21 ,12, 13, instrumentDety);
        //System.out.println(x.getInstrument());
        System.out.println(x1.getProdukt());
        System.out.println(x2.getProdukt());
        System.out.println(x3.getProdukt());
        //System.out.println(x2.getInstrumentDety());
        cf.addToList(x4);
        cf.addToList(x2);
        cf.addToList(x2);
        cf.addToList(x2);
        cf.addToList(x2);
        cf.addToList(x1);
        cf.addToList(x1);
        cf.addToList(x1);
        cf.addToList(x3);
        cf.addToList(x3);
        cf.addToList(x3);
        cf.showList(cf.getList());

        cf.show();

        cf.searchListForIndex("21");
        //cf.removeFromList("21");
        ArrayList<String> lista = new ArrayList<>();
        lista.add(cf.getList().get(cf.searchListForIndex("21")).get(0));
        lista.add(cf.getList().get(cf.searchListForIndex("21")).get(1));
        for(int i = 1; i < cf.getList().get(cf.searchListForIndex("21")).size(); i++){
            lista.add("dodano" + i);
        }
        cf.modifyList("21", lista);
        cf.sortyByCategory();

        cf.show();

        cf.searchList2("10");
        //cf.menu();
        //System.out.println(cf.getList());
        //cf.searchList("producent");
    }
    /*public static void showAll(Produkty produkt){
        System.out.println("ID:    " + "Cena:  " + "Ilosc:  " + "Producent:  " + "Model:  " + "NR Seryjny:  " + "Rok Produkcji:\n" + 
                           produkt.getID() + " " + produkt.getCena() + " " + produkt.getIlosc() + " " + produkt.getProdukt().getProducent() + " " + produkt.getProdukt().getModel() + " " + produkt.getProdukt().getNrSeryjny() + " " + produkt.getProdukt().getRokProd());
    }*/
}