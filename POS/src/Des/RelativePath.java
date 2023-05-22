package Des;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RelativePath {
    public static void main(String[] args) {
    	  String absolutePath = "/POS/src/Des/Assets/icons8-cashier-23.png";
          String referencePoint = "/POS/src";
          
          Path absolute = Paths.get(absolutePath);
          Path reference = Paths.get(referencePoint);
          
          Path relativePath = reference.relativize(absolute);
          
          System.out.println(relativePath);
    }
}