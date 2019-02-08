package pl.goinweb.dev.dawid.raportapp;

public class DateFormatter {

    static public String choosen_datetime_start, choosen_datetime_stop;
    String t_start, t_stop, d_start, d_stop;
    String dt_start, dt_stop;

    void formatDate(String time_start, String time_stop, String date_start, String date_stop)
    {
        t_start = time_start;
        t_stop = time_stop;
        d_start = date_start;
        d_stop = date_stop;
        dt_start = d_start + "T" + t_start;
        dt_stop = d_stop + "T" + t_stop;

    }
}
