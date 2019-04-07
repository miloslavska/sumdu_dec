import java.awt.Point;
import java.awt.geom.GeneralPath;

public class ShapeFactory
{
  public java.awt.Shape shape;
  public java.awt.BasicStroke stroke;
  public java.awt.Paint paint;
  public int width = 25;
  public int height = 25;
  
  public ShapeFactory(int shape_type) {
    this.stroke = new java.awt.BasicStroke(3.0F);
    switch (shape_type / 10) {
    case 1: 
      this.shape = createStar(3, new Point(0, 0), this.width / 2.0D, this.width / 2.0D);
      break;
    case 3: 
      this.shape = createStar(5, new Point(0, 0), this.width / 2.0D, this.width / 4.0D);
      break;
    case 5: 
      this.shape = new java.awt.geom.Rectangle2D.Double(-this.width / 2.0D, -this.height / 2.0D, this.width, this.height);
      break;
    case 7: 
      GeneralPath path = new GeneralPath();
      double tmp_height = Math.sqrt(2.0D) / 2.0D * this.height;
      path.moveTo(-this.width / 2, -tmp_height);
      path.lineTo(0.0D, -tmp_height);
      path.lineTo(this.width / 2, tmp_height);
      path.closePath();
      this.shape = path;
      break;
    case 9: 
      this.shape = new java.awt.geom.Arc2D.Double(-this.width / 2.0D, -this.height / 2.0D, this.width, this.height, 30.0D, 300.0D, 
        2);
      break;
    case 2: case 4: case 6: 
    case 8: default: 
      throw new Error("type is nusupported");
    }
    switch (shape_type % 10) {
    case 1:  this.stroke = new java.awt.BasicStroke(3.0F);
      break;
    case 3: 
      break;
    case 4:  this.stroke = new java.awt.BasicStroke(7.0F);
      break;
    case 7: 
      this.paint = new java.awt.GradientPaint(
        -this.width, -this.height, java.awt.Color.white, 
        this.width, this.height, java.awt.Color.gray, true);
      break;
    case 8: 
      this.paint = java.awt.Color.red;
      break;
    case 2: case 5: case 6: default: 
      throw new Error("type is nusupported");
    }
  }
  
  private static java.awt.Shape createStar(int arms, Point center, double rOuter, double rInner)
  {
    double angle = 3.141592653589793D / arms;
    
    GeneralPath path = new GeneralPath();
    
    for (int i = 0; i < 2 * arms; i++)
    {
      double r = (i & 0x1) == 0 ? rOuter : rInner;
      java.awt.geom.Point2D.Double p = new java.awt.geom.Point2D.Double(center.x + Math.cos(i * angle) * r, center.y + Math.sin(i * angle) * r);
      if (i == 0) path.moveTo(p.getX(), p.getY()); else
        path.lineTo(p.getX(), p.getY());
    }
    path.closePath();
    return path;
  }
}