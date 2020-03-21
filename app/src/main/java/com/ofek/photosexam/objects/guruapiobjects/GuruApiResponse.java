package com.ofek.photosexam.objects.guruapiobjects;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class GuruApiResponse{

	@SerializedName("success")
	private boolean success;

	@SerializedName("error_code")
	private int errorCode;

	@SerializedName("message")
	private String message;

	@SerializedName("items")
	private List<PhotoDto> items;

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean isSuccess(){
		return success;
	}

	public void setErrorCode(int errorCode){
		this.errorCode = errorCode;
	}

	public int getErrorCode(){
		return errorCode;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public List<PhotoDto> getItems() {
		return items;
	}

	public GuruApiResponse setItems(List<PhotoDto> items) {
		this.items = items;
		return this;
	}

	@Override
 	public String toString(){
		return 
			"GuruApiResponse{" + 
			"success = '" + success + '\'' + 
			",error_code = '" + errorCode + '\'' + 
			",message = '" + message + '\'' + 
			",items = '" + items + '\'' + 
			"}";
		}
}