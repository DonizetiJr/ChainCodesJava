import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Assert;
import org.junit.Test;


public class ImageProcessTest {

	@Test
	public void testImageProcess() throws IOException {
		File f = new File("src/img-circulo.png");
		BufferedImage bi = ImageIO.read(f);
		ImageProcess ip = new ImageProcess(bi);
		Assert.assertEquals(ip.getImage(), bi);
	}

	@Test
	public void testGetInitialPoint() throws IOException {
		File f = new File("src/img-circulo.png");
		BufferedImage bi = ImageIO.read(f);
		ImageProcess ip = new ImageProcess(bi);
		Assert.assertArrayEquals(new int[] {23, 16}, ip.getInitialPoint());
	}
	
	@Test
	public void testGetInitialPointNULL() throws IOException {
		
	}
	
	@Test
	public void testGetObjectWidth() throws IOException {
		File f = new File("src/img-circulo.png");
		BufferedImage bi = ImageIO.read(f);
		ImageProcess ip = new ImageProcess(bi);
		Assert.assertEquals(20, ip.getObjectWidth());
		
		f = new File("src/oval.png");
		bi = ImageIO.read(f);
		ip = new ImageProcess(bi);
		Assert.assertEquals(196, ip.getObjectWidth());
	}

	@Test
	public void testGetObjectHeight() throws IOException {
		File f = new File("src/img-circulo.png");
		BufferedImage bi = ImageIO.read(f);
		ImageProcess ip = new ImageProcess(bi);
		Assert.assertEquals(20, ip.getObjectHeight());
		
		f = new File("src/oval.png");
		bi = ImageIO.read(f);
		ip = new ImageProcess(bi);
		Assert.assertEquals(61, ip.getObjectHeight());
	}

	@Test
	public void testChainCodesPointsNumber() throws IOException {
		File f = new File("src/img-circulo.png");
		BufferedImage bi = ImageIO.read(f);
		ImageProcess ip = new ImageProcess(bi);
		Assert.assertEquals(52, (int) ip.chainCodes(0));

		f = new File("src/oval.png");
		bi = ImageIO.read(f);
		ip = new ImageProcess(bi);
		Assert.assertEquals(407, (int) ip.chainCodes(0));
	}

	@Test
	public void testChainCodesPerimeter() throws IOException {
		File f = new File("src/img-circulo.png");
		BufferedImage bi = ImageIO.read(f);
		ImageProcess ip = new ImageProcess(bi);
		Assert.assertEquals(61.94112549695426, ip.chainCodes(1));
	}
}
