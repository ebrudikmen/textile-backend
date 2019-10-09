package app;

import java.text.SimpleDateFormat;
import java.util.Locale;

class Common {

    // database connection
    public static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DATABASE_URL = "jdbc:mysql://127.0.0.1:3306/textile";
    public static final String DATABASE_USERNAME = "root";
    public static final String DATABASE_PASSWORD = "Caretta";

    // date formatter
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final Locale DATE_LOCALE = Locale.ENGLISH;
    public static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(Common.DATE_FORMAT, Common.DATE_LOCALE);
}
