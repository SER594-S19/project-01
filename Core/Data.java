package Core;

import com.sun.jmx.snmp.Timestamp;

import Model.AffectiveData;
import Model.ExpressionData;
import View.Affective;

/**
 * This class encapsulates a timestamp for a row of data (one entry per channel)
 *
 * @author javiergs
 * @version 20190130
 */
public class Data {

    private double time;
    // representing 14 channels (raw data) of bci
    private double[] values=new double[14];
//    private Expressions expressions;
    private AffectiveData affective;
    private ExpressionData expressive;
    
    public AffectiveData getAffective() {
		return affective;
	}

	public void setAffective(AffectiveData affective) {
		this.affective = affective;
	}
	
	public ExpressionData getExpressive() {
		return expressive;
	}

	public void setExpressive(ExpressionData expressive) {
		this.expressive = expressive;
	}
	
	public Data(double time, double[] values,AffectiveData affectiveData) {
        this.time = time; 
        this.values = values;
        this.affective=affectiveData;
    }

    public void setValues(double[] data) {
      
      values=data;
    }
    public void setTimeStamp(double time) {
    	this.time=time;
    }

  @Override
    //  public String toString() {
    //    return "Data{" + "time=" + time + ", value=" + value + '}';
    //  }
  public String toString() {
        String data;
        data = "time ="+time+"\n";
        for(int i=0;i<14;i++) {
            String ch=i+1+"";
            data+="Channel "+ch+": "+values[i]+"\n";
        }
        data+="frustration: "+affective.getFrustration()+"\n";
        data+="engagement: "+affective.getEngagement()+"\n";
        data+="meditation: "+affective.getMeditation()+"\n";
        data+="short term engagement: "+affective.getStengagement()+"\n";
        data+="long term engagement: "+affective.getLtengagement()+"\n";
        data+= "blink"+ expressive.getBlink()+"\n";
        data+= "smirkLeft" + expressive.getSmirkLeft()+"\n";
        data+= "smirkRight "+ expressive.getSmirkRight()+"\n";
        return data;
      //return "Data{" + "time=" + time + ", value=" + value + '}';
  }


}
