package pl.goinweb.dev.dawid.raportapp;

public class dateFormatter {

    static public String choosen_datetime_start, choosen_datetime_stop;
    String t_start, t_stop, d_start, d_stop;

    void dateFormatter(String time_start, String time_stop, String date_start, String date_stop)
    {
        time_start = t_start;
        time_stop = t_stop;
        date_start = d_start;
        date_stop = d_stop;
//        doIt();
    }

    void doIt(String time_start, String time_stop, String date_start, String date_stop)
    {
        t_start = time_start;
        t_stop = time_stop;
        d_start = date_start;
        d_stop = date_stop;
        String dt_start = d_start +"T"+ t_start;
        System.out.println(dt_start);
    }
}
