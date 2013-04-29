package com.apusic.login.utils;

import java.awt.image.BufferedImage;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRUtils {
	
	public static int QR_CODE_SIZE = 256;
	
	public static BufferedImage encode(String data) {
		BufferedImage image = null;
		try {
			QRCodeWriter writer = new QRCodeWriter();
			BitMatrix matrix = writer.encode(data, BarcodeFormat.QR_CODE, QR_CODE_SIZE, QR_CODE_SIZE, null);
			image = new BufferedImage(matrix.getWidth(), matrix.getHeight(), BufferedImage.TYPE_INT_RGB);
			for (int x = 0; x < matrix.getWidth(); x++) { 
				 for (int y = 0; y < matrix.getHeight(); y++) {
					image.setRGB(x, y, (matrix.get(x, y) ? 0 : 0xFFFFFF));
				 }
			}
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}
}
