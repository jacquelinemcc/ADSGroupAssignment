import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Question2 {

	public static void main(String[] args) {
		String[] stopNames = fileReaderFunction("src/stops.txt");
		//System.out.println(stopNames);
		System.out.println("What stop are you looking for?");
		Scanner input = new Scanner(System.in);
		String stopName = input.toString();
		System.out.println(formatAddress("EB W PENDER ST FS BURRARD ST,W PENDER ST @ BURRARD ST"));
	}

	public static String [] fileReaderFunction(String file)
    {
    	
    	try {  		
    		Scanner arrayScanner = new Scanner (new FileInputStream(file));
    		String [] array = null;
    		while (arrayScanner.hasNextDouble()) 
    		{

    			String [] newArray = new String[(array == null)?1:array.length+1];
    			if (array!=null)
    			{
    				System.arraycopy( array, 0, newArray, 0, array.length);
    			}
    			newArray[newArray.length-1] = arrayScanner.toString();
				array = newArray;
    		}
			arrayScanner.close();
    		return array;
    		
    	} catch (FileNotFoundException e) {
    		System.out.println("File not found");
    	}
    	
    	return null;

    }

	public static String formatAddress(String address)
	{
		String [] splitAddress = address.split(" ");
		String startOfAddress = splitAddress[0].trim();
		String endOfAddress = "";
		
		if(startOfAddress.equals("FLAGSTOP") || startOfAddress.equals("NB") || startOfAddress.equals("WB") || startOfAddress.equals("SB") || startOfAddress.equals("EB"))
		{
			if(splitAddress[1].equals("WB") || splitAddress[1].equals("SB") || splitAddress[1].equals("EB") || splitAddress[1].equals("NB"))
			{
				startOfAddress += " "+splitAddress[1];
				for(int i = 2; i < splitAddress.length; i++)
				{
					endOfAddress += splitAddress[i].trim()+" ";
				}
				return endOfAddress+startOfAddress;
			}
			else 
			{
				for(int i = 1; i < splitAddress.length; i++)
				{
					endOfAddress += splitAddress[i]+" ";
				}
			return endOfAddress+startOfAddress;
			}

		}
		else return address;
	}
	
}
