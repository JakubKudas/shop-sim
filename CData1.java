public class CData1{
    private int[] dateNotProcessed = {0, 0, 0}, dateProcessed = {0, 0, 0}, initialDate = {0, 0, 0};
    private int days, initialDays;
    private int[] monthStarters = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};
    private int[] monthLeapStarters = {0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335};
    private static final String[] DniTygodnia = {"Niedziela", "Poniedzialek", "Wtorek", "Sroda", "Czwartek", "Piatek", "Sobota"};

    public int getDay(){
        return this.dateProcessed[0];
    }

    public int getMonth(){
        return this.dateProcessed[1];
    }

    public int getYear(){
        return this.dateProcessed[2];
    }

    public int getDays(){
        return this.days;
    }

    public int getInitialDays() {
        return this.initialDays;
    }

    public int getInitialDay(){
        return this.initialDate[0];
    }

    public int getInitialMonth(){
        return this.initialDate[1];
    }

    public int getInitialYear(){
        return this.initialDate[2];
    }

    private void setDays(){
        this.days = (dateNotProcessed[0] + monthToDays(dateNotProcessed[1] - 1) + yearToDays(dateNotProcessed[2]) - 1);
    }

    private void setDay(){
        this.dateProcessed[0] = (getDays() - yearToDays(getYear()) - monthToDays(getMonth()));
    }


    private void setMonth(){
        int day = days - yearToDays(getYear());
        int[] preparedArray = new int[12];

        if(leapYear(getYear())){
            for(int i = 0; i <= 11; i++)
                preparedArray[i] = day - monthLeapStarters[i];
            this.dateProcessed[1] = indexForMonth(preparedArray);
        }
        else{
            for(int i = 0; i <= 11; i++)
                preparedArray[i] = day - monthStarters[i];
            this.dateProcessed[1] = indexForMonth(preparedArray);
        }
    }

    private void setYear(){
        int year;
        int daysRemaining = days;

        for(year = 1900; daysRemaining > 364; year++){
            if(leapYear(year) && daysRemaining == 365)
                this.dateProcessed[2] = year;
            int daysInYear = leapYear(year) ? 366 : 365;
            daysRemaining -= daysInYear;
        }
        this.dateProcessed[2] = year;
    }

    private boolean leapYear(int year){
        if(year % 4 == 0 && year % 100 != 0)
            return true;
        else if(year % 100 == 0 && year % 400 == 0)
            return true;
        else
            return  false;
    }

    private int indexForMonth(int[] preparedArray){
        int index = 0, max = 0;
        for(int i = 0; i <= 11; i++)
            max = Math.max(max, preparedArray[i]);
        
        int min = max;
        for(int i = 0; i <= 11; i++){
            if(preparedArray[i] >= 0)
                min = Math.min(min, preparedArray[i]);
        }

        for(int i = 0; i <= 11; i++){
            if(min == preparedArray[i])
                index = i;
        }
        return index;
    }

    private int monthToDays(int month){
        return (getYear() != 0 ? leapYear(getYear()) : leapYear(dateNotProcessed[2]))
                ? monthLeapStarters[month]
                : monthStarters[month];
    }

    private int yearToDays(int year) {
        int leapYearNum = (year - 1900) / 4 - (year - 1900) / 100 + (year - 1600) / 400;
        int nonLeapYearNum = year - 1900 - leapYearNum;
        int day = leapYearNum * 366 + nonLeapYearNum * 365 - (leapYear(year) ? 1 : 0);
        return day;
    }


    private void setDateToProcess(int day, int month, int year){
        this.dateNotProcessed[0] = day;
        this.dateNotProcessed[1] = month;
        this.dateNotProcessed[2] = year;
    }

    private void setProcessedDate(){
        setYear();
        setMonth();
        setDay();
    }

    private void setInitialDate(){
        this.initialDate[0] = dateProcessed[0];
        this.initialDate[1] = dateProcessed[1];
        this.initialDate[2] = dateProcessed[2];
        this.initialDays = days;
    }

    private String inMonth(int month){
        int[] M31 = {1, 3, 5, 7, 8, 10, 12};
        int[] M30 = {4, 6, 9, 11};
        String m = "None";
        for(int i = 0; i < M31.length; i++){
            if(M31[i] == month){
                m = "M31";
            }
        }
        for(int i = 0; i < M30.length; i++){
            if(M30[i] == month){
                m = "M30";
            }
        }
        return m;
    }

    public CData1(){
        setDateToProcess(1, 1, 1900);
        setDays();
        setProcessedDate();
        setInitialDate();
    }

    public CData1(int year){
        setDateToProcess(1, 1, year);
        setDays();
        setProcessedDate();
        setInitialDate();
    }

    public CData1(int day, int month, int year)throws Exception{
        if(month < 1 || month > 12){
            throw new Exception("Wrong date input!");
        }
        else if(inMonth(month).equals("M31")){
            if(day < 1 || day > 31)
                throw new Exception("Wrong date input!");
        }
        else if(inMonth(month).equals("M30")){
            if(day < 1 || day > 30) 
                throw new Exception("Wrong date input!");
        }
        else if(inMonth(month).equals("None") && leapYear(year)){
            if(day < 1 || day > 29)
                throw new Exception("Wrong date input!");
        }
        else if(inMonth(month).equals("None") && !leapYear(year)){
            if(day < 1 || day > 28)
                throw new Exception("Wrong date input!");
        }
        
        setDateToProcess(day, month, year);
        setDays();
        setProcessedDate();
        setInitialDate();
    }

    public CData1(String stringDate)throws Exception{
        String[] cutStringDate = {"", "", ""};
        cutStringDate = stringDate.split("-");
        if(cutStringDate.length == 3){
            if(!cutStringDate[0].matches("\\d+") && !cutStringDate[1].matches("\\d+") && !cutStringDate[2].matches("\\d+")){
				throw new Exception("Wrong date input!");
			}
            int day = Integer.parseInt(cutStringDate[0]);
			int month = Integer.parseInt(cutStringDate[1]);
			int year = Integer.parseInt(cutStringDate[2]);
            if(month < 1 || month > 12){
                throw new Exception("Wrong date input!");
            }
            else if(inMonth(month).equals("M31")){
                if(day < 1 || day > 31)
                    throw new Exception("Wrong date input!");
            }
            else if(inMonth(month).equals("M30")){
                if(day < 1 || day > 30) 
                    throw new Exception("Wrong date input!");
            }
            else if(inMonth(month).equals("None") && leapYear(year)){
                if(day < 1 || day > 29)
                    throw new Exception("Wrong date input!");
            }
            else if(inMonth(month).equals("None") && !leapYear(year)){
                if(day < 1 || day > 28)
                    throw new Exception("Wrong date input!");
            }
            setDateToProcess(day, month, year);
            setDays();
            setProcessedDate();
            setInitialDate();
        }
        else if(stringDate.length() == 4){
            if(!stringDate.matches("\\d+")){
                throw new Exception("Wrong date input!");
            }
            int year = Integer.parseInt(stringDate);
            setDateToProcess(1, 1, year);
            setDays();
            setProcessedDate();
            setInitialDate();
        }
        else
            setDateToProcess(1, 1, 1900);
            setDays();
            setProcessedDate();
            setInitialDate();
    }

    public String toString(){
        String day, month, year;
        day = Integer.toString(getDay() + 1);
        month = Integer.toString(getMonth() + 1);
        year = Integer.toString(getYear());
        return day + "." + month + "." + year;
    }

    public String getInitialDate(){
        return getInitialDay() + "." + getInitialMonth() + "." + getInitialYear();
    }

    public void doPrzyszlosci(int przesuniecie){
        int daysMoved = getInitialDays() + przesuniecie;
        this.days = daysMoved;
        setProcessedDate();
	}

    public void doPrzeszlosci(int przesuniecie){
        int daysMoved = getInitialDays() - przesuniecie;
        this.days = daysMoved;
        setProcessedDate();
    }

    public void Jutro(){
        doPrzyszlosci(1);
    }

    public void Wczoraj(){
        doPrzeszlosci(1);
    }

    public void zaTydzien(){
        doPrzyszlosci(7);
    }

    public void tydzienTemu(){
        doPrzeszlosci(7);
    }

    public boolean porownajDaty(CData1 data){
        int data1 = getInitialDays();
        int data2 = data.getInitialDays();
        return (data1 == data2) ? true : false;
    }

    public int roznicaDat(CData1 data){
        int data1 = getInitialDays();
        int data2 = data.getInitialDays();
        return Math.abs(data1 - data2);
    }

    public static String dzienTygodnia(CData1 data){
        int day = data.getInitialDay() + 1;
        int month = data.getInitialMonth() + 1;
        int year = data.getInitialYear();
		int k, C, Y;
		double m = 0;
		if(day > 0 && day < 32 && month > 0 && month < 13){
			k = day;
			C = year/100;
			Y = year%100;
			if(month == 1 || month == 2)
				m = ((double) month) + 10.0;
			else
				m = ((double) month) - 2.0;
		}
		else
			return "Blad, podaj poprawna date";
		int W =(int) (k + Math.floor(2.6 * m - 0.2) - (2*C) + Y + Math.floor(Y/4) + Math.floor(C/4)) % 7;
        if(W < 0)
            W = W + 7;
		return DniTygodnia[W];
	}

    public static boolean porownajDaty(CData1 dataA, CData1 dataB){
        int data1 = dataA.getInitialDays();
        int data2 = dataB.getInitialDays();
        return (data1 == data2) ? true : false;
    }

    public static int roznicaDat(CData1 dataA, CData1 dataB){
        int data1 = dataA.getInitialDays();
        int data2 = dataB.getInitialDays();
        return Math.abs(data1 - data2);
    }

    public static int[] dataWielkanocy(int year){ //https://i.stack.imgur.com/WNhEk.jpg
		int y = year,
		p = y/100,
		q = y - 19*(y/19),
		r = (p-17)/25,
		s = p - (p/4) - ((p-r)/3) + 19*q + 15;
		s = s - (30*(s/30));
		s = s - ((s / 28) * (1 - ((s / 28) * (29 / (2 + 1)) * ((21 - q) / 11))));
		int t = y + (y/4) + s + 2 - p + (p/4);
		t = t - (7*(t/7));
		int u = s - t,
		m = 3 + ((u + 40)/44),
		d = u + 28 - 31*(m/4);
		
		int[] date = {d, m, year};
		
		return date;
	}
}