
public class Question2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String print = formatAddress("FLAGSTOP NB MILLER RD AT DEEP BAY");
		System.out.println(print);
	}
	
	public static String formatAddress(String address)
	{
		String [] splitAddress = address.split(" ");
		String endOfAddress = "";
		String startOfAddress = splitAddress[0];
		if(startOfAddress.equals("FLAGSTOP") || startOfAddress.equals("NB") || startOfAddress.equals("WB") || startOfAddress.equals("SB") || startOfAddress.equals("EB")) {
			if(splitAddress[1].equals("WB") || splitAddress[1].equals("SB") || splitAddress[1].equals("EB") || splitAddress[1].equals("NB")) {
				startOfAddress += " "+splitAddress[1];
				for(int i = 2; i < splitAddress.length; i++)
				{
					endOfAddress += splitAddress[i].trim()+" ";
				}
				return endOfAddress+startOfAddress;
			}
			else {
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
