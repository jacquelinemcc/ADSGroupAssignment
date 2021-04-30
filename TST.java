import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class TST <Value>
{
	public static String file = "stops.txt";

	private int size;
	private Node<Value> root;
	
	private static class Node<Value>
	{
		private char character;
		private Node<Value> left, mid, right;
		private Value value;
	}
	
	
	public TST()
	{
		
	}
	
	public int size()
	{
		return size;
	}
	
	public boolean contains (String key)
	{
		if(key == null) 
		{
			throw new IllegalArgumentException("this is null");
		}
		return get(key) != null;
	}
	
	
	public Value get(String key) 
	{
		if(key == null)
		{
			throw new IllegalArgumentException("this is null");
		}
		if(key.length() == 0)
		{
			throw new IllegalArgumentException("key must be greater than 1");
		}
		Node<Value> value = get(root, key, 0);
		if(value == null)
		{
			return null;
		}
		return value.value;		
	}
	
	private Node<Value> get(Node<Value> value, String key, int index)
	{
		if(value == null)
		{
			return null;
		}
		if(key.length() == 0)
		{
			throw new IllegalArgumentException("key must be greater than 1");
		}
		char character = key.charAt(index);
		if(character < value.character)
		{
			return get(value.left, key, index);
		}
		else if(character > value.character)
		{
			return get(value.right, key, index);
		}
		else if(index < key.length()-1)
		{
			return get(value.mid, key, index+1);
		}
		else return value;
	}
	
	
	public void put(String key, Value value)
	{
		if(key == null)
		{
			throw new IllegalArgumentException("this is null");
		}
		if(!contains(key))
		{
			size++;
		}
		else if(value == null)
		{
			size--;
		}
		root = put(root, key, value, 0);
	}
	
	private Node<Value> put(Node<Value> x, String key, Value value, int index)
	{
		char character = key.charAt(index);
		if(x == null)
		{
			x = new Node<Value>();
			x.character = character;
		}
		
		if(character < x.character)
		{
			x.left = put(x.left, key, value, index);
		}
		else if(character > x.character)
		{
			x.right = put(x.right, key, value, index);
		}
		else if(index < key.length()-1)
		{
			x.mid = put(x.mid, key, value, index+1);
		}
		else x.value = value;
		return x;
	}
	
	public Iterable<String> keysWithGivenPrefix(String prefix) {
		if (prefix == null) {
			throw new IllegalArgumentException("calls keysWithGivenPrefix() with a null argument");
		}
		Queue<String> queue = new LinkedList<String>();
		Node<Value> x = get(root, prefix, 0);
		if (x == null) return queue;
		if (x.value != null) queue.add(prefix);
		collect(x.mid, new StringBuilder(prefix), queue);
		return queue;
	}

	private void collect(Node<Value> x, StringBuilder prefix, Queue<String> queue) {
		if (x == null) return;
		collect(x.left,  prefix, queue);
		if (x.value != null) queue.add(prefix.toString() + x.character);
		collect(x.mid,   prefix.append(x.character), queue);
		prefix.deleteCharAt(prefix.length() - 1);
		collect(x.right, prefix, queue);
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
			System.out.println("Please enter the first name of the stop you're looking for (using all capital letters): ");
			Scanner userInput1 = new Scanner(System.in);

			String stopName1 = userInput1.nextLine();
			String output = "";
			
			for(String element: tree.keysWithGivenPrefix(stopName1))
			{
				System.out.println("The information for this stop is :(Stop ID, Stop Code, Stop Name, Stop Address, Stop Latitude, Stop Longitude, Zone ID, Stop URL, Location Type, Parent Station): " + element);
			}

		}

		else if(firstQuestion.hasNext("2"))
		{
			System.out.println("Please enter the full address of the stop you're looking for:");
			Scanner userInput2 = new Scanner(System.in);

			String stopName2 = userInput2.nextLine();

			if(tree.contains(stopName2)) {
				System.out.println("Heres the information on that stop: " + tree.get(stopName2));
			}
			else
			{
				System.out.println("Please enter a correct name");
			}

		}
	}
}
