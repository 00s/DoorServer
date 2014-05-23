package jamillo.oreia.zoio;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
  * Retorna a hora em HHmmss no tempo corrente do sistema
  * 
  */
public class CurrentTime {

    public static String getIt() {
    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    	String now = sdf.format(Calendar.getInstance().getTime());
    	System.out.println(now);
    	return now;
    }
    
    public static int getHour(){
    	return Integer.parseInt(CurrentTime.getIt().split(":")[0]);
    }

}