package umn.dcsg.wieralocalserver.storageinterfaces;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

//import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: ajay
 * Date: 22/02/13
 * Time: 12:17 AM
 * To change this template use File | Settings | File Templates.
 */
public class LocalDiskInterface extends StorageInterface {
	//Special Chracter for directory key. (reserved)
	//If user wants to set a key that ends '/'
	private static String m_strSpecialKeyDirectory = "special_key_for_this_directory_should_not_removed";
	private String m_strLocalFolder;
	//For Testing Purpose
	private boolean m_bUseDirectIO = false;


	public LocalDiskInterface(String strLocalDirectory, boolean bDirectIO) {
		this.m_strLocalFolder = strLocalDirectory;
		m_bUseDirectIO = bDirectIO;

		//check directory have '/'
		int nLastIndex = m_strLocalFolder.lastIndexOf('/');

		// If key includes '/' then check first as it is a directory.
		if (nLastIndex < 0) {
			m_strLocalFolder += '/';
		}

		File localDir = new File(m_strLocalFolder);

		// if the directory does not exist, create it
		if (localDir.exists() == false) {
			try {
				localDir.mkdir();
			} catch (SecurityException se) {
				//handle it
				System.out.println("Failed to create local disk directory.");
				se.printStackTrace();
			}
		}
	}

	@Override
	public boolean put(String key, byte[] value) {
		if (value == null) {
			return false;
		}
		try {
			int nLastIndex = key.lastIndexOf('/');

			// If key includes '/' then check first as it is a directory.
			if (nLastIndex > 0) {
				String strKeyDirectory = m_strLocalFolder + '/' + key.substring(0, key.lastIndexOf('/'));
				strKeyDirectory = strKeyDirectory.replace("//", "/");
				File localDir = new File(strKeyDirectory);

				// if the directory does not exist, create it
				if (localDir.exists() == false) {
					try {
						System.out.printf("Try to create directory: %s\n", strKeyDirectory);
						localDir.mkdirs();
					} catch (SecurityException se) {
						//handle it
						System.out.println("Failed to create local disk directory for the key.");
						se.printStackTrace();
						return false;
					}
				}
			}

			//Check the key is for directory or not.
			if (key.endsWith("/") == true) {
				key += m_strSpecialKeyDirectory;
			}

			if (m_bUseDirectIO == false) {
				FileOutputStream fos = new FileOutputStream(m_strLocalFolder + key, false);
				fos.write(value);
				fos.close();
			} else {
/*				File directFile = new File(m_strLocalFolder + key);
				DirectIoByteChannelAligner direct = DirectIoByteChannelAligner.open(lib, directFile, bufferSize, false);

				DataOutputStream out = new DataOutputStream(new JNAOutputStream(m_strLocalFolder + key, true));
//				DirectRandomAccessFile fout = new DirectRandomAccessFile(new File(m_strLocalFolder + key), "rw", value.length);
				out.write(value);
				out.flush();
//				out.write(value, 0, value.length);
				out.close();*/
			}
		} catch (Exception e) {
			System.out.println("ERROR: Put to EBS failed");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public byte[] get(String key) {
		//ObjectInputStream in = null;
		byte[] value = null;
		try {
			//Check the key is for directory or not.
			if (key.substring(key.length() - 1).equals("/") == true) {
				key += m_strSpecialKeyDirectory;
			}

			if (m_bUseDirectIO == false) {
				Path path = Paths.get(m_strLocalFolder + key);
				value = Files.readAllBytes(path);
			} else {
/*				DataInputStream in = new DataInputStream(new JNAInputStream(m_strLocalFolder+key, true));
				// available stream to be read
				int nSize = in.available();

				// create buffer
				value = new byte[nSize];
				in.readFully(value);
				in.close();*/
			}
		} catch (Exception e) {
			System.out.println("ERROR: Get from EBS failed");
			e.printStackTrace();
			value = null;
		}

		return value;
	}

	@Override
	public boolean rename(String oldKey, String newKey) {
		File srcFile = new File(m_strLocalFolder + oldKey);
		File targetFile = new File(m_strLocalFolder + newKey);

		if (srcFile.exists() == true && srcFile.isFile() == true && targetFile.exists() == false) {
			return srcFile.renameTo(targetFile);
		}

		return false;
	}

	@Override
	public boolean copy(String oldKey, String newKey) {
		File srcFile = new File(m_strLocalFolder + oldKey);
		File targetFile = new File(m_strLocalFolder + newKey);

		if (srcFile.exists() == true && srcFile.isFile() == true && targetFile.exists() == false) {
			try {
				Files.copy(srcFile.toPath(), targetFile.toPath(), REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}

		return true;
	}

/*	@Override
	protected boolean rename(String oldKey, String newKey) {
		File f = new File(m_strLocalFolder + oldKey);

		if(f.exists() == true) {
			return f.renameTo(new File(m_strLocalFolder + newKey));
		}

		return false;
	}*/

	@Override
	public boolean delete(String key) {
		File f = new File(m_strLocalFolder + key);
		return f.delete();
	}

	@Override
	public boolean growTier(int byPercent) {
		return true;
	}

	@Override
	public boolean shrinkTier(int byPercent) {
		return true;
	}
}