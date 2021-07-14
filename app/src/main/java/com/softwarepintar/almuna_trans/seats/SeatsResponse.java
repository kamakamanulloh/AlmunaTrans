package com.softwarepintar.almuna_trans.seats;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SeatsResponse{

	@SerializedName("resultD")
	private List<ResultDItem> resultD;

	@SerializedName("value")
	private int value;

	@SerializedName("resultC")
	private List<ResultCItem> resultC;

	@SerializedName("resultB")
	private List<ResultBItem> resultB;

	@SerializedName("resultA")
	private List<ResultAItem> resultA;

	public void setResultD(List<ResultDItem> resultD){
		this.resultD = resultD;
	}

	public List<ResultDItem> getResultD(){
		return resultD;
	}

	public void setValue(int value){
		this.value = value;
	}

	public int getValue(){
		return value;
	}

	public void setResultC(List<ResultCItem> resultC){
		this.resultC = resultC;
	}

	public List<ResultCItem> getResultC(){
		return resultC;
	}

	public void setResultB(List<ResultBItem> resultB){
		this.resultB = resultB;
	}

	public List<ResultBItem> getResultB(){
		return resultB;
	}

	public void setResultA(List<ResultAItem> resultA){
		this.resultA = resultA;
	}

	public List<ResultAItem> getResultA(){
		return resultA;
	}

	@Override
 	public String toString(){
		return 
			"SeatsResponse{" + 
			"resultD = '" + resultD + '\'' + 
			",value = '" + value + '\'' + 
			",resultC = '" + resultC + '\'' + 
			",resultB = '" + resultB + '\'' + 
			",resultA = '" + resultA + '\'' + 
			"}";
		}
}