package representation;
import java.util.*;

public class BinaryTuple{
  Object val1;
  Object val2;
  public BinaryTuple(Object val1, Object val2){
    this.val1 = val1;
    this.val2 = val2;
  }


  public Object getVal1(){
    return this.val1;
  }
  public Object getVal2(){
    return this.val2;
  }
  @Override
  public boolean equals(Object obj){
    if(this == obj){
      return true;
    }
    else if(obj instanceof BinaryTuple){
      BinaryTuple newtup = (BinaryTuple)obj;
      return newtup.val1.equals(this.val1) && newtup.val2.equals(this.val2);
    }
    return false;
  }
  @Override
  public int hashCode(){
    return Objects.hash(val1,val2);
  }

}
