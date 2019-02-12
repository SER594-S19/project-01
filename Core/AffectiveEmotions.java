package Core;

public class AffectiveEmotions {
    private int shortTermExcitement;
    private int longTermExcitement;
    private int meditation;
    private int frustration;

    public int getShortTermExcitement() {
        return shortTermExcitement;
    }

    public void setShortTermExcitement(int shortTermExcitement) {
        this.shortTermExcitement = shortTermExcitement;
    }

    public int getLongTermExcitement() {
        return longTermExcitement;
    }

    public void setLongTermExcitement(int longTermExcitement) {
        this.longTermExcitement = longTermExcitement;
    }

    public int getMeditation() {
        return meditation;
    }

    public void setMeditation(int meditation) {
        this.meditation = meditation;
    }

    public int getFrustration() {
        return frustration;
    }

    public void setFrustration(int frustration) {
        this.frustration = frustration;
    }

    public int getBoredom() {
        return boredom;
    }

    public void setBoredom(int boredom) {
        this.boredom = boredom;
    }

    public int getEngagement() {
        return engagement;
    }

    public void setEngagement(int engagement) {
        this.engagement = engagement;
    }

    private int boredom;
    private int engagement;
}
