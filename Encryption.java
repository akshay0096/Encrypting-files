//Program to Encrypt files of limited size:
import java.io.*;
import java.util.Scanner;
class Encrypt
{
	int store[];
	Encrypt(String p) throws IOException
	{
		File f=new File(p);
		int length=(int)f.length();
		FileInputStream fin=new FileInputStream(p);
		store=new int[length];
		for(int i=0;i<length;i++)
		{
			store[i]=fin.read();
		}
		for(int i=0;i<length;i++)
			store[i]+=10;
		FileOutputStream fout=new FileOutputStream(p);
		for(int i=0;i<length;i++)
		{
			fout.write(store[i]);
		}
		System.out.println("Your file Encrypted Successfully!");
	}
}
class Decrypt
{
	int store[];
	boolean pwdMatch(String pwd)
	{
		File match=new File("f:"+pwd+".txt");
		return match.exists();
	}
	void DecryptIt(String p) throws IOException
	{
		File f=new File(p);
		int length=(int)f.length();
		FileInputStream fin=new FileInputStream(p);
		store=new int[length];
		for(int i=0;i<length;i++)
		{
			store[i]=fin.read();
		}
		for(int i=0;i<length;i++)
		{
			store[i]-=10;
		}
		FileOutputStream fout=new FileOutputStream(p);
		for(int i=0;i<length;i++)
		{
			fout.write(store[i]);
		}
		System.out.println("Your file Decrypted Successfully");
	}
}
class Password
{
	Password(String filepath) throws IOException
	{
		System.out.println("Enter a password:");
		Scanner sc=new Scanner(System.in);
		String pass=sc.nextLine();
		char ch[]=pass.toCharArray();
		char fch[]=filepath.toCharArray();
		FileOutputStream fout=new FileOutputStream("f:password.txt",true);
		for(int i=0;i<filepath.length();i++)
			fout.write(fch[i]);
		for(int i=0;i<pass.length();i++)
		{
			fout.write(ch[i]);
		}
		FileOutputStream ffout=new FileOutputStream("f:"+pass+".txt");
	}
}
class Encryption
{
	public static void main(String[] args) throws IOException
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Operations you can perform:");
		System.out.println("1: Encrypt an existing file");
		System.out.println("2: Create a new file and Encrypt it");
		System.out.println("3: Decrypt a file");
		System.out.println("\nEnter a number from above options");
		Scanner scan=new Scanner(System.in);
		int choice=scan.nextInt();
		switch(choice)
		{
			
			case 1://Encrypt existing file
			{
				System.out.println("Specify the location of existing file for encryption:");
				String path=sc.nextLine();
				Password p=new Password(path);
				Encrypt e=new Encrypt(path);
			}	
			break;
			case 2://Encrypt by creating new file
			{
				System.out.println("Specify the location to create new file:");
				String path=sc.nextLine();
				FileOutputStream fout=new FileOutputStream(path);
				System.out.println("Enter the data:");
				String data=sc.nextLine();
				char ch[]=data.toCharArray();
				for(int i=0;i<data.length();i++)
					fout.write(ch[i]);
				Password p=new Password(path);
				Encrypt e=new Encrypt(path);
			}
			break;
			case 3://Decrypt file
			{
				System.out.println("Specify the location of file to decrypt it:");
				String path=sc.nextLine();
				System.out.println("Enter password to decrypt this file:");
				String code=sc.nextLine();
				Decrypt d=new Decrypt();
				if(d.pwdMatch(code))
				{
					d.DecryptIt(path);
					
				}	
				else
				{
					System.out.println("Incorrect Password");
					return;
				}
			}
		}
			
		
	}
}