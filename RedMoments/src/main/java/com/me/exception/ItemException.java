package com.me.exception;

public class ItemException extends Exception
{
	public ItemException(String message)
	{
		super("ItemException-"+ message);
	}
	
	public ItemException(String message, Throwable cause)
	{
		super("ItemtException-"+ message,cause);
	}
}