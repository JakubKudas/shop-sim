public class CData1demo{
    public static void main(String[] args)throws Exception{
        try{
            CData1 cdata = new CData1(1,1,2000);
            CData1 cdata1 = new CData1(2000);
            CData1 cdata2 = new CData1("2000");
            CData1 cdata3 = new CData1("28-2-1999");
            CData1 cdata4 = new CData1(30,4,1999);

            System.out.println(cdata.toString());

            cdata.doPrzeszlosci(1);
            System.out.println(cdata.toString());

            cdata.doPrzyszlosci(1);
            System.out.println(cdata.toString());

            cdata.Jutro();
            System.out.println(cdata.toString());

            cdata.Wczoraj();
            System.out.println(cdata.toString());

            cdata.zaTydzien();
            System.out.println(cdata.toString());

            cdata.tydzienTemu();
            System.out.println(cdata.toString());


            System.out.println(cdata.porownajDaty(new CData1(1,1,2000)));
            
            System.out.println(cdata.porownajDaty(new CData1(2,1,2000)));

            System.out.println(cdata.roznicaDat(new CData1(1,1,2000)));

            System.out.println(cdata.roznicaDat(new CData1(1,2,2000)));

            System.out.println(cdata.dzienTygodnia(new CData1(24,05,2023)));

            System.out.println(cdata.porownajDaty(cdata, new CData1(1,1,2000)));
            
            System.out.println(cdata.porownajDaty(cdata, new CData1(2,1,2000)));

            System.out.println(cdata.roznicaDat(cdata, new CData1(1,1,2000)));
            
            System.out.println(cdata.roznicaDat(cdata, new CData1(1,2,2000)));

            int[] dataWielkanocyVar = cdata.dataWielkanocy(2023);
            System.out.print(dataWielkanocyVar[0] + "." + dataWielkanocyVar[1] + "." + dataWielkanocyVar[2]);
        }
        catch(Exception e){
            System.out.println(e);
        }

    }
}