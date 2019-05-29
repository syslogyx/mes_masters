package com.syslogyx.utility;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.syslogyx.bo.Pagination;
import com.syslogyx.constants.IConstants;
import com.syslogyx.constants.IConstants.DATE_TIME_FORMAT;
import com.syslogyx.exception.ApplicationException;
import com.syslogyx.message.IResponseCodes;
import com.syslogyx.message.IResponseMessages;

/**
 * Maintain Utility for Application
 * 
 * @author namrata
 *
 */
public class Utils {
	static Cipher cipher;
	static SecretKey secretKey;

	static {
		KeyGenerator keyGenerator;
		try {
			keyGenerator = KeyGenerator.getInstance("AES");

			keyGenerator.init(128);
			secretKey = keyGenerator.generateKey();
			cipher = Cipher.getInstance("AES");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static final Logger LOG = Logger.getLogger(">>SMART_FACTORY>>");

	public static String encrypt(String plainText) throws Exception {
		byte[] plainTextByte = plainText.getBytes();
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] encryptedByte = cipher.doFinal(plainTextByte);
		Base64.Encoder encoder = Base64.getEncoder();
		String encryptedText = encoder.encodeToString(encryptedByte);
		return encryptedText;
	}

	public static String decrypt(String encryptedText) throws Exception {
		Base64.Decoder decoder = Base64.getDecoder();
		byte[] encryptedTextByte = decoder.decode(encryptedText);
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
		String decryptedText = new String(decryptedByte);
		return decryptedText;
	}

	/**
	 * Return Current date in "yyyy/MM/dd" this format
	 *
	 * @return
	 */
	public static String getCurrentDateString() {
		try {
			DateFormat dateFormat = new SimpleDateFormat(DATE_TIME_FORMAT.YYYY_MM_DD_HH_MM_SS_A);
			Date date = new Date();
			return dateFormat.format(date);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Return Current date
	 *
	 * @return
	 */
	public static Date getCurrentDate() {
		try {
			Date date = new Date();
			return date;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Return Current date in "yyyy/MM/dd HH:mm:ss" this format
	 * 
	 * @return
	 */
	public static String getCurrentDateTime() {
		try {
			DateFormat dateFormat = new SimpleDateFormat(DATE_TIME_FORMAT.YYYY_MM_DD_HH_MM_SS_A);
			Date date = new Date();
			return dateFormat.format(date);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Get encoded string in MD5 format
	 * 
	 * @param message
	 * @return
	 * @throws ApplicationException
	 */
	public static String generateMD5(String message) throws ApplicationException {
		return hashString(message, "MD5");
	}

	/**
	 * Get encoded string in SHA-1 format
	 * 
	 * @param message
	 * @return
	 * @throws ApplicationException
	 */
	public static String generateSHA1(String message) throws ApplicationException {
		return hashString(message, "SHA-1");
	}

	/**
	 * Get encoded string in SHA-256 format
	 * 
	 * @param message
	 * @return
	 * @throws ApplicationException
	 */
	public static String generateSHA256(String message) throws ApplicationException {
		return hashString(message, "SHA-256");
	}

	/**
	 * Apply hashing technique according to provided algorithm
	 * 
	 * @param message
	 * @param algorithm
	 * @return
	 * @throws ApplicationException
	 */
	private static String hashString(String message, String algorithm) throws ApplicationException {

		try {
			MessageDigest digest = MessageDigest.getInstance(algorithm);
			byte[] hashedBytes = digest.digest(message.getBytes("UTF-8"));

			return convertByteArrayToHexString(hashedBytes);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
			throw new ApplicationException(IResponseCodes.SERVER_ERROR, IResponseMessages.CANT_CONVERT_HASH_CODE);

		}
	}

	/**
	 * Convert byte array to hex string
	 * 
	 * @param arrayBytes
	 * @return
	 */
	private static String convertByteArrayToHexString(byte[] arrayBytes) {
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < arrayBytes.length; i++) {
			stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		return stringBuffer.toString();
	}

	/**
	 * Parse the given instance to long
	 * 
	 * @param count
	 * @return
	 */
	public static long getParsedValueInLong(Object count) {
		if (count != null) {
			if (count instanceof BigDecimal) {
				BigDecimal bigDecimal = (BigDecimal) count;
				if (bigDecimal != null) {
					return bigDecimal.longValue();
				}
			} else if (count instanceof BigInteger) {
				BigInteger bigInteger = (BigInteger) count;
				if (bigInteger != null) {
					return bigInteger.longValue();
				}
			} else if (count instanceof Integer) {
				Integer integer = (Integer) count;
				if (integer != null) {
					return integer;
				}

			} else if (count instanceof Double) {
				Double doubleValue = (Double) count;
				if (doubleValue != null) {
					return doubleValue.longValue();
				}
			} else if (count instanceof Long) {
				Long longValue = (Long) count;
				if (longValue != null) {
					return longValue;
				}
			} else if (count instanceof String) {
				Long longValue = Long.parseLong(count.toString());
				if (longValue != null) {
					return longValue;
				}
			}
		}
		return 0;
	}

	/**
	 * Get Parsed value in Double
	 * 
	 * @param count
	 * @return
	 */
	public static double getParsedValueInDouble(Object count) {
		if (count != null) {
			if (count instanceof BigDecimal) {
				BigDecimal bigDecimal = (BigDecimal) count;
				if (bigDecimal != null) {
					return bigDecimal.doubleValue();
				}
			} else if (count instanceof BigInteger) {
				BigInteger bigInteger = (BigInteger) count;
				if (bigInteger != null) {
					return bigInteger.doubleValue();
				}
			} else if (count instanceof Integer) {
				Integer integer = (Integer) count;
				if (integer != null) {
					return integer.doubleValue();
				}

			} else if (count instanceof Double) {
				Double doubleValue = (Double) count;
				if (doubleValue != null) {
					return doubleValue.doubleValue();
				}
			} else if (count instanceof Float) {
				Float longValue = (Float) count;
				if (longValue != null) {
					return longValue.doubleValue();
				}
			} else if (count instanceof String) {
				return Double.parseDouble(count.toString());
			}
		}
		return 0;
	}

	/**
	 * Return Current date in provided format
	 *
	 * @return String for current date
	 */
	public static String getCurrentFormatedDate(String format) {
		try {
			DateFormat dateFormat = new SimpleDateFormat(format);
			Date date = new Date();
			return dateFormat.format(date);
		} catch (Exception e) {
			return null;
		}
	}

	/***
	 * Returns the random number between and inclusive specified minimum and maximum
	 * value range.
	 * 
	 * @param min
	 * @param max
	 * @return int
	 */
	public static int getRandomNo(int min, int max) {

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		return ThreadLocalRandom.current().nextInt(min, max + 1);

	}

	/**
	 * Prepare the Pagination Object according to provided page count and limit
	 * 
	 * @param current_page
	 * @param count
	 * @param limit
	 * @return
	 */
	public static Pagination getPagination(int current_page, long count, int limit) {
		// get Page Count
		float pages = ((float) count / (float) limit);
		int page_count = (int) Math.ceil(pages);

		// check whether page have previous page
		boolean has_prev_page = true;
		if (current_page == 1) {
			has_prev_page = false;
		}

		// check whether page have next page
		boolean has_next_page = true;
		if (current_page >= page_count) {
			has_next_page = false;
		}

		// Set the Page Data
		Pagination pagination = new Pagination(page_count, current_page, has_next_page, has_prev_page, count,
				page_count);

		return pagination;
	}

	public static String getRandomString() {
		String uuid = UUID.randomUUID().toString();
		return uuid.replace("-", "");

	}

	public static String getRandomString(int length) {
		String uuid = UUID.randomUUID().toString();
		return uuid.replace("-", "").substring(0, length);
	}

	/***
	 * This method accepts time in seconds and returns in hh:mm:ss format
	 * 
	 * @param timeInSeconds
	 * @return returns the time in hh:mm:ss format
	 */
	public static String formatSeconds(long timeInSeconds) {
		int hours = (int) (timeInSeconds / 3600);
		int secondsLeft = (int) (timeInSeconds - hours * 3600);
		int minutes = secondsLeft / 60;
		int seconds = secondsLeft - minutes * 60;

		String formattedTime = "";
		if (hours < 10)
			formattedTime += "0";
		formattedTime += hours + ":";

		if (minutes < 10)
			formattedTime += "0";
		formattedTime += minutes + ":";

		if (seconds < 10)
			formattedTime += "0";
		formattedTime += seconds;

		return formattedTime;
	}

	/**
	 * Calculate Difference between two dates in seconds
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getDifferenceBetweenTwoDatesInSeconds(Date date1, Date date2) {

		if (date1 != null && date2 != null) {
			long diff = (Math.abs(date1.getTime() - date2.getTime()) / 1000);

			return diff;
		}
		return IConstants.VALUE_ZERO;
	}

	/**
	 * Parse the Provided Date into required format
	 * 
	 * @param date
	 *            : Instance of date to be parsed
	 * @param current_format
	 *            : Current format of Date
	 * @param target_format
	 *            : Target format of Date
	 * @return
	 */
	public static Date getFormatedDate(Date date, String current_format, String target_format) {
		DateFormat currentFormat = new SimpleDateFormat(current_format);
		DateFormat targetFormat = new SimpleDateFormat(target_format);
		Date formatedDate = null;

		// if date is not provided then consider the current date in the current format
		if (date == null)
			date = new Date();

		String dateString = currentFormat.format(date);

		try {
			formatedDate = targetFormat.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return formatedDate;
	}

	/**
	 * Parse the Provided Date into required format
	 * 
	 * @param date
	 *            : Instance of date to be parsed
	 * @param target_format
	 *            : Target format of Date
	 * @return
	 */
	public static String getFormatedDate(Date date, String target_format) {
		DateFormat targetFormat = new SimpleDateFormat(target_format);

		// if date is not provided then consider the current date in the current format
		if (date == null)
			date = new Date();

		return targetFormat.format(date);
	}

	/**
	 * Prepare the Header row of Excel File according to the provided Header String
	 * List
	 * 
	 * @param sheet
	 * @param excelHeaders
	 * @param cellStyle
	 * @param cellFont
	 */
	public static void writeToExcelHeaderRow(HSSFSheet sheet, List<String> excelHeaders, HSSFCellStyle cellStyle,
			HSSFFont cellFont) {
		HSSFRow rowhead = sheet.createRow(IConstants.VALUE_ZERO);

		// Set the Font to the Cell
		cellFont.setBoldweight(cellFont.BOLDWEIGHT_BOLD);
		cellFont.setFontName(cellFont.FONT_ARIAL);
		cellFont.setColor(HSSFColor.BLUE_GREY.index);

		// Set the Style to the Cell
		cellStyle.setAlignment(cellStyle.ALIGN_CENTER);

		cellStyle.setWrapText(true);
		cellStyle.setFont(cellFont);

		// loop through the Header list and write into the Header row
		for (int index = 0; index < excelHeaders.size(); index++) {

			HSSFCell headerCell = rowhead.createCell(index);
			headerCell.setCellValue(excelHeaders.get(index));

			headerCell.setCellStyle(cellStyle);
		}
	}

	/**
	 * Write the Details from the workbook instance which is provided in the
	 * parameter to the file on the provided path
	 * 
	 * @param workbook
	 * @param filepath
	 * @return
	 * @throws ApplicationException
	 */
	public static String writeDataToWorkbook(HSSFWorkbook workbook, String filepath) throws ApplicationException {
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(filepath + IConstants.EXTENSION_EXCEL);
			workbook.write(fileOut);
			fileOut.close();

			return filepath + IConstants.EXTENSION_EXCEL;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new ApplicationException(IResponseCodes.SERVER_ERROR, IResponseMessages.UNABLE_TO_LOCATE_FILE);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ApplicationException(IResponseCodes.SERVER_ERROR, IResponseMessages.UNABLE_TO_EXORT_EXCEL);
		}
	}

	/**
	 * Prepare the File Path by appending the time stamp
	 * 
	 * @param basePath
	 * @param fileName
	 * @return
	 */
	public static String getFilePath(String basePath, String fileName) {
		if (fileName != null && !fileName.isEmpty()) {
			return basePath + System.currentTimeMillis() + fileName;
		}
		return fileName;
	}

	/**
	 * Prepare the Header row of PDF File according to the provided Header String
	 * List
	 * 
	 * @param table
	 * @param headerList
	 * 
	 */
	public static void writeToPDFHeaderRow(PdfPTable table, List<String> headerList) {

		for (int index = 0; index < headerList.size(); index++) {
			table.addCell(new Phrase(headerList.get(index), FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8)));
			// table.addCell(headerList.get(index));
		}

		PdfPRow headerRows = table.getRow(IConstants.VALUE_ZERO);
		for (int index = 0; index < headerRows.getCells().length; index++) {
			// headerRows.getCells()[index].setBackgroundColor(BaseColor.GRAY);
			headerRows.getCells()[index].setFixedHeight(30);
		}

		table.setWidthPercentage(100);
	}

	/**
	 * For writing data to pdf
	 * 
	 * @param document
	 * @param table
	 * @param filename
	 * @return
	 * @throws ApplicationException
	 */
	public static String writeDataToPDF(Document document, PdfPTable table, String filename)
			throws ApplicationException {
		try {
			PdfWriter.getInstance(document, new FileOutputStream(filename + IConstants.EXTENSION_PDF));
			document.open();
			document.add(table);
			document.close();

			return filename + IConstants.EXTENSION_PDF;
		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
			throw new ApplicationException(IResponseCodes.SERVER_ERROR, IResponseMessages.UNABLE_TO_EXORT_PDF);
		}
	}

	/**
	 * Validate the Date value by the required format
	 * 
	 * @param date_time
	 * @param format
	 * @throws ApplicationException
	 */
	public static void validateDateFormat(String date_time, String format) throws ApplicationException {
		if (date_time != null && !date_time.isEmpty()) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			sdf.setLenient(false);

			try {
				// if not valid, it will throw ParseException
				Date date = sdf.parse(date_time);

			} catch (Exception e) {
				e.printStackTrace();
				throw new ApplicationException(IResponseCodes.INVALID_DATA, IResponseMessages.INALID_DATETIME_FORMAT);
			}
		}
	}

}
