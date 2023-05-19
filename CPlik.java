import java.io.*;

public class CPlik{

    public void plikIn(String fileName)throws IOException{
        BufferedReader br = null;
        try{

            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileName));
            br = new BufferedReader(new InputStreamReader(bis, "UTF-16"));

            String line;
            while((line = br.readLine()) != null){
                System.out.println(line);
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

    public static void main(String[] args)throws IOException{
        CPlik plik = new CPlik();
        plik.plikIn("b.txt");

        String[] pl = new String[20];
        for(String elem : pl)
            System.out.println(elem);
    }
}