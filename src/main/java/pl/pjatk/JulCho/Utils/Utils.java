package pl.pjatk.JulCho.Utils;

public class Utils {

    public static Integer TryParseInt(String value)
    {
        try
        {
            return Integer.parseInt(value);
        }
        catch (NumberFormatException e)
        {
            return null;
        }
    }
}
