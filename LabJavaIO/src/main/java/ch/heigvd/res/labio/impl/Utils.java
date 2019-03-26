package ch.heigvd.res.labio.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 */
public class Utils {

  private static final Logger LOG = Logger.getLogger(Utils.class.getName());

  /**
   * This method looks for the next new line separators (\r, \n, \r\n) to extract
   * the next line in the string passed in arguments. 
   * 
   * @param lines a string that may contain 0, 1 or more lines
   * @return an array with 2 elements; the first element is the next line with
   * the line separator, the second element is the remaining text. If the argument does not
   * contain any line separator, then the first element is an empty string.
   */
  public static String[] getNextLine(String lines) {
    String[] resultat = {null, null};
    String [] separtor = {"\r", "\n", "\n\r"};
    int indexe;
    for(int i=0; i<separtor.length; i++){
      indexe = lines.indexOf(separtor[i]);
      if (indexe!=-1){
          resultat[0] = lines.substring(0, indexe + separtor[i].length());
          resultat[1] = lines.substring(indexe + separtor[i].length());

        return resultat;
      }
    }
    resultat[0]= "";
    resultat[1]= lines;
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    return resultat;
  }

}
