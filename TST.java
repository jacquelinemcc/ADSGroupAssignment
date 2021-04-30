import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class TST <Value>
{
	public static String file = "src/stops.txt";
	TST<String> tree = new TST<String>();
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
	
	public static TST<Value> treeFromFile()
	{
		TST<String> tree = new TST<String>();
		BufferedReader br = null;
		br = new BufferedReader(new FileReader(file));
		String line = null;
		line = br.readLine();
		
		while((line = br.readLine()) != null)
		{
			String stopDetails = line;
			String[] parts = line.split(",");
			String stopName = parts[3].trim();
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
			else return address;
			
		}
	}
	
	public static void main(String[] args)
	{
		TST<String> tree = treeFromFile();

		
		for (int i = 0; i < file.length(); i++)
		{
			tree.put(file, i);
		}
		
		
		/*
		System.out.println("Please enter the first name of the stop you're looking for:");
		Scanner userInput = new Scanner(System.in);

		String stopName = userInput.nextLine();

		
		
		String input2 = "Emma";
		*/
		
		System.out.println(tree);
	}
}