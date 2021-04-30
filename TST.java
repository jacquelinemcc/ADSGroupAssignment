import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class TST <Value>
{

	private int size;
	private Node<Value> root;
	
	private static class Node<Value>
	{
		private char character;
		private Node<Value> left, middle, right;
		private Value value;
	}
	
	
	public TST()
	{
		
	}
	
	public int size()
	{
		return size;
	}
	
	public boolean contains (String stopInformation)
	{
		if(stopInformation == null) 
		{
			throw new IllegalArgumentException("This is null");
		}
		return get(stopInformation) != null;
	}
	
	
	public Value get(String stopInformation) 
	{
		if(stopInformation == null)
		{
			throw new IllegalArgumentException("There is no stop name entered.");
		}
		if(stopInformation.length() == 0)
		{
			throw new IllegalArgumentException("The stop name must not have 0 letters");
		}
		Node<Value> node = get(root, stopInformation, 0);
		if(node == null)
		{
			return null;
		}
		return node.value;		
	}
	
	private Node<Value> get(Node<Value> node, String stopInformation, int index)
	{
		if(node == null)
		{
			return null;
		}
		if(stopInformation.length() == 0)
		{
			throw new IllegalArgumentException("The stop name must not have 0 letters");
		}
		char character = stopInformation.charAt(index);
		if(character < node.character)
		{
			return get(node.left, stopInformation, index);
		}
		else if(character > node.character)
		{
			return get(node.right, stopInformation, index);
		}
		else if(index < stopInformation.length()-1)
		{
			return get(node.middle, stopInformation, index+1);
		}
		else return node;
	}
	
	
	public void put(String stopInformation, Value value)
	{
		if(stopInformation == null)
		{
			throw new IllegalArgumentException("this is null");
		}
		if(!contains(stopInformation))
		{
			size++;
		}
		else if(value == null)
		{
			size--;
		}
		root = put(root, stopInformation, value, 0);
	}
	
	private Node<Value> put(Node<Value> node, String stopInformation, Value value, int index)
	{
		char character = stopInformation.charAt(index);
		if(node == null)
		{
			node = new Node<Value>();
			node.character = character;
		}
		
		if(character < node.character)
		{
			node.left = put(node.left, stopInformation, value, index);
		}
		else if(character > node.character)
		{
			node.right = put(node.right, stopInformation, value, index);
		}
		else if(index < stopInformation.length()-1)
		{
			node.middle = put(node.middle, stopInformation, value, index+1);
		}
		else node.value = value;
		return node;
	}
	
	public Iterable<String> keysWithGivenPrefix(String prefix) 
	{
		if (prefix == null) 
		{
			throw new IllegalArgumentException("There has been no prefix entered");
		}
		ArrayList<String> listOfNames = new ArrayList<String>();
		Node<Value> node = get(root, prefix, 0);
		if (node == null) 
		{
			return listOfNames;
		}
		if (node.value != null)
		{
			listOfNames.add(prefix);
		}
		collect(node.middle, new StringBuilder(prefix), listOfNames);
		return listOfNames;
	}

	private void collect(Node<Value> node, StringBuilder prefix, ArrayList<String> listOfNames) {
		if (node == null) 
		{
			return;
		}
		collect(node.left,  prefix, listOfNames);
		if (node.value != null) 
		{
			listOfNames.add(prefix.toString() + node.character);
		}
		collect(node.middle, prefix.append(node.character), listOfNames);
		prefix.deleteCharAt(prefix.length() - 1);
		collect(node.right, prefix, listOfNames);
	}
	
	public static String formatAddress(String address)
	{
		
		String[] parts = address.split(",");
		
		String stopName = parts[2].trim();
		String [] splitAddress = stopName.split(" ");
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
		else return stopName;
	}
	
	
	

	
	public static void main(String[] args)
	{
		TST<String> tree = new TST<String>();

		try {
			File filePath = new File("stops.txt");
			String line = "";
			BufferedReader br = new BufferedReader(new FileReader(filePath));

			while((line = br.readLine()) != null)
			{
				String addressKey = formatAddress(line); 
				tree.put(addressKey, line);			
			}

		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		System.out.println("Enter 1 if you want to search by first name or enter 2 if you want to search by full address");
		Scanner firstQuestion = new Scanner(System.in);
		if(firstQuestion.hasNext("1"))
		{
			System.out.println("Please enter the first name or the first few letters of the stop you're looking for (using all capital letters): ");
			Scanner userInput1 = new Scanner(System.in);

			String stopName1 = userInput1.nextLine();

			for(String element: tree.keysWithGivenPrefix(stopName1))
			{
					System.out.println("The information for this stop is :(Stop ID, Stop Code, Stop Name, Stop Address, Stop Latitude, Stop Longitude, Zone ID, Stop URL, Location Type, Parent Station): " + tree.get(element));				
			}

		}

		else if(firstQuestion.hasNext("2"))
		{
			System.out.println("Please enter the full address of the stop you're looking for:");
			Scanner userInput2 = new Scanner(System.in);

			String stopName2 = userInput2.nextLine();

			if(tree.contains(stopName2)) 
			{
				System.out.println("The information for this stop is :(Stop ID, Stop Code, Stop Name, Stop Address, Stop Latitude, Stop Longitude, Zone ID, Stop URL, Location Type, Parent Station): " + tree.get(stopName2));
			}
			else
			{
				System.out.println("Please enter the correct name of a bus stop");
			}

		}
		else
		{
			System.out.println("Please enter either 1 or 2");
		}
	}
}
