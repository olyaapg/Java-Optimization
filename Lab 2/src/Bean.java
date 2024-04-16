import java.util.ArrayList;
import java.util.List;

public class Bean {

  private byte[] bytes = new byte[10];
  private List<Integer> list = new ArrayList<>();
  private int intVar = 0;
  private long longVar = 1L;
  private char charVar = ' ';
  private boolean boolVar = false;
  private Bean bean;

  public byte[] getBytes() {
    return bytes;
  }

  public void setBytes(byte[] bytes) {
    this.bytes = bytes;
  }

  public List<Integer> getList() {
    return list;
  }

  public void setList(List<Integer> list) {
    this.list = list;
  }

  public int getIntVar() {
    return intVar;
  }

  public void setIntVar(int intVar) {
    this.intVar = intVar;
  }

  public char getCharVar() {
    return charVar;
  }

  public void setCharVar(char charVar) {
    this.charVar = charVar;
  }

  public boolean isBoolVar() {
    return boolVar;
  }

  public void setBoolVar(boolean boolVar) {
    this.boolVar = boolVar;
  }

  public long getLongVar() {
    return longVar;
  }

  public void setLongVar(long longVar) {
    this.longVar = longVar;
  }

  public Bean getBean() {
    return bean;
  }

  public void setBean(Bean bean) {
    this.bean = bean;
  }
}
