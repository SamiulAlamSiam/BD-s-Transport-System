package HelperLayer;

public class ValidationHelper {
	
	public static boolean IsValidInt(String value)
	{
		try
		{
			Integer.parseInt(value);
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	public static boolean IsValidFloat(String value)
	{
		try
		{
			Float.parseFloat(value);
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	

}
