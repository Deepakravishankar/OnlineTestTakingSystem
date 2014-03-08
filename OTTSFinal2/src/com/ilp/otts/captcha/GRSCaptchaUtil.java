package com.ilp.otts.captcha;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;


/**
 * @author Tushar
 *
 */
public final class GRSCaptchaUtil extends RGBImageFilter {



	private static Random random = new Random();

	private static final int WIDTH = 200;

	private static final int HEIGHT = 60;

	private static GRSCaptchaUtil grsCaptchaUtil = new GRSCaptchaUtil();

	/**
	 * create new instance of GRSCaptchaUtil.java
	 */
	private GRSCaptchaUtil() {
		canFilterIndexColorModel = true;
	}

	/**
	 * @author Tushar
	 */
	public static GRSCaptchaUtil getInstance() {
		return grsCaptchaUtil;
	}



	/**
	 * @author Tushar
	 * Gets the Random String of defined size
	 * @param count
	 * @param exclude
	 * @return
	 */
	public String getCaptchaString(Integer stringCount) {
		String excludeChars = "Ii0o";
		if (stringCount <= 0) {
			throw new IllegalArgumentException("Character length must be > 0");
		}
		Integer endAscii = new Integer(122)+ 1;
		StringBuffer buf = new StringBuffer();
		char randomChar;
		while (stringCount-- != 0) {
			randomChar = (char) random.nextInt(endAscii);
			if (Character.isLetterOrDigit(randomChar)
					&& excludeChars.indexOf((int) randomChar) < 0) {
				buf.append(randomChar);
			} else {
				stringCount++;
			}
		}
		return buf.toString();
	}
	/**
	 * @author Tushar
	 * Gets the Random captcha image
	 * @param count
	 * @param exclude
	 * @return
	 */
	public BufferedImage getCaptchaImage(String captchaStr, int width,
			int height) {
		BufferedImage retBufImage = null;
		Graphics graphicsLine = null;
		Graphics captchaImg = null;
		BufferedImage bufferImg = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		try {
			graphicsLine = bufferImg.getGraphics();
			graphicsLine.setColor(new Color(Integer.parseInt("c0c0c0", 16)));
			graphicsLine.fillRect(0, 0, width, width);
			graphicsLine
					.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 30));
			graphicsLine.setColor(Color.GRAY);
			drawRandomLine(graphicsLine, 16);
			drawMessage(graphicsLine, captchaStr);

			ImageProducer source = bufferImg.getSource();
			ImageProducer producer = new FilteredImageSource(source, this);
			Image filteredImg = Toolkit.getDefaultToolkit().createImage(
					producer);
			retBufImage = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			captchaImg = retBufImage.getGraphics();
			captchaImg.drawImage(filteredImg, 0, 0, null);
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if (graphicsLine != null)
				graphicsLine.dispose();
			if (captchaImg != null)
				captchaImg.dispose();
		}
		return retBufImage;
	}

	private void drawMessage(Graphics graphics, String captchaStr) {
		graphics.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 30));
		graphics.setColor(Color.GRAY);
		int len = captchaStr.length();
		int wgap = WIDTH / len;
		int startX = 10;
		int startY = 20;
		for (int i = 0; i < len; i++) {
			graphics.drawString(captchaStr.substring(i, i + 1), startX
					+ (wgap * i), startY + random.nextInt(40));
		}
	}

	/**
	 * @author Tushar
	 * Used to draw lines for defined times
	 * @param g
	 * @param count
	 */
	private void drawRandomLine(Graphics graphics, int count) {
		while (count > 0) {
			drawRandomLine(graphics);
			count--;
		}
	}

	/**
	 * @author Tushar
	 * Draws a line
	 * @param g
	 */
	private void drawRandomLine(Graphics graphics) {
		int x1 = random.nextInt(WIDTH);
		int y1 = random.nextInt(HEIGHT);
		int x2 = random.nextInt(WIDTH);
		int y2 = random.nextInt(HEIGHT);
		graphics.drawLine(x1, y1, x2, y2);
	}

	@Override
	public int filterRGB(int x, int y, int rgb) {
		return (rgb | 0x000000FF);
	}

	/**
	 * @author Tushar
	 * Generates Random Text for displaying on the image
	 * @return <String>
	 */
	private String getRandomString() {
		String str = new String(
				"QAa0bcLdUK2eHfJgTP8XhiFj61DOklNm9nBoI5pGqYVrs3CtSuMZvwWx4yE7zR");
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		int te = 0;
		for (int i = 1; i <= 6; i++) {
			te = r.nextInt(62);
			sb.append(str.charAt(te));
		}
		return sb.toString();
	}
}
