import java.awt.image.BufferedImage;

public class ImageProcess {
	private BufferedImage image;
    private double perimeter;
    private int pointsNumber;

    public ImageProcess(BufferedImage image) {
    	this.image = image;
    	this.perimeter = 0;
    	this.pointsNumber = 0;
    }
    
    public BufferedImage getImage() {
    	return this.image;
    }
    
    /*
     * Localizar ponto "inicial" do objeto
     */
    
    public int[] getInitialPoint() {
    	boolean aux = false;
    	int[] init = new int[2];
    	
    	for (int y = 0; y < image.getHeight(); y++) {
    		for (int x = 0; x < image.getWidth(); x++) {
            	
                if (image.getRGB(x, y) != -1 && !(aux)) {
                    
                	init[0] = x;
                    init[1] = y;
                    aux = true;
                    
                    return init;
                }
            }
        }
        
		return init;
    }
    
    /*
     * Achar largura do objeto
     */
    
    public int getObjectWidth() {
    	int count = 0;
    	int objectWidth = 0;
    	
    	for (int y = 0; y < image.getHeight(); y++) {
    		for (int x = 0; x < image.getWidth(); x++) {
            	if (image.getRGB(x, y) != -1) {
            		count++;
            	} else {
            		if (count > objectWidth) {
						objectWidth = count;
					}
            		count = 0;
            	}	
            }
        }
		return objectWidth;
    }
    
    /*
     * Achar altura do objeto
     */
    
    public int getObjectHeight() {
    	int count = 0;
    	int objectHeight = 0;
    	
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
            	
            	if (image.getRGB(x, y) != -1) {
            		count++;
            	} else {
            		if (count > objectHeight) {
						objectHeight = count;
					}
            		count = 0;
            	}	
            }
        }
		return objectHeight;
    }
    
    public double chainCodes(int op) {
    	int[][] visited = new int[image.getHeight()][image.getWidth()];
    	int[] initialPoint = getInitialPoint();
    	
    	this.perimeter = 0;
    	this.pointsNumber = 0;
    	
    	chainCodesAux(image, visited, initialPoint[0]-1, initialPoint[1]-1);
    	
    	if (op == 0) // Calcula o número de pontos da borda
    		return (double) this.pointsNumber;
    	else if (op == 1) // Tamanho da borda
    		return this.perimeter;
    	else
    		return -1;
    }
    
    /*
     * Calcula o número de pontos da borda do objeto
     */
    private void chainCodesAux(BufferedImage image, int[][] visited, int x, int y) {
    	int[] point = new int[2];
    	
    	point = nextPixels(image, visited, x, y);
    	
    	visited[y][x] = 1;
    	if(visited[point[1]][point[0]] == 0) {
    		chainCodesAux(image, visited, point[0], point[1]);
    	} else {
    		System.out.println();
    	}
    }

	private int[] nextPixels(BufferedImage image, int[][] visited, int x, int y) {
		int[] point = new int[2];
		boolean aux = false;
		int[][] sides = {{0,1}, {1,1}, {1,0}, {1,-1}, {0,-1}, {-1,-1}, {-1,0}, {-1,1}};
		
		for (int[] p : sides) {
			int newX = x+p[0];
			int newY = y+p[1];
			
			if (newY*newX >= 0 && border(image, newX, newY) && !aux && visited[newY][newX] == 0) {
				aux = true;
				point[0] = newX;
				point[1] = newY;
				pointsNumber++;
				
				perimeter += (p[0]*p[1] == 0)
						? 1 // if not diagonal
						: Math.sqrt(2); // else 
				
				return point;
			}
		}
		
		point[0] = x;
		point[1] = y;
		return point;
	}

//172 52
	private boolean border(BufferedImage image, int x, int y) {
		if (image.getRGB(x, y) == -1) return false;
		
		// Verifica esquerda
		if (y == 0) return true;
		if (y > 0) {
			if(image.getRGB(x-1, y) == -1) return true;
		}
		
		// Verifica topo
		if (x == 0) return true;
		if (x > 0) {
			if (image.getRGB(x, y-1) == -1) return true;
		}
		
		// Verifica direita
		if (y == image.getHeight()) return true;
		if (y < image.getHeight()) {
			if (image.getRGB(x+1, y) == -1) return true;
		}
		
		// Verifica embaixo
		if (x == image.getWidth()) return true;
		if (x < image.getWidth()) {
			if (image.getRGB(x, y+1) == -1) return true;
		}
		
	return false;
	}
}
