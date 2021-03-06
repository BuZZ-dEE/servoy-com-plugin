package com.servoyguy.plugins.servoycom;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.jacob.com.Variant;

public class JSVariant implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String stringValue;
	private boolean isString = false;
	private double doubleValue;
	private boolean isDouble = false;
	private int intValue;
	private boolean isInt = false;
	private boolean booleanValue;
	private boolean isBoolean = false;
	private double dateValue;
	private boolean isDate = false;
	private boolean isNull = false;
	
	public JSVariant(final Object o){
		
		//	should always cast : put in here for reflection
		final Variant v = (Variant)o;
		
		if(v.isNull()){
			isNull = true;
			return;
		}
		
		try{
			stringValue = v.toString();
			isString = true;
		}catch(final Throwable t){}
		
		try{
			doubleValue = v.changeType(Variant.VariantDouble).getDouble();
			isDouble = true;
		}catch(final Throwable t){}
		
		try{
			intValue = v.changeType(Variant.VariantInt).getInt();
			isInt = true;
		}catch(final Throwable t){}
		
		try{
			booleanValue = v.changeType(Variant.VariantBoolean).getBoolean();
			isBoolean = true;
		}catch(final Throwable t){}
		
		try{
			dateValue = v.changeType(Variant.VariantDate).getDate();
			isDate = true;
		}catch(final Throwable t){}
	}

	public String getString() {
		if(isString){
			return stringValue;
		}else {
			throw new IllegalStateException("Cannot convert result to string");
		}
	}

	public double getDouble() {
		if(isDouble){
			return doubleValue;
		}else {
			throw new IllegalStateException("Cannot convert result to double");
		}
	}

	public int getInt() {
		if(isInt){
			return intValue;
		}else {
			throw new IllegalStateException("Cannot convert result to int");
		}
	}
	
	public double getDate() {
		if(isDate){
			return dateValue;
		}else {
			throw new IllegalStateException("Cannot convert result to date/double");
		}
	}

	public Date getFormattedDate(){
		SimpleDateFormat sdf = new SimpleDateFormat( "EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH );
		try 
		{
			return sdf.parse( stringValue );
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	public boolean isNull() {
		return isNull;
	}

	public boolean getBoolean() {
		if(isBoolean){
			return booleanValue;
		}else {
			throw new IllegalStateException("Cannot convert result to boolean");
		}
	}
	
	public String toString() {
		if (!isNull) {
			if (isString) {
				return stringValue;
			} else if (isInt) {
				return "" + intValue;
			} else if (isDouble) {
				return "" + doubleValue;
			} else if (isBoolean) {
				return "" + booleanValue;
			} else if (isDate) {
				return DateFormat.getInstance().format(dateValue);
			}
		}
		return null;
	}
}
