import javax.swing.JFrame;

public class TitlesFrame extends JFrame
{
  /**
   * Конструктор класса
   */
  public TitlesFrame()
  {
    initUI();
  }
  
  /**
   * Инициализирует графический интерфейс
   */
  private void initUI()
  {
    setTitle("Кривые фигуры");
    setDefaultCloseOperation(3);
    add(new TitlesPanel(57));
    setSize(350, 350);
    setLocationRelativeTo(null);
  }
  
  public static void main(String[] args)
  {
    javax.swing.SwingUtilities.invokeLater(() -> { // использован лямбда синтаксис
        TitlesFrame ps = new TitlesFrame();
        ps.setVisible(true);
    });
  }
}