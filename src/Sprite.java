import java.awt.Component;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.PixelGrabber;

import javax.swing.ImageIcon;

public final class Sprite extends DrawingArea {

	public Sprite(int i, int j) {
		myPixels = new int[i * j];
		myWidth = anInt1444 = i;
		myHeight = anInt1445 = j;
		drawOffsetX = drawOffsetY = 0;
	}
	
	
	public String location = SignLink.findcachedir() + "Sprites/";

	public Sprite(byte abyte0[], Component component) {
		try {
			//Image image = Toolkit.getDefaultToolkit().getImage(location+"mopar.jpg");
			Image image = Toolkit.getDefaultToolkit().createImage(abyte0);
			MediaTracker mediatracker = new MediaTracker(component);
			mediatracker.addImage(image, 0);
			mediatracker.waitForAll();
			myWidth = image.getWidth(component);
			myHeight = image.getHeight(component);
			anInt1444 = myWidth;
			anInt1445 = myHeight;
			drawOffsetX = 0;
			drawOffsetY = 0;
			myPixels = new int[myWidth * myHeight];
			PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, myWidth, myHeight, myPixels, 0, myWidth);
			pixelgrabber.grabPixels();
		} catch(Exception _ex) {
			System.out.println("Error converting jpg");
		}
	}

    public Sprite(String img) {
		try {
			Image image = Toolkit.getDefaultToolkit().getImage(location + img + ".png");
			ImageIcon sprite = new ImageIcon(image);
			myWidth = sprite.getIconWidth();
			myHeight = sprite.getIconHeight();
			anInt1444 = myWidth;
			anInt1445 = myHeight;
			drawOffsetX = 0;
			drawOffsetY = 0;
			myPixels = new int[myWidth * myHeight];
			PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, myWidth, myHeight, myPixels, 0, myWidth);
			pixelgrabber.grabPixels();
			image = null;
			setTransparency(255, 0, 255);
		} catch(Exception _ex) {
			System.out.println(_ex);
		}
	}
    
	public Sprite(byte spriteData[]) {
		try {
			Image image = Toolkit.getDefaultToolkit().createImage(spriteData);
			ImageIcon sprite = new ImageIcon(image);
			myWidth = sprite.getIconWidth();
			myHeight = sprite.getIconHeight();
			anInt1444 = myWidth;
			anInt1445 = myHeight;
			drawOffsetX = 0;
			drawOffsetY = 0;
			myPixels = new int[myWidth * myHeight];
			PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, myWidth, myHeight, myPixels, 0, myWidth);
			pixelgrabber.grabPixels();
			image = null;
			setTransparency(255, 0, 255);
		} catch (Exception _ex) {
			System.out.println(_ex);
		}
	}
	
	public Sprite(StreamLoader streamLoader, int width, int height) {
		try {
			Image image = Toolkit.getDefaultToolkit().createImage(EMPTY);
			myWidth = width;
			myHeight = height;
			anInt1444 = myWidth;
			anInt1445 = myHeight;
			drawOffsetX = 0;
			drawOffsetY = 0;
			myPixels = new int[myWidth * myHeight];
			PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, myWidth, myHeight, myPixels, 0, myWidth);
			pixelgrabber.grabPixels();
			image = null;
			setTransparency(255, 0, 255);
		} catch(Exception e) { }
	}
	
	public Sprite(String s, int width, int height) {
		try {
			Image image = Toolkit.getDefaultToolkit().createImage(HITPOINTS);
			myWidth = width;
			myHeight = height;
			anInt1444 = myWidth;
			anInt1445 = myHeight;
			drawOffsetX = 0;
			drawOffsetY = 0;
			myPixels = new int[myWidth * myHeight];
			PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, myWidth, myHeight, myPixels, 0, myWidth);
			pixelgrabber.grabPixels();
			image = null;
			setTransparency(255, 0, 255);
			setTransparency(255, 255, 255);
		} catch(Exception e) { }
	}		
	
	public void draw24BitSprite(int x, int y) {
		int alpha = 256;
		x += this.drawOffsetX;// offsetX
		y += this.drawOffsetY;// offsetY
		int destOffset = x + y * DrawingArea.width;
		int srcOffset = 0;
		int height = this.myHeight;
		int width = this.myWidth;
		int destStep = DrawingArea.width - width;
		int srcStep = 0;
		if (y < DrawingArea.topY) {
			int trimHeight = DrawingArea.topY - y;
			height -= trimHeight;
			y = DrawingArea.topY;
			srcOffset += trimHeight * width;
			destOffset += trimHeight * DrawingArea.width;
		}
		if (y + height > DrawingArea.bottomY) {
			height -= (y + height) - DrawingArea.bottomY;
		}
		if (x < DrawingArea.topX) {
			int trimLeft = DrawingArea.topX - x;
			width -= trimLeft;
			x = DrawingArea.topX;
			srcOffset += trimLeft;
			destOffset += trimLeft;
			srcStep += trimLeft;
			destStep += trimLeft;
		}
		if (x + width > DrawingArea.bottomX) {
			int trimRight = (x + width) - DrawingArea.bottomX;
			width -= trimRight;
			srcStep += trimRight;
			destStep += trimRight;
		}
		if (!((width <= 0) || (height <= 0))) {
			set24BitPixels(width, height, DrawingArea.pixels, myPixels, alpha, destOffset,
				srcOffset, destStep, srcStep);
		}
	}
	
	public void drawSprite1(int i, int j, int alpha) {
		int k = alpha;// was parameter
		i += drawOffsetX;
		j += drawOffsetY;
		int i1 = i + j * DrawingArea.width;
		int j1 = 0;
		int k1 = myHeight;
		int l1 = myWidth;
		int i2 = DrawingArea.width - l1;
		int j2 = 0;
		if (j < DrawingArea.topY) {
			int k2 = DrawingArea.topY - j;
			k1 -= k2;
			j = DrawingArea.topY;
			j1 += k2 * l1;
			i1 += k2 * DrawingArea.width;
		}
		if (j + k1 > DrawingArea.bottomY)
			k1 -= (j + k1) - DrawingArea.bottomY;
		if (i < DrawingArea.topX) {
			int l2 = DrawingArea.topX - i;
			l1 -= l2;
			i = DrawingArea.topX;
			j1 += l2;
			i1 += l2;
			j2 += l2;
			i2 += l2;
		}
		if (i + l1 > DrawingArea.bottomX) {
			int i3 = (i + l1) - DrawingArea.bottomX;
			l1 -= i3;
			j2 += i3;
			i2 += i3;
		}
		if (!(l1 <= 0 || k1 <= 0)) {
			method351(j1, l1, DrawingArea.pixels, myPixels, j2, k1, i2, k, i1);
		}
	}	
	
    public void drawARGBImage(int x, int y, int alpha) {
        x += this.drawOffsetX;
        y += this.drawOffsetY;
        int destOffset = x + y * DrawingArea.width;
        int srcOffset = 0;
        int height = this.myHeight;
        int width = this.myWidth;
        int destStep = DrawingArea.width - width;
        int srcStep = 0;
        if (y < DrawingArea.topY) {
            int trimHeight = DrawingArea.topY - y;
            height -= trimHeight;
            y = DrawingArea.topY;
            srcOffset += trimHeight * width;
            destOffset += trimHeight * DrawingArea.width;
        }
        if (y + height > DrawingArea.bottomY) {
        	height -= (y + height) - DrawingArea.bottomY;
        }
        if (x < DrawingArea.topX) {
            int trimLeft = DrawingArea.topX - x;
            width -= trimLeft;
            x = DrawingArea.topX;
            srcOffset += trimLeft;
            destOffset += trimLeft;
            srcStep += trimLeft;
            destStep += trimLeft;
        }
        if (x + width > DrawingArea.bottomX) {
            int trimRight = (x + width) - DrawingArea.bottomX;
            width -= trimRight;
            srcStep += trimRight;
            destStep += trimRight;
        }
        if (!((width <= 0) || (height <= 0))) {
        	setARGBPixels(width, height, DrawingArea.pixels, myPixels, alpha, destOffset, srcOffset, destStep, srcStep);
        }
    }
    
    private void setARGBPixels(int width, int height, int destPixels[], int srcPixels[], int alpha, int destOffset, int srcOffset, int destStep, int srcStep) {
        int srcColor;
        int destAlpha;
        for (int loop = -height; loop < 0; loop++) {
            for (int loop2 = -width; loop2 < 0; loop2++) {
                int srcAlpha = alpha - (255 - ((this.myPixels[srcOffset] >> 24) & 255));
                if(srcAlpha < 0) srcAlpha = 0;
                destAlpha = (256 - srcAlpha);
                srcColor = srcPixels[srcOffset++];
                if (srcColor != 0 && srcColor != 0xffffff) {
                    int destColor = destPixels[destOffset];
                    destPixels[destOffset++] = ((srcColor & 0xff00ff) * srcAlpha + (destColor & 0xff00ff) * destAlpha & 0xff00ff00) + ((srcColor & 0xff00) * srcAlpha + (destColor & 0xff00) * destAlpha & 0xff0000) >> 8;
                } else {
                	destOffset++;
                }
            }
            destOffset += destStep;
            srcOffset += srcStep;
        }
    }
	
	public void drawARGBSprite(int xPos, int yPos) {
		drawARGBSprite(xPos, yPos, 256);
	}

	public void drawARGBSprite(int xPos, int yPos, int alpha) {
		int alphaValue = alpha;
		xPos += drawOffsetX;
		yPos += drawOffsetY;
		int i1 = xPos + yPos * DrawingArea.width;
		int j1 = 0;
		int spriteHeight = myHeight;
		int spriteWidth = myWidth;
		int i2 = DrawingArea.width - spriteWidth;
		int j2 = 0;
		if (yPos < DrawingArea.topY) {
			int k2 = DrawingArea.topY - yPos;
			spriteHeight -= k2;
			yPos = DrawingArea.topY;
			j1 += k2 * spriteWidth;
			i1 += k2 * DrawingArea.width;
		}
		if (yPos + spriteHeight > DrawingArea.bottomY)
			spriteHeight -= (yPos + spriteHeight) - DrawingArea.bottomY;
			if (xPos < DrawingArea.topX) {
			int l2 = DrawingArea.topX - xPos;
			spriteWidth -= l2;
			xPos = DrawingArea.topX;
			j1 += l2;
			i1 += l2;
			j2 += l2;
			i2 += l2;
		}
		if (xPos + spriteWidth > DrawingArea.bottomX) {
			int i3 = (xPos + spriteWidth) - DrawingArea.bottomX;
			spriteWidth -= i3;
			j2 += i3;
			i2 += i3;
		}
		if (!(spriteWidth <= 0 || spriteHeight <= 0)) {
			renderARGBPixels(spriteWidth, spriteHeight, myPixels, DrawingArea.pixels, i1, alphaValue, j1, j2, i2);
		}
	}

    private void renderARGBPixels(int spriteWidth, int spriteHeight, int spritePixels[], int renderAreaPixels[], int pixel, int alphaValue, int i, int l, int j1) {
    	int pixelColor;
    	int alphaLevel;
    	int alpha = alphaValue;
    	for (int height = -spriteHeight; height < 0; height++) {
    		for (int width = -spriteWidth; width < 0; width++) {
    			alphaValue = ((myPixels[i] >> 24) & (alpha - 1));
    			alphaLevel = 256 - alphaValue;
    			if (alphaLevel > 256) {
    				alphaValue = 0;
    			}
    			if (alpha == 0) {
    				alphaLevel = 256;
    				alphaValue = 0;
    			} 
    			pixelColor = spritePixels[i++];
    			if (pixelColor != 0) {
    				int pixelValue = renderAreaPixels[pixel];
    				renderAreaPixels[pixel++] = ((pixelColor & 0xff00ff) * alphaValue + (pixelValue & 0xff00ff) * alphaLevel & 0xff00ff00) + ((pixelColor & 0xff00) * alphaValue + (pixelValue & 0xff00) * alphaLevel & 0xff0000) >> 8;
    			} else {
    				pixel++;
    			}
    		}
    		pixel += j1;
    		i += l;
    	}
		/*int alpha = 256 - alphaValue;
		for (int k2 = -spriteHeight; k2 < 0; k2++) {
			for (int l2 = -spriteWidth; l2 < 0; l2++) {
				pixelLevel = spritePixels[i++];
				if (pixelLevel != 0) {
					int i3 = renderAreaPixels[pixel];
					renderAreaPixels[pixel++] = ((pixelLevel & 0xff00ff) * alphaValue + (i3 & 0xff00ff) * alpha & 0xff00ff00) + ((pixelLevel & 0xff00) * alphaValue + (i3 & 0xff00) * alpha & 0xff0000) >> 8;
				} else {
					pixel++;
				}
			}
			pixel += j1;
			i += l;
		}*/
    }			
	
	public void drawTransparentSprite(int i, int j, int opacity)
	{
		int k = opacity;//was parameter
		i += drawOffsetX;
		j += drawOffsetY;
		int i1 = i + j * DrawingArea.width;
		int j1 = 0;
		int k1 = myHeight;
		int l1 = myWidth;
		int i2 = DrawingArea.width - l1;
		int j2 = 0;
		if(j < DrawingArea.topY)
		{
			int k2 = DrawingArea.topY - j;
			k1 -= k2;
			j = DrawingArea.topY;
			j1 += k2 * l1;
			i1 += k2 * DrawingArea.width;
		}
		if(j + k1 > DrawingArea.bottomY)
			k1 -= (j + k1) - DrawingArea.bottomY;
		if(i < DrawingArea.topX)
		{
			int l2 = DrawingArea.topX - i;
			l1 -= l2;
			i = DrawingArea.topX;
			j1 += l2;
			i1 += l2;
			j2 += l2;
			i2 += l2;
		}
		if(i + l1 > DrawingArea.bottomX)
		{
			int i3 = (i + l1) - DrawingArea.bottomX;
			l1 -= i3;
			j2 += i3;
			i2 += i3;
		}
		if(!(l1 <= 0 || k1 <= 0))
		{
			method351(j1, l1, DrawingArea.pixels, myPixels, j2, k1, i2, k, i1);
		}
	}	

	private void set24BitPixels(int width, int height, int destPixels[], int srcPixels[],
		int srcAlpha, int destOffset, int srcOffset, int destStep, int srcStep) {
		int srcColor;
		int destAlpha;
		for (int loop = -height; loop < 0; loop++) {
			for (int loop2 = -width; loop2 < 0; loop2++) {
				srcAlpha = ((this.myPixels[srcOffset] >> 24) & 255);
				destAlpha = 256 - srcAlpha;
				srcColor = srcPixels[srcOffset++];
				if (srcColor != 0 && srcColor != 0xffffff) {
					int destColor = destPixels[destOffset];
					destPixels[destOffset++] = ((srcColor & 0xff00ff) * srcAlpha
						+ (destColor & 0xff00ff) * destAlpha & 0xff00ff00)
						+ ((srcColor & 0xff00) * srcAlpha + (destColor & 0xff00) * destAlpha & 0xff0000) >> 8;
				} else {
					destOffset++;
				}
			}
			destOffset += destStep;
			srcOffset += srcStep;
		}
	}	
	
	public void setTransparency(int transRed, int transGreen, int transBlue)
	{
		for(int index = 0; index < myPixels.length; index++)
			if(((myPixels[index] >> 16) & 255) == transRed && ((myPixels[index] >> 8) & 255) == transGreen && (myPixels[index] & 255) == transBlue)
				myPixels[index] = 0;
	}
	
	public Sprite(StreamLoader streamLoader, String s, int i)
	{
		Stream stream = new Stream(streamLoader.getDataForName(s + ".dat"));
		Stream stream_1 = new Stream(streamLoader.getDataForName("index.dat"));
		stream_1.currentOffset = stream.readUnsignedWord();
		anInt1444 = stream_1.readUnsignedWord();
		anInt1445 = stream_1.readUnsignedWord();
		int j = stream_1.readUnsignedByte();
		int ai[] = new int[j];
		for(int k = 0; k < j - 1; k++)
		{
			ai[k + 1] = stream_1.read3Bytes();
			if(ai[k + 1] == 0)
				ai[k + 1] = 1;
		}

		for(int l = 0; l < i; l++)
		{
			stream_1.currentOffset += 2;
			stream.currentOffset += stream_1.readUnsignedWord() * stream_1.readUnsignedWord();
			stream_1.currentOffset++;
		}

		drawOffsetX = stream_1.readUnsignedByte();
		drawOffsetY = stream_1.readUnsignedByte();
		myWidth = stream_1.readUnsignedWord();
		myHeight = stream_1.readUnsignedWord();
		int i1 = stream_1.readUnsignedByte();
		int j1 = myWidth * myHeight;
		myPixels = new int[j1];
		if(i1 == 0)
		{
			for(int k1 = 0; k1 < j1; k1++)
				myPixels[k1] = ai[stream.readUnsignedByte()];
			setTransparency(255,0,255);
			return;
		}
		if(i1 == 1)
		{
			for(int l1 = 0; l1 < myWidth; l1++)
			{
				for(int i2 = 0; i2 < myHeight; i2++)
					myPixels[l1 + i2 * myWidth] = ai[stream.readUnsignedByte()];
			}

		}
		setTransparency(255,0,255);
	}

	public void method343()
	{
		DrawingArea.initDrawingArea(myHeight, myWidth, myPixels);
	}

	public void method344(int i, int j, int k)
	{
		for(int i1 = 0; i1 < myPixels.length; i1++)
		{
			int j1 = myPixels[i1];
			if(j1 != 0)
			{
				int k1 = j1 >> 16 & 0xff;
				k1 += i;
				if(k1 < 1)
					k1 = 1;
				else
				if(k1 > 255)
					k1 = 255;
				int l1 = j1 >> 8 & 0xff;
				l1 += j;
				if(l1 < 1)
					l1 = 1;
				else
				if(l1 > 255)
					l1 = 255;
				int i2 = j1 & 0xff;
				i2 += k;
				if(i2 < 1)
					i2 = 1;
				else
				if(i2 > 255)
					i2 = 255;
				myPixels[i1] = (k1 << 16) + (l1 << 8) + i2;
			}
		}

	}

	public void method345()
	{
		int ai[] = new int[anInt1444 * anInt1445];
		for(int j = 0; j < myHeight; j++)
		{
			System.arraycopy(myPixels, j * myWidth, ai, j + drawOffsetY * anInt1444 + drawOffsetX, myWidth);
		}

		myPixels = ai;
		myWidth = anInt1444;
		myHeight = anInt1445;
		drawOffsetX = 0;
		drawOffsetY = 0;
	}

	public void method346(int i, int j)
	{
		i += drawOffsetX;
		j += drawOffsetY;
		int l = i + j * DrawingArea.width;
		int i1 = 0;
		int j1 = myHeight;
		int k1 = myWidth;
		int l1 = DrawingArea.width - k1;
		int i2 = 0;
		if(j < DrawingArea.topY)
		{
			int j2 = DrawingArea.topY - j;
			j1 -= j2;
			j = DrawingArea.topY;
			i1 += j2 * k1;
			l += j2 * DrawingArea.width;
		}
		if(j + j1 > DrawingArea.bottomY)
			j1 -= (j + j1) - DrawingArea.bottomY;
		if(i < DrawingArea.topX)
		{
			int k2 = DrawingArea.topX - i;
			k1 -= k2;
			i = DrawingArea.topX;
			i1 += k2;
			l += k2;
			i2 += k2;
			l1 += k2;
		}
		if(i + k1 > DrawingArea.bottomX)
		{
			int l2 = (i + k1) - DrawingArea.bottomX;
			k1 -= l2;
			i2 += l2;
			l1 += l2;
		}
		if(k1 <= 0 || j1 <= 0)
		{
		} else
		{
			method347(l, k1, j1, i2, i1, l1, myPixels, DrawingArea.pixels);
		}
	}

	private void method347(int i, int j, int k, int l, int i1, int k1,
						   int ai[], int ai1[])
	{
		int l1 = -(j >> 2);
		j = -(j & 3);
		for(int i2 = -k; i2 < 0; i2++)
		{
			for(int j2 = l1; j2 < 0; j2++)
			{
				ai1[i++] = ai[i1++];
				ai1[i++] = ai[i1++];
				ai1[i++] = ai[i1++];
				ai1[i++] = ai[i1++];
			}

			for(int k2 = j; k2 < 0; k2++)
				ai1[i++] = ai[i1++];

			i += k1;
			i1 += l;
		}
	}

	public void drawSprite1(int i, int j)
	{
		int k = 128;//was parameter
		i += drawOffsetX;
		j += drawOffsetY;
		int i1 = i + j * DrawingArea.width;
		int j1 = 0;
		int k1 = myHeight;
		int l1 = myWidth;
		int i2 = DrawingArea.width - l1;
		int j2 = 0;
		if(j < DrawingArea.topY)
		{
			int k2 = DrawingArea.topY - j;
			k1 -= k2;
			j = DrawingArea.topY;
			j1 += k2 * l1;
			i1 += k2 * DrawingArea.width;
		}
		if(j + k1 > DrawingArea.bottomY)
			k1 -= (j + k1) - DrawingArea.bottomY;
		if(i < DrawingArea.topX)
		{
			int l2 = DrawingArea.topX - i;
			l1 -= l2;
			i = DrawingArea.topX;
			j1 += l2;
			i1 += l2;
			j2 += l2;
			i2 += l2;
		}
		if(i + l1 > DrawingArea.bottomX)
		{
			int i3 = (i + l1) - DrawingArea.bottomX;
			l1 -= i3;
			j2 += i3;
			i2 += i3;
		}
		if(!(l1 <= 0 || k1 <= 0))
		{
			method351(j1, l1, DrawingArea.pixels, myPixels, j2, k1, i2, k, i1);
		}
	}

	public void drawSprite(int i, int k)
	{
		i += drawOffsetX;
		k += drawOffsetY;
		int l = i + k * DrawingArea.width;
		int i1 = 0;
		int j1 = myHeight;
		int k1 = myWidth;
		int l1 = DrawingArea.width - k1;
		int i2 = 0;
		if(k < DrawingArea.topY)
		{
			int j2 = DrawingArea.topY - k;
			j1 -= j2;
			k = DrawingArea.topY;
			i1 += j2 * k1;
			l += j2 * DrawingArea.width;
		}
		if(k + j1 > DrawingArea.bottomY)
			j1 -= (k + j1) - DrawingArea.bottomY;
		if(i < DrawingArea.topX)
		{
			int k2 = DrawingArea.topX - i;
			k1 -= k2;
			i = DrawingArea.topX;
			i1 += k2;
			l += k2;
			i2 += k2;
			l1 += k2;
		}
		if(i + k1 > DrawingArea.bottomX)
		{
			int l2 = (i + k1) - DrawingArea.bottomX;
			k1 -= l2;
			i2 += l2;
			l1 += l2;
		}
		if(!(k1 <= 0 || j1 <= 0))
		{
			method349(DrawingArea.pixels, myPixels, i1, l, k1, j1, l1, i2);
		}
	}

	public void drawSprite(int i, int k, int color) {
		int tempWidth = myWidth + 2;
		int tempHeight = myHeight + 2;
		int[] tempArray = new int[tempWidth * tempHeight];
		for(int x = 0; x < myWidth; x++) {
			for(int y = 0; y < myHeight; y++) {
				if(myPixels[x + y * myWidth] != 0)
					tempArray[(x + 1) + (y + 1) * tempWidth] = myPixels[x + y * myWidth];
			}
		}
		for(int x = 0; x < tempWidth; x++) {
			for(int y = 0; y < tempHeight; y++) {
				if(tempArray[(x) + (y) * tempWidth] == 0) {
					if(x < tempWidth - 1 && tempArray[(x + 1) + ((y) * tempWidth)] > 0 && tempArray[(x + 1) + ((y) * tempWidth)] != 0xffffff) {
						tempArray[(x) + (y) * tempWidth] = color;
					}
					if(x > 0 && tempArray[(x - 1) + ((y) * tempWidth)] > 0 && tempArray[(x - 1) + ((y) * tempWidth)] != 0xffffff) {
						tempArray[(x) + (y) * tempWidth] = color;
					}
					if(y < tempHeight - 1 && tempArray[(x) + ((y + 1) * tempWidth)] > 0 && tempArray[(x) + ((y + 1) * tempWidth)] != 0xffffff) {
						tempArray[(x) + (y) * tempWidth] = color;
					}
					if(y > 0 && tempArray[(x) + ((y - 1) * tempWidth)] > 0 && tempArray[(x) + ((y - 1) * tempWidth)] != 0xffffff) {
						tempArray[(x) + (y) * tempWidth] = color;
					}
				}
			}
		}
		i--;
		k--;
		i += drawOffsetX;
		k += drawOffsetY;
		int l = i + k * DrawingArea.width;
		int i1 = 0;
		int j1 = tempHeight;
		int k1 = tempWidth;
		int l1 = DrawingArea.width - k1;
		int i2 = 0;
		if (k < DrawingArea.topY) {
			int j2 = DrawingArea.topY - k;
			j1 -= j2;
			k = DrawingArea.topY;
			i1 += j2 * k1;
			l += j2 * DrawingArea.width;
		}
		if (k + j1 > DrawingArea.bottomY) {
			j1 -= (k + j1) - DrawingArea.bottomY;
		}
		if (i < DrawingArea.topX) {
			int k2 = DrawingArea.topX - i;
			k1 -= k2;
			i = DrawingArea.topX;
			i1 += k2;
			l += k2;
			i2 += k2;
			l1 += k2;
		}
		if (i + k1 > DrawingArea.bottomX) {
			int l2 = (i + k1) - DrawingArea.bottomX;
			k1 -= l2;
			i2 += l2;
			l1 += l2;
		}
		if (!(k1 <= 0 || j1 <= 0)) {
			method349(DrawingArea.pixels, tempArray, i1, l, k1, j1, l1, i2);
		}
	}
	
	
	public void drawSprite2(int i, int j) {
		int k = 225;//was parameter
		i += drawOffsetX;
		j += drawOffsetY;
		int i1 = i + j * DrawingArea.width;
		int j1 = 0;
		int k1 = myHeight;
		int l1 = myWidth;
		int i2 = DrawingArea.width - l1;
		int j2 = 0;
		if(j < DrawingArea.topY) {
			int k2 = DrawingArea.topY - j;
			k1 -= k2;
			j = DrawingArea.topY;
			j1 += k2 * l1;
			i1 += k2 * DrawingArea.width;
		}
		if(j + k1 > DrawingArea.bottomY)
			k1 -= (j + k1) - DrawingArea.bottomY;
		if(i < DrawingArea.topX) {
			int l2 = DrawingArea.topX - i;
			l1 -= l2;
			i = DrawingArea.topX;
			j1 += l2;
			i1 += l2;
			j2 += l2;
			i2 += l2;
		}
		if(i + l1 > DrawingArea.bottomX) {
			int i3 = (i + l1) - DrawingArea.bottomX;
			l1 -= i3;
			j2 += i3;
			i2 += i3;
		}
		if(!(l1 <= 0 || k1 <= 0)) {
			method351(j1, l1, DrawingArea.pixels, myPixels, j2, k1, i2, k, i1);
		}
	}

	private void method349(int ai[], int ai1[], int j, int k, int l, int i1, int j1, int k1) {
		int i;//was parameter
		int l1 = -(l >> 2);
		l = -(l & 3);
		for(int i2 = -i1; i2 < 0; i2++) {
			for(int j2 = l1; j2 < 0; j2++) {
				i = ai1[j++];
				if(i != 0 && i != -1)
				{
					ai[k++] = i;
				} else {
				k++;
			}
			i = ai1[j++];
			if(i != 0 && i != -1) {
				ai[k++] = i;
			} else {
				k++;
			}
			i = ai1[j++];
			if(i != 0 && i != -1) {
				ai[k++] = i;
			} else {
				k++;
			}
			i = ai1[j++];
			if(i != 0 && i != -1) {
				ai[k++] = i;
			} else {
				k++;
			}
		}

		for(int k2 = l; k2 < 0; k2++) {
			i = ai1[j++];
			if(i != 0 && i != -1) {
				ai[k++] = i;
			} else {
				k++;
			}
		}
		k += j1;
		j += k1;
		}
	}

	private void method351(int i, int j, int ai[], int ai1[], int l, int i1,
						   int j1, int k1, int l1)
	{
		int k;//was parameter
		int j2 = 256 - k1;
		for(int k2 = -i1; k2 < 0; k2++)
		{
			for(int l2 = -j; l2 < 0; l2++)
			{
				k = ai1[i++];
				if(k != 0)
				{
					int i3 = ai[l1];
					ai[l1++] = ((k & 0xff00ff) * k1 + (i3 & 0xff00ff) * j2 & 0xff00ff00) + ((k & 0xff00) * k1 + (i3 & 0xff00) * j2 & 0xff0000) >> 8;
				} else
				{
					l1++;
				}
			}

			l1 += j1;
			i += l;
		}
	}

	public void method352(int i, int j, int ai[], int k, int ai1[], int i1,
						  int j1, int k1, int l1, int i2)
	{
		try
		{
			int j2 = -l1 / 2;
			int k2 = -i / 2;
			int l2 = (int)(Math.sin((double)j / 326.11000000000001D) * 65536D);
			int i3 = (int)(Math.cos((double)j / 326.11000000000001D) * 65536D);
			l2 = l2 * k >> 8;
			i3 = i3 * k >> 8;
			int j3 = (i2 << 16) + (k2 * l2 + j2 * i3);
			int k3 = (i1 << 16) + (k2 * i3 - j2 * l2);
			int l3 = k1 + j1 * DrawingArea.width;
			for(j1 = 0; j1 < i; j1++)
			{
				int i4 = ai1[j1];
				int j4 = l3 + i4;
				int k4 = j3 + i3 * i4;
				int l4 = k3 - l2 * i4;
				for(k1 = -ai[j1]; k1 < 0; k1++)
				{
					DrawingArea.pixels[j4++] = myPixels[(k4 >> 16) + (l4 >> 16) * myWidth];
					k4 += i3;
					l4 -= l2;
				}

				j3 += l2;
				k3 += i3;
				l3 += DrawingArea.width;
			}

		}
		catch(Exception _ex)
		{
		}
	}

	public void method353(int i,
						  double d, int l1)
	{
		//all of the following were parameters
		int j = 15;
		int k = 20;
		int l = 15;
		int j1 = 256;
		int k1 = 20;
		//all of the previous were parameters
		try
		{
			int i2 = -k / 2;
			int j2 = -k1 / 2;
			int k2 = (int)(Math.sin(d) * 65536D);
			int l2 = (int)(Math.cos(d) * 65536D);
			k2 = k2 * j1 >> 8;
			l2 = l2 * j1 >> 8;
			int i3 = (l << 16) + (j2 * k2 + i2 * l2);
			int j3 = (j << 16) + (j2 * l2 - i2 * k2);
			int k3 = l1 + i * DrawingArea.width;
			for(i = 0; i < k1; i++)
			{
				int l3 = k3;
				int i4 = i3;
				int j4 = j3;
				for(l1 = -k; l1 < 0; l1++)
				{
					int k4 = myPixels[(i4 >> 16) + (j4 >> 16) * myWidth];
					if(k4 != 0)
						DrawingArea.pixels[l3++] = k4;
					else
						l3++;
					i4 += l2;
					j4 -= k2;
				}

				i3 += k2;
				j3 += l2;
				k3 += DrawingArea.width;
			}

		}
		catch(Exception _ex)
		{
		}
	}

	public void method354(Background background, int i, int j)
	{
		j += drawOffsetX;
		i += drawOffsetY;
		int k = j + i * DrawingArea.width;
		int l = 0;
		int i1 = myHeight;
		int j1 = myWidth;
		int k1 = DrawingArea.width - j1;
		int l1 = 0;
		if(i < DrawingArea.topY)
		{
			int i2 = DrawingArea.topY - i;
			i1 -= i2;
			i = DrawingArea.topY;
			l += i2 * j1;
			k += i2 * DrawingArea.width;
		}
		if(i + i1 > DrawingArea.bottomY)
			i1 -= (i + i1) - DrawingArea.bottomY;
		if(j < DrawingArea.topX)
		{
			int j2 = DrawingArea.topX - j;
			j1 -= j2;
			j = DrawingArea.topX;
			l += j2;
			k += j2;
			l1 += j2;
			k1 += j2;
		}
		if(j + j1 > DrawingArea.bottomX)
		{
			int k2 = (j + j1) - DrawingArea.bottomX;
			j1 -= k2;
			l1 += k2;
			k1 += k2;
		}
		if(!(j1 <= 0 || i1 <= 0))
		{
			method355(myPixels, j1, background.aByteArray1450, i1, DrawingArea.pixels, 0, k1, k, l1, l);
		}
	}

	private void method355(int ai[], int i, byte abyte0[], int j, int ai1[], int k,
						   int l, int i1, int j1, int k1)
	{
		int l1 = -(i >> 2);
		i = -(i & 3);
		for(int j2 = -j; j2 < 0; j2++)
		{
			for(int k2 = l1; k2 < 0; k2++)
			{
				k = ai[k1++];
				if(k != 0 && abyte0[i1] == 0)
					ai1[i1++] = k;
				else
					i1++;
				k = ai[k1++];
				if(k != 0 && abyte0[i1] == 0)
					ai1[i1++] = k;
				else
					i1++;
				k = ai[k1++];
				if(k != 0 && abyte0[i1] == 0)
					ai1[i1++] = k;
				else
					i1++;
				k = ai[k1++];
				if(k != 0 && abyte0[i1] == 0)
					ai1[i1++] = k;
				else
					i1++;
			}

			for(int l2 = i; l2 < 0; l2++)
			{
				k = ai[k1++];
				if(k != 0 && abyte0[i1] == 0)
					ai1[i1++] = k;
				else
					i1++;
			}

			i1 += l;
			k1 += j1;
		}

	}
	
	public void drawAdvancedSprite(int i, int j) {
		int k = 256;
		i += anInt1444;
		j += anInt1445;
		int i1 = i + j * DrawingArea.width;
		int j1 = 0;
		int k1 = myHeight;
		int l1 = myWidth;
		int i2 = DrawingArea.width - l1;
		int j2 = 0;
		if (j < DrawingArea.topY) {
			int k2 = DrawingArea.topY - j;
			k1 -= k2;
			j = DrawingArea.topY;
			j1 += k2 * l1;
			i1 += k2 * DrawingArea.width;
		}
		if (j + k1 > DrawingArea.bottomY)
			k1 -= (j + k1) - DrawingArea.bottomY;
		if (i < DrawingArea.topX) {
			int l2 = DrawingArea.topX - i;
			l1 -= l2;
			i = DrawingArea.topX;
			j1 += l2;
			i1 += l2;
			j2 += l2;
			i2 += l2;
		}
		if (i + l1 > DrawingArea.bottomX) {
			int i3 = (i + l1) - DrawingArea.bottomX;
			l1 -= i3;
			j2 += i3;
			i2 += i3;
		}
		if (!(l1 <= 0 || k1 <= 0)) {
			drawAlphaSprite(j1, l1, DrawingArea.pixels, myPixels, j2, k1, i2,
					k, i1);
		}
	}
	
	private void drawAlphaSprite(int i, int j, int ai[], int ai1[], int l,
			int i1, int j1, int k1, int l1) {
		int k;// was parameter
		int j2;
		for (int k2 = -i1; k2 < 0; k2++) {
			for (int l2 = -j; l2 < 0; l2++) {
				k1 = ((myPixels[i] >> 24) & 255);
				j2 = 256 - k1;
				k = ai1[i++];
				if (k != 0) {
					int i3 = ai[l1];
					ai[l1++] = ((k & 0xff00ff) * k1 + (i3 & 0xff00ff) * j2 & 0xff00ff00)
							+ ((k & 0xff00) * k1 + (i3 & 0xff00) * j2 & 0xff0000) >> 8;
				} else {
					l1++;
				}
			}

			l1 += j1;
			i += l;
		}
	}
	
	private final byte[] EMPTY = {
		-119, 80, 78, 71, 13, 10, 26, 10, 0, 0, 0, 13, 73, 
		72, 68, 82, 0, 0, 0, 27, 0, 0, 0, 27, 8, 6, 0, 0, 0, -115,
		-44, -12, 85, 0, 0, 0, 1, 115, 82, 71, 66, 0, -82, -50, 28,
		-23, 0, 0, 0, 4, 103, 65, 77, 65, 0, 0, -79, -113, 11, -4, 
		97, 5, 0, 0, 0, 32, 99, 72, 82, 77, 0, 0, 122, 38, 0, 0, 
		-128, -124, 0, 0, -6, 0, 0, 0, -128, -24, 0, 0, 117, 48, 
		0, 0, -22, 96, 0, 0, 58, -104, 0, 0, 23, 112, -100, -70, 
		81, 60, 0, 0, 0, 9, 112, 72, 89, 115, 0, 0, 14, -60, 0, 
		0, 14, -60, 1, -107, 43, 14, 27, 0, 0, 2, 2, 73, 68, 65, 
		84, 72, 75, -91, 86, -63, 74, -61, 64, 16, 77, 82, -102, 
		-76, 32, -6, 1, -118, 39, 15, 69, -20, 73, 111, 122, -13, 
		119, 61, -118, 55, 111, -98, -84, -126, -46, -117, 7, 65,
		16, 127, 64, 17, 108, 90, -38, -24, -37, -8, -30, 100, 50,
		-69, -37, -30, 66, -39, 116, 54, 51, 111, -34, -101, -39,
		-35, -92, 85, 82, 85, 73, 100, 28, -113, -9, 99, -81, 36,
		-9, -45, -41, -24, 59, 105, 8, 108, 60, -38, -13, 6, -56,
		-5, -103, 91, -101, 47, 86, 110, -58, -1, 94, -42, 75, 110,
		31, 95, -68, 62, 38, 88, 8, -124, -127, 49, 35, -8, 114, -75,
		116, 51, 71, -34, -17, -3, 36, -80, 52, 65, -21, -12, -60,
		-112, 64, -56, -106, 12, -84, 116, 1, 36, 7, -128, 56, -50,
		78, 14, 58, 46, 45, 48, 13, 4, -119, 40, -109, -12, -44, 54,
		11, 20, -20, 52, 96, 35, -93, 5, -28, 19, 95, -77, -123, -116,
		-110, 85, 81, -28, -50, -75, 44, -25, 110, -66, -71, 123, 118,
		-77, 99, -10, 95, 32, -103, 20, -127, 96, -29, 51, 25, -74, 100,
		12, -43, 7, -50, 62, 70, -110, -107, 79, 13, -40, -45, -93, -47,
		110, 116, -97, -123, -128, 116, 112, -55, -116, 107, -112, 19, 118,
		-57, 76, 102, 108, -79, 11, -43, 8, 65, -28, -113, 0, -61, 65, -98,
		-32, 39, -27, 116, 96, -24, 46, -67, 73, -103, -124, 13, 94, -73,
		-72, -59, 2, 118, -126, -24, -25, -50, 62, 99, 102, 22, 8, -70,
		110, -8, -37, 105, -21, 0, 73, -106, 77, 55, -110, 93, -88, -72,
		-78, -67, 53, 16, 37, -109, -116, 16, 107, 80, -12, -35, -113,
		-93, 97, -58, -45, -62, 58, 53, -12, 62, -78, -22, 18, 74, 20,
		-128, 72, -92, -45, 32, -38, -55, -73, 97, 99, -63, 37, 35, -78,
		-12, -42, 12, 47, -124, -128, -76, 100, 33, 112, -84, -51, -54,
		69, -51, -116, -127, -27, -23, -83, -99, 101, -99, 66, 64, 100,
		-108, 102, 69, 43, 4, -20, 13, -104, 62, 76, 37, -80, -81, -13,
		116, 66, 90, 58, -67, -34, -110, 49, 116, 101, 108, 90, -93, 106,
		85, 118, 92, -78, -23, -45, 91, -57, -72, 9, -85, 16, -101, -19, 
		-83, -99, 38, -10, -59, -43, -28, 79, 70, 89, 59, 60, -29, 112, 
		93, 87, 62, 31, -21, -113, -49, -9, -42, 82, 115, -97, -15, -93, 
		-122, 29, 104, 1, -55, -58, -120, -43, 71, -94, -128, 21, 70, 
		-21, 27, 4, -128, -42, 113, 100, -99, 12, -79, 86, 103, -69, 
		95, 94, 63, 52, -81, -74, 26, -124, -97, 99, 96, -59, 91, 118, 
		-45, -3, -60, -56, -40, 87, 18, 8, -10, -50, -90, -26, 21, 78, 
		25, -65, 102, -11, -43, -50, -79, -114, 124, 22, 80, 71, 70, 
		45, -51, -7, -23, -95, 51, -7, -40, 89, -64, -84, -113, 37, 
		115, -16, 35, -107, 14, 33, 80, 0, -6, -104, 104, -64, 111, 
		-60, 87, -104, -24, 65, 38, 115, 44, 0, 0, 0, 0, 73, 69, 78, 
		68, -82, 66, 96, -126
	};
				
	private static final byte[] HITPOINTS = {
		-119, 80, 78, 71, 13, 10, 26, 10, 0,
		0, 0, 13, 73, 72, 68, 82, 0, 0, 0, 56, 0, 0, 0, 7, 8, 6, 0,
		0, 0, 93, -86, -114, 102, 0, 0, 0, 4, 103, 65, 77, 65, 0, 0,
		-79, -113, 11, -4, 97, 5, 0, 0, 1, 121, 73, 68, 65, 84, 56,
		79, -35, -108, -53, 43, -124, 81, 24, -121, -33, 47, -111,
		-107, 13, 54, 44, 40, 74, -92, 44, -58, 102, 74, 86, 108, 44,
		101, -93, -79, 16, 43, -39, -38, 88, 88, -79, -103, -115,
		-110, -90, -122, 70,-115, 68, -109, 88, -111, 68, -55, 37,
		-105, -56, -62, 2, -111, -1, -27, -25, -3, 57, -34, 111, 
		-50, 124, -58, 92, -54, -54, 87, 79, -17, -71, 125, -25, 
		-100, -25, -36, 2, -24, 23, -12, 4, -14, 31, 63, -68, 64,
		-75, -70, 5, 100, -26, 68, 48, 126, 47, 72, 92, -28, -103,
		58, 21, 84, -62, -28, -95, -128, 76, 28, 56, 70, -9, 11,
		25, -39, 22, 68, 25, -42, 50, 18, -49, 10, 98, -103, 60,
		29, 41, 65, -108, -42, 85, 65, -75, -48, 105, -96, -66, 
		70, 13, 53, -79, -8, -24, -40, 122, 22,-28, 62, -54, -109,
		125, 23, 24, 27, 111, 2, 35, -3, 42, 72, 62, -3, -60, -6, -9,
		-29, -126, 46, 102, 41, -40, -74, 88, -3, -20, -107, 32, 74,
		-30, 76, 55, -59, -93, 97, 93, 64, 66, -63, -91, 27, 39, -73,
		-87, -99, 102, 30, 28, 76, -5, 121, 43, 103, 76, -35, 9, -8,
		15, 99, -14, -42, -91, -25, -81, 5, 115, 58, 56, -79, 9, 76,
		95, 10, 12, -1, 100, 48, 61, 118, 94, -56, -112, -98, 22, -97,
		-63, 99, 1, -119, 31, 9, -6, -11, 116, -112, 94, 61, 29, -107,
		-64, -74, -75, -53, 17, 65, -82, -104, 73, -3, 38, 102, -110,
		-108, 42, 38, 99, 98, 86, 71, 57, 19, -15, 5, 125, 57, -109,
		50, -95, 104, -84, 70, -80, 115, 79, -48, -68, 43, -88, 91,
		113, -124, 59, 40, 93, 110, -62, -100, 16, 7, -120, -23, 125,
		52, -6, 52, 95, -55, -86, -79, 115, -46, -106, 83, 118, 4,
		-19, 74, -53, 90, 105, 26, -45, 2, -125, 119, -84, 73, -17,
		95, -79, -69, -58, 114, -65, -83, 29, 65, 19, 97, -28, -114,
		25, -52, -121, 119, -112, -81, -24, -41, 67, -93, -110, 33,
		-47, -68, 95, 87, 109, -70, 92, 95, 86, -1, -3, -40, -39,
		-93, -9, 23, -111, 110, -97, -128, 46, 123, -112, -108, -7,
		117, -5, 0, 0, 0, 0, 73, 69, 78, 68, -82, 66, 96, -126
	};	

	public int myPixels[];
	public int myWidth;
	public int myHeight;
	public int drawOffsetX;
	public int drawOffsetY;
	public int anInt1444;
	public int anInt1445;
}
