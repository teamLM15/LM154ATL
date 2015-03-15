/*
 * ObjectIO Created on 2013/04/22
 */

package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectIO
{
	public final static String FOOTER = "footer";
	public final static String ACCOUNT = "account";
	public final static String SETTING = "setting";

	public static void ObjectOutput(Object obj, String filename) throws IOException
	{
		FileOutputStream outFile = new FileOutputStream(filename);
		ObjectOutputStream outObject = new ObjectOutputStream(outFile);
		outObject.writeObject(obj);

		outObject.close();
		outFile.close();
	}

	public static Object ObjectInput(String filename) throws Exception
	{
		FileInputStream inFile = new FileInputStream(filename);
		ObjectInputStream inObject = new ObjectInputStream(inFile);
		Object obj = inObject.readObject();

		inObject.close();
		inFile.close();

		return obj;
	}
}
