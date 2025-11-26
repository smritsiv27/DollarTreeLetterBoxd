/*
Smriti Siva
DollarTree Letterboxd Final Project
8/2/24
*/
import javax.swing.*;
/**
*This class shortens the amount of code necessary to create JTextAreas and JScrollPanes
*/
public class GUIHelper {
   /**
   *creates a JTextArea based off of information passed in
   *@param rows num of rows
   *@param cols num of columns
   *@param editable whether you can edit the text or not
   *@param txt the string text
   *@return JTextArea
   */
   public static JTextArea getTextArea(int rows, int cols, boolean editable, String txt) {
      JTextArea ta = new JTextArea(rows, cols);
      ta.setText(txt);
      ta.setWrapStyleWord(true);
      ta.setLineWrap(true);
      ta.setEditable(editable);
      ta.setFocusable(editable);
      ta.setOpaque(editable);
      return ta;
   }
   /**
   *creates a JScrollPane based off of information passed in
   *@param rows num of rows
   *@param cols num of columns
   *@param editable whether you can edit the text or not
   *@param txt txt the string text
   *@return JScrollPane
   */
   public static JScrollPane getScrollTextArea(int rows, int cols, boolean editable, String txt){
      JTextArea ta = getTextArea(rows, cols, editable, txt);
      JScrollPane jsp = new JScrollPane(ta);
      jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      return jsp;
   }
}
