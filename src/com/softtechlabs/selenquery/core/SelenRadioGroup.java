package com.softtechlabs.selenquery.core;



import java.util.ArrayList;


public class SelenRadioGroup extends ArrayList<SelenElement> {

	public void SelectByValue(String value){
		for (SelenElement elm : this) {
			if(elm.getValue().equals(value)){
				elm.click();
				return;
			}
		}
	}
	
	
	public void SelectByAttr(String AttrName, String AttrValue){
		for (SelenElement elm : this) {
			if(elm.getAttribute(AttrName).equals(AttrValue)){
				elm.click();
				return;
			}
		}
	}
	
	
	public Should Should(String AttrName, String AttrValue){
		for (SelenElement elm : this) {
			if(elm.getAttribute(AttrName).equals(AttrValue)){
				return elm.should();
			}
		}
		return null;
	}
	
	public Should ShouldNot(String AttrName, String AttrValue){
		for (SelenElement elm : this) {
			if(elm.getAttribute(AttrName).equals(AttrValue)){
				return elm.shouldNot();
			}
		}
		return null;
	}
	
}
