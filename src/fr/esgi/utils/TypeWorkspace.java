package fr.esgi.utils;

public class TypeWorkspace {

	private int idTypeWorkspace;
	private String labelTypeWorkspace;
	
	public TypeWorkspace()
	{
		
	}
	
	public TypeWorkspace(int idTypeWorkspace, String labelTypeWorkspace)
	{
		this.idTypeWorkspace = idTypeWorkspace;
		this.labelTypeWorkspace = labelTypeWorkspace;
	}
	
	public int getIdTypeWorkspace()
	{
		return idTypeWorkspace;
	}
	
	public void setIdTypeWorkspace(int idTypeWorkspace)
	{
		this.idTypeWorkspace = idTypeWorkspace;
	}
	
	public String getLabelTypeWorkspace()
	{
		return labelTypeWorkspace;
	}
	
	public void setLabelTypeWorkspace(String labelTypeWorkspace)
	{
		this.labelTypeWorkspace = labelTypeWorkspace;
	}
}
