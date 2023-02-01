package ca.ulaval.glo4003.projet.base.ws.domain.apartment.details;

public class ApartmentDetails {

  private int nbOfHousing;
  private boolean hasCommerce;
  private boolean hasJet;
  private boolean hasAlarm;

  public int getNbOfHousing() {
    return nbOfHousing;
  }

  public void setNbOfHousing(int nbOfHousing) {
    this.nbOfHousing = nbOfHousing;
  }

  public boolean getHasCommerce() {
    return hasCommerce;
  }

  public void setHasCommerce(boolean hasCommerce) {
    this.hasCommerce = hasCommerce;
  }

  public boolean getHasJet() {
    return hasJet;
  }

  public void setHasJet(boolean hasJet) {
    this.hasJet = hasJet;
  }

  public boolean getHasAlarm() {
    return hasAlarm;
  }

  public void setHasAlarm(boolean hasAlarm) {
    this.hasAlarm = hasAlarm;
  }
}
