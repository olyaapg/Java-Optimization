import java.util.ArrayList;
import java.util.List;

public class Bean {

    private int intVar = 0;
    private int[] intArrayVar = new int[10];
    private long longVar = 1L;
    private char charVar = ' ';
    private char[] charArrayVar;
    private boolean boolVar = false;
    private String string = "";
    private List<Integer> listVar = new ArrayList<>(10);
    private Bean bean;

    @Override
    public String toString() {
        return intVar + " " + longVar + " " + charVar + " " + boolVar + " " + string;
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

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

  public int[] getIntArrayVar() {
    return intArrayVar;
  }

  public void setIntArrayVar(int[] intArrayVar) {
    this.intArrayVar = intArrayVar;
  }

  public char[] getCharArrayVar() {
    return charArrayVar;
  }

  public void setCharArrayVar(char[] charArrayVar) {
    this.charArrayVar = charArrayVar;
  }

  public List<Integer> getListVar() {
    return listVar;
  }

  public void setListVar(List<Integer> listVar) {
    this.listVar = listVar;
  }
}
