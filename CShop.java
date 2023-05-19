import java.util.ArrayList;
import java.io.*;

public class CShop{
    ArrayList<Produkty> towar = new ArrayList<>(); //lista produktow
    private int edgeLength;

    public ArrayList<Produkty> getList(){
        return this.towar;
    }

    public int getEdgeLength(){
        return this.edgeLength;
    }

    public void addToList(Produkty produkt){
        towar.add(produkt);
    }

    public void removeFromList(int searchVar){
        for(int i = 0; i < towar.size(); i++){
            if(searchVar == towar.get(i).getID())//szukanie po id
                towar.remove(i);
        }
    }

    public void modifyList(int searchVar, Produkty produkt)throws Exception{
        for(int i = 0; i < towar.size(); i++){
            if(searchVar == towar.get(i).getID()){
                try{
                    produkt = new Produkty(towar.get(i).getID(), produkt.getNazwa(), produkt.getCena(), produkt.getIlosc(), produkt.getProdukt());
                    towar.set(i, produkt);
                }
                catch(Exception e){
                    System.out.println(e);
                }
            }
        }
    }

    public ArrayList<Produkty> searchList(String searchVar){
        ArrayList<Produkty> searchResults = new ArrayList<>();
        for(int i = 0; i < towar.size(); i++){
            if(searchVar.matches("\\d+") && searchVar.length() > 1){
                if(Integer.parseInt(searchVar) == (towar.get(i).getID()))
                    searchResults.add(towar.get(i));
            }
            else if(searchVar.equals(towar.get(i).getNazwa()))
                searchResults.add(towar.get(i));
            else if(searchVar.equals(Integer.toString(towar.get(i).getCategory())))
                searchResults.add(towar.get(i));
        }

        return searchResults;
    }

    public void bubblesort(){//zmienic nazwe na sort
        //sortuje list kategoriami po najnizszych klasach -> odczytuje cala liste i tworzy nowa. Pierw algorytm sprawdza czy categoria jest najmniejsza a potem przepisuje na nastepna liste.
        // w momencie w ktorym id jest takie samo sprawdza i wybiera najnizsze id
        //bubblesort
        int n = towar.size() - 1;
        boolean swapped;
        for(int i = 0; i < n; i++){
            swapped = false;
            for(int j = 0; j < n - i; j++){ // normalnie powinno byc po prostu n(size - 1), ale dodatkowo -i powoduje ze jest to zoptymalizowane -> po każdej iteracji wewnetrznej pętli, jeden z elementów powinien byc na odp miejscu dlateog - i pomija sprawdzanie tego elementu
                if(towar.get(j).getCategory() > towar.get(j + 1).getCategory()){
                    Produkty temp = towar.get(j);
                    towar.set(j, towar.get(j+1));
                    towar.set(j+1, temp);
                    swapped = true;
                }
            }
            if(!swapped){
                break;
            }
        }
    } //to do, sortowanie wg ceny, malejąco, rosnąco i wg nazwy - alfabetycznie.

    public void fileReader(String fileName)throws Exception{
        BufferedReader br = null;
        try{
            //FileInputStream is used to read the file as a stream of bytes.
            //BufferedInputStream adds buffering functionality, which improves the efficiency of reading bytes from the underlying FileInputStream by reducing the number of direct system calls.
            //InputStreamReader converts the stream of bytes from the BufferedInputStream into a character stream.
            //BufferedReader provides buffering functionality and allows us to read the file line by line, rather than reading individual characters or bytes.
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileName));
            br = new BufferedReader(new InputStreamReader(bis, "UTF-16"));

            String line;
            while((line = br.readLine()) != null){
                addToListFromFile(line);
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Plik nie został znaleziony"); 
        }
        finally{
            if(br != null){
                try{
                    br.close();
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    private void addToListFromFile(String line)throws Exception{
        Produkty produkt;
        ArrayList<String> cutLine = new ArrayList<>();
        String[] cutLineArray = new String[20];
        cutLineArray = line.split(","); //category int, 3int, 4String, CDATA, String -> bool(if), 2String
        for(String elem : cutLineArray){
            if(elem != null)
                cutLine.add(elem);
        }
        int category = Integer.parseInt(cutLine.get(0));
        switch(category){
            case 0:
                if(cutLine.size() == 9){
                    produkt = new Produkty(Integer.parseInt(cutLine.get(1)), cutLine.get(2), Integer.parseInt(cutLine.get(3)), Integer.parseInt(cutLine.get(4)), 
                            new SprzetMuzyczny(cutLine.get(5), cutLine.get(6), cutLine.get(7), new CData1(cutLine.get(8))));
                }
                else{
                    throw new Exception("Wrong number of values in the file!");
                }
                break;
            case 1:
                if(cutLine.size() == 11){
                    produkt = new Produkty(Integer.parseInt(cutLine.get(1)), cutLine.get(2), Integer.parseInt(cutLine.get(3)), Integer.parseInt(cutLine.get(4)), 
                            new Instrumenty(cutLine.get(5), cutLine.get(6), cutLine.get(7), new CData1(cutLine.get(8)), (cutLine.get(9).equals("Tak")), cutLine.get(10)));
                }
                else{
                    throw new Exception("Wrong number of values in the file!");
                }
                break;
            case 2:
                if(cutLine.size() == 12){
                    produkt = new Produkty(Integer.parseInt(cutLine.get(1)), cutLine.get(2), Integer.parseInt(cutLine.get(3)), Integer.parseInt(cutLine.get(4)), 
                            new InstrumentyDete(cutLine.get(5), cutLine.get(6), cutLine.get(7), new CData1(cutLine.get(8)), (cutLine.get(9).equals("Tak")), cutLine.get(10), cutLine.get(11)));
                }
                else{
                    throw new Exception("Wrong number of values in the file!");
                }
                break;
            default:
                produkt = new Produkty();
        }
        addToList(produkt);
    }

    public void showList(){
        for(Produkty produkt : towar){
            System.out.println(produkt.toString());
        }
    }

    public void showList(ArrayList<Produkty> searchResults){
        for(Produkty produkt : searchResults){
            System.out.println(produkt.toString());
        }
    }

    public void edgeOfTheTable(ArrayList<Produkty> arraylist){
        int max = arraylist.get(0).toString().length();
        for(int i = 0; i < arraylist.size(); i++){
            if(arraylist.get(i).toString().length() > max)
                max = arraylist.get(i).toString().length();
        }
        System.out.println("-".repeat(max));
        this.edgeLength = max;
    }

    public void topContents(int edgeLength){
        String fullTop = String.format("| %-5s | %-10s | %-5s%-3s | %-3s | %-3s | %-9s | %-5s | %-5s | %-5s | %-4s | %-8s | %-9s |",
                                 "ID", "Nazwa", " Cena", "", "Ile", "IDK", "Producent", "Model", "NrSer", "RokPr", "Elek", "Material", "Stroik");


        edgeLength = (edgeLength > fullTop.length()) ? fullTop.length() : edgeLength;
        String cutTop = fullTop.substring(0, edgeLength);
        System.out.println(cutTop);
    }

    public void visualTable(ArrayList<Produkty> arraylist){
        edgeOfTheTable(arraylist);
        topContents(getEdgeLength());
        edgeOfTheTable(arraylist);
        for(Produkty produkt : arraylist){
            System.out.println(produkt.toString());
            edgeOfTheTable(arraylist);
        }
    }
}