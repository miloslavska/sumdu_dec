import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.geom.AffineTransform;
import javax.swing.Timer;

public class TitlesPanel extends javax.swing.JPanel implements java.awt.event.ActionListener
{
  private Graphics2D g2d;
  private final Timer animation; // field animation can be final
  private boolean is_done;
  private int start_angle = 0;
  private final int shape; // field animation can be final
  
  /**
   * Конструктор класса
     * @param _shape
   */
  public TitlesPanel(int _shape) {
    this.is_done = true;
    this.shape = _shape;
    this.animation = new Timer(50, this);
    this.animation.setInitialDelay(50);
    this.animation.start();
  }
  

  @Override // Добавлена аннотация
  public void actionPerformed(java.awt.event.ActionEvent arg0)
  {
    if (this.is_done) {
      repaint();
    }
  }
  
  /**
   * Выполняет трансформацию графического объекта (вращение)
   */
  private void doDrawing(Graphics g) {
    this.is_done = false;
    this.g2d = ((Graphics2D)g);
    this.g2d.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, 
      java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
    
    java.awt.Dimension size = getSize();
    Insets insets = getInsets();
    
    int w = size.width - insets.left - insets.right;
    int h = size.height - insets.top - insets.bottom;
    
    ShapeFactory localShape = new ShapeFactory(this.shape); // переменная shape переименована в localShape
    this.g2d.setStroke(localShape.stroke);
    this.g2d.setPaint(localShape.paint);
    double angle = this.start_angle++;
    if (this.start_angle > 360) this.start_angle = 0;
    double dr = 90.0D / (w / (localShape.width * 1.5D));
    for (int j = localShape.height; j < h; j = (int)(j + localShape.height * 1.5D))
      for (int i = localShape.width; i < w; i = (int)(i + localShape.width * 1.5D)) {
        angle = angle > 360.0D ? 0.0D : angle + dr;
        AffineTransform transform = new AffineTransform();
        transform.translate(i, j);
        transform.rotate(Math.toRadians(angle));
        this.g2d.draw(transform.createTransformedShape(localShape.shape));
      }
    this.is_done = true;
  }
  
  @Override // добавлена аннотация
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    doDrawing(g);
  }
}