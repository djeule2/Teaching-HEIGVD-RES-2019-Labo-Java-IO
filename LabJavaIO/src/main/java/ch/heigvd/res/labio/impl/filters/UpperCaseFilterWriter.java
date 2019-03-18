package ch.heigvd.res.labio.impl.filters;

import java.io.*;

/**
 *
 * @author Olivier Liechti
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
      super.write(str.toUpperCase(), off, len);

    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
      String cbufconvert = new String(cbuf);
      super.write(cbufconvert.toUpperCase(), off, len);
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

  @Override
  public void write(int c) throws IOException {
      super.write(Character.toUpperCase((char)c));

   // throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

}
