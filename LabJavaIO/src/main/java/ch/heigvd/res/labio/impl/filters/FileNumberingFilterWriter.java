package ch.heigvd.res.labio.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 *
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 *
 */

public class FileNumberingFilterWriter extends FilterWriter {
  boolean debufichier = true;
  int numeroligne=1;
  char charprecedent = ' ';

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    String debuligne = "";
    int i = off;
    if(debufichier){
      debuligne="1\t";
      debufichier = false;
    }
    while (i<(off+len)){
      if(str.charAt(i)=='\r') {
        if (str.charAt(i + 1) == '\n') {
          debuligne = debuligne + str.charAt(i);
          debuligne = debuligne + str.charAt(++i) + ++numeroligne + "\t";
        } else {
          debuligne = debuligne + str.charAt(i) + ++numeroligne + "\t";
        }
      }else if(str.charAt(i)=='\n'){
        debuligne = debuligne + str.charAt(i) + ++numeroligne + "\t";

      }else {
        debuligne = debuligne + str.charAt(i);

      }
      i++;

    }
    super.write(debuligne, 0, debuligne.length());
    super.flush();


    // throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }


  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {

    String str = new String(cbuf);
    write(str, off, len);

    // throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

  @Override
  public void write(int c) throws IOException {
    String debutligne = "";
    if(debufichier){
      debutligne = "1\t";
      super.write(debutligne, 0, debutligne.length());
      super.write(c);
      debufichier = false;
    }else if (Character.toTitleCase(c)=='\n' && charprecedent !='\r'){
      super.write(c);
      numeroligne +=1;
      debutligne =debutligne+ numeroligne+'\t';
      super.write(debutligne, 0, debutligne.length());

    }
    else if(Character.toTitleCase(c)!='\n'&& charprecedent =='\r'){
      numeroligne += 1;
      debutligne = debutligne+numeroligne+'\t';
      super.write(debutligne, 0, debutligne.length());
      super.write(c);
      charprecedent = ' ';

    }
    else if (Character.toTitleCase(c)=='\n'&&charprecedent=='\r'){

      super.write(c);
      numeroligne+=1;
      debutligne=debutligne+numeroligne+'\t';
      super.write(debutligne, 0, debutligne.length());
      charprecedent=' ';
    }
    else
      super.write(c);

    if (Character.toTitleCase(c)=='\r'){
      charprecedent = '\r';
    }


    // throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

}