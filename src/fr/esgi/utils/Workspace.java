package fr.esgi.utils;

public class Workspace {

	private int idWorkspace;
	private int idTypeWorkspace;
	private Float priceWorkspace;
	private int seatsNumberWorkspace;
	private String descriptionWorkspace;
	private String emailAdmin;
	private String addressWorkspace;
	private String cityWorkspace;
	private int cpWorkspace;
	private String countryWorkspace;
	private Float longitudeWorkspace;
	private Float lattitudeWorkspace;
	
	private Boolean dispoWorkspace;
	private int nbReservation;
	private TypeWorkspace typeWorkspace;

	public Workspace()
	{
		
	}
	
	
	public Workspace(int idWorkspace, int idTypeWorkspace, Float priceWorkspace, int seatsNumberWorkspace, 
			String descriptionWorkspace, String emailAdmin,String addressWorkspace, String cityWorkspace, 
			int cpWorkspace, String countryWorkspace, Float longitudeWorkspace, Float lattitudeWorkspace, 
			int nbReservation, Boolean dispoWorkspace)
	{
		this.idWorkspace = idWorkspace;
		this.idTypeWorkspace = idTypeWorkspace;
		this.priceWorkspace = priceWorkspace;
		this.seatsNumberWorkspace = seatsNumberWorkspace;
		this.descriptionWorkspace = descriptionWorkspace;
		this.emailAdmin = emailAdmin;
		this.addressWorkspace = addressWorkspace;
		this.cityWorkspace = cityWorkspace;
		this.cpWorkspace = cpWorkspace;
		this.countryWorkspace = countryWorkspace;
		this.longitudeWorkspace = longitudeWorkspace;
		this.lattitudeWorkspace = lattitudeWorkspace;
		this.nbReservation = nbReservation;
		this.dispoWorkspace = dispoWorkspace;
	}
	
	public Workspace(int idWorkspace, int idTypeWorkspace, Float priceWorkspace, int seatsNumberWorkspace,
			String cityWorkspace, int nbReservation, Boolean dispoWorkspace)
	{
		this.idWorkspace = idWorkspace;
		this.idTypeWorkspace = idTypeWorkspace;
		this.priceWorkspace = priceWorkspace;
		this.seatsNumberWorkspace = seatsNumberWorkspace;
		this.cityWorkspace = cityWorkspace;
		this.nbReservation = nbReservation;
		this.dispoWorkspace = dispoWorkspace;
	}
	
	public Workspace(int idTypeWorkspace, Float priceWorkspace, int seatsNumberWorkspace, 
			String descriptionWorkspace, String emailAdmin,String addressWorkspace, String cityWorkspace, 
			int cpWorkspace, String countryWorkspace, Float longitudeWorkspace, Float lattitudeWorkspace, 
			int nbReservation, Boolean dispoReservation)
	{
		this.idTypeWorkspace = idTypeWorkspace;
		this.priceWorkspace = priceWorkspace;
		this.seatsNumberWorkspace = seatsNumberWorkspace;
		this.descriptionWorkspace = descriptionWorkspace;
		this.emailAdmin = emailAdmin;
		this.addressWorkspace = addressWorkspace;
		this.cityWorkspace = cityWorkspace;
		this.cpWorkspace = cpWorkspace;
		this.countryWorkspace = countryWorkspace;
		this.longitudeWorkspace = longitudeWorkspace;
		this.lattitudeWorkspace = lattitudeWorkspace;
		this.nbReservation = nbReservation;
		this.dispoWorkspace = dispoReservation;
	}
	
	public int getIdWorkspace() {
		return idWorkspace;
	}

	public void setIdWorkspace(int idWorkspace) {
		this.idWorkspace = idWorkspace;
	}

	public int getIdTypeWorkspace() {
		return idTypeWorkspace;
	}

	public void setIdTypeWorkspace(int idTypeWorkspace) {
		this.idTypeWorkspace = idTypeWorkspace;
	}

	public String getDescriptionWorkspace() {
		return descriptionWorkspace;
	}

	public void setDescriptionWorkspace(String descriptionWorkspace) {
		this.descriptionWorkspace = descriptionWorkspace;
	}

	public int getSeatsNumberWorkspace() {
		return seatsNumberWorkspace;
	}

	public void setSeatsNumberWorkspace(int seatsNumberWorkspace) {
		this.seatsNumberWorkspace = seatsNumberWorkspace;
	}

	public String getEmailAdmin() {
		return emailAdmin;
	}

	public void setEmailAdmin(String emailAdmin) {
		this.emailAdmin = emailAdmin;
	}

	public String getAddressWorkspace() {
		return addressWorkspace;
	}

	public void setAddressWorkspace(String addressWorkspace) {
		this.addressWorkspace = addressWorkspace;
	}

	public String getCityWorkspace() {
		return cityWorkspace;
	}

	public void setCityWorkspace(String cityWorkspace) {
		this.cityWorkspace = cityWorkspace;
	}

	public int getCpWorkspace() {
		return cpWorkspace;
	}

	public void setCpWorkspace(int cpWorkspace) {
		this.cpWorkspace = cpWorkspace;
	}

	public String getCountryWorkspace() {
		return countryWorkspace;
	}

	public void setCountryWorkspace(String countryWorkspace) {
		this.countryWorkspace = countryWorkspace;
	}

	public Float getPriceWorkspace() {
		return priceWorkspace;
	}

	public void setPriceWorkspace(Float priceWorkspace) {
		this.priceWorkspace = priceWorkspace;
	}

	public Float getLongitudeWorkspace() {
		return longitudeWorkspace;
	}

	public void setLongitudeWorkspace(Float longitudeWorkspace) {
		this.longitudeWorkspace = longitudeWorkspace;
	}

	public Float getLattitudeWorkspace() {
		return lattitudeWorkspace;
	}

	public void setLattitudeWorkspace(Float lattitudeWorkspace) {
		this.lattitudeWorkspace = lattitudeWorkspace;
	}

	
	public Boolean getDispoWorkspace()
	{
		return this.dispoWorkspace;
	}
	
	public void setDispoWorkspace(Boolean dispoWorkspace) 
	{
		this.dispoWorkspace = dispoWorkspace;
	}


	public int getNbReservation() {
		return nbReservation;
	}

	public void setNbReservation(int nbReservation) {
		this.nbReservation = nbReservation;
	}
	
	public TypeWorkspace getTypeWorkspace()
	{
		return typeWorkspace;
	}
	
	public void setTypeWorkspace(TypeWorkspace typeWorkspace)
	{
		this.typeWorkspace = typeWorkspace;
	}
	
//	@Override
//	  public String toString() {
//		  return "Desk [id=" + idDesk + ", idTypeDesk=" + idTypeDesk + ", place=" + placeDesk
//				    + ", disponible=" + dispoDesk + ", nombre de places=" + nbPlaceDesk
//				    + ", something=" + somethingDesk + ", prix=" + priceDesk+"]";
//	  }
}
