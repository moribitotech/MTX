/*******************************************************************************
 * Copyright 2012-Present, MoribitoTech
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.moribitotech.mtx.managers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.moribitotech.mtx.settings.MtxLogger;

public class FileManager {
	//
	protected static final String logTag = "MtxFileManagerLog";
	public static boolean logActive = true;
	//
	@SuppressWarnings("unused")
	private static String currentLine = "";

	// File Type
	public enum FileType {
		INTERNAL_FILE, LOCAL_FILE, EXTERNAL_FILE
	}

	/**
	 * Read lines from text file.
	 * 
	 * @param strFile
	 *            file to read
	 * @param lineNumber
	 *            line number to read
	 * @param fileType
	 *            the type of file to retrieve file (INTERNAL, LOCAL, EXTERNAL)
	 * */
	public static String readLine(String strFile, int lineNumber,
			FileType fileType) {
		// Identify file type and get storage location
		FileHandle file = getFile(strFile, fileType);

		// Start buffered reader
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				file.read()));
		String currentLine = null;
		int counter = 0;
		try {
			while ((currentLine = reader.readLine()) != null) {
				if (counter == lineNumber) {
					MtxLogger.log(logActive, true, logTag, "READ LINE: "
							+ currentLine);
					break;
				}
				counter++;
			}
			reader.close();
		} catch (IOException e) {
			MtxLogger.log(logActive, true, logTag, "CANT READ LINE: File: "
					+ strFile + ", Line Number: " + lineNumber);
			e.printStackTrace();
		}
		return currentLine;
	}

	/**
	 * Write new lines in text file
	 * 
	 * @param strFile
	 *            file to write
	 * @param value
	 *            value to write to new line
	 * @param fileType
	 *            the type of file to retrieve file (INTERNAL, LOCAL, EXTERNAL)
	 * */
	public static void writeLine(String strFile, String value, FileType fileType) {
		try {
			FileHandle file = getFile(strFile, fileType);
			FileWriter fw = new FileWriter(file.file(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(value);
			bw.newLine();
			bw.close();
			MtxLogger.log(logActive, true, logTag, "Write New Line: File: "
					+ strFile + ", value: " + value);
		} catch (IOException e) {
			MtxLogger.log(logActive, true, logTag, "CANT WRITE LINE: File: "
					+ strFile);
			e.printStackTrace();
		}
	}

	/**
	 * Re-Write an existing line in a text file without effecting other lines
	 * 
	 * @param strFile
	 *            file to write
	 * @param lineNumber
	 *            line number to write
	 * @param newValue
	 *            the new value to write over existing line
	 * @param fileType
	 *            the type of file to retrieve file (INTERNAL, LOCAL, EXTERNAL)
	 * */
	public static void writeExistingLine(String strFile, int lineNumber,
			String newValue, FileType fileType) {
		try {
			FileHandle file = getFile(strFile, fileType);
			ArrayList<String> lineByLineTextList = getUpdatedTextInfo(strFile,
					lineNumber, newValue);
			FileWriter fw = new FileWriter(file.file(), false);
			BufferedWriter bw = new BufferedWriter(fw);
			for (int i = 0; i < lineByLineTextList.size(); i++) {
				if (lineByLineTextList.get(i) != null) {
					bw.write(lineByLineTextList.get(i));
					bw.newLine();
				}
			}
			bw.close();
		} catch (IOException e) {
			MtxLogger.log(logActive, true, logTag, "CANT WRITE LINE: File: "
					+ strFile + ", Line Number: " + lineNumber);
			e.printStackTrace();
		}
	}

	private static ArrayList<String> getUpdatedTextInfo(String strFile,
			int lineNumber, String newValue) {
		ArrayList<String> lineByLineTextList = new ArrayList<String>();
		FileHandle file = Gdx.files.local(strFile);
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				file.read()));
		String currentLine = null;
		int counter = 0;
		try {
			while ((currentLine = reader.readLine()) != null) {
				if (counter == lineNumber) {
					MtxLogger.log(logActive, true, logTag,
							"WRITE EXISTING LINE: OLD: " + currentLine
									+ " - NEW: " + newValue);
					lineByLineTextList.add(newValue);
				} else {
					lineByLineTextList.add(currentLine);
				}
				counter++;
			}
			reader.close();
		} catch (IOException e) {
		}
		return lineByLineTextList;
	}

	/**
	 * If a line consist of comma separated values, it returns each value in
	 * ArrayList
	 * 
	 * @param strFile
	 *            file to read
	 * @param lineNumber
	 *            line number to read, starts from 1
	 * @param fileType
	 *            the type of file to retrieve file (INTERNAL, LOCAL, EXTERNAL)
	 * */
	public static ArrayList<String> getValuesSeperatedByCommaInLine(
			String strFile, int lineNumber, FileType fileType) {
		String lineString = readLine(strFile, lineNumber, fileType);
		ArrayList<String> values = new ArrayList<String>(
				Arrays.asList(lineString.split(",")));
		return values;
	}

	/**
	 * Create a file in a LOCAL storage. Good place the store game data in text
	 * files
	 * */
	public static void createTextFileInLocalStorage(String fileName) {
		// Get local storage
		String localDir = Gdx.files.getLocalStoragePath();

		// Create files
		try {
			new FileWriter(localDir + fileName);
			Gdx.app.log("SettingLog", "TXT FILE CREATED: " + fileName);
		} catch (IOException e) {
			Gdx.app.log("SettingLog", "CANT CREATE TEXT FILE: File: "
					+ fileName);
			e.printStackTrace();
		}
	}

	/**
	 * Get number of lines in a text file
	 * */
	public static int getNumberOflInesInTextFile(String strFile,
			FileType fileType) {
		FileHandle file = getFile(strFile, fileType);
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				file.read()));
		currentLine = "";
		int counter = 0;
		try {
			while ((currentLine = reader.readLine()) != null) {
				counter++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Gdx.app.log("SettingLog", "NUMBER OF LINES: " + file.name() + ": "
				+ counter);
		return counter;
	}

	/**
	 * Get file from one of the storages, there are three storages, INTERNAL
	 * (Read Only), LOCAL, EXTERNAL (SD CARD)
	 * 
	 * @param strFile
	 *            file name to retrieve
	 * @param fileType
	 *            file type for location identification
	 * @return the file
	 * */
	private static FileHandle getFile(String strFile, FileType fileType) {
		FileHandle file = null;
		if (fileType == FileType.INTERNAL_FILE) {
			try {
				file = Gdx.files.internal(strFile);
			} catch (Exception e) {
				MtxLogger.log(logActive, true, logTag,
						"!!! FILE IS NOT INTERNAL OR NOT EXIST: " + strFile);
			}
		} else if (fileType == FileType.LOCAL_FILE) {
			try {
				file = Gdx.files.local(strFile);
			} catch (Exception e) {
				MtxLogger.log(logActive, true, logTag,
						"!!! FILE IS NOT LOCAL OR NOT EXIST: " + strFile);
			}
		} else if (fileType == FileType.EXTERNAL_FILE) {
			try {
				file = Gdx.files.external(strFile);
			} catch (Exception e) {
				MtxLogger.log(logActive, true, logTag,
						"!!! FILE IS NOT EXTERNAL OR NOT EXIST: " + strFile);
			}
		}
		return file;
	}
}
