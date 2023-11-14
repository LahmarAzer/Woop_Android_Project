package com.esprit.validationlastwoop;

public class Trip {
    private String imagePath; // Store the image path as a String
    private String titre;
    private String location;
    private String hostName;
    private float rating;
    private int nbFeedback;
    private double lastActivity; // In hours
    private double replyPercentage; // Percentage
    private String bio;
    private String helpDescription;
    private int nbWoopers;
    private String hoursDescription;

    public Trip(long tripId, String titre, String location, String hostName, float rating, int nbFeedback, double lastActivity, double replyPercentage, String bio, String helpDescription, int nbWoopers, String hoursDescription, String typeOfHelp, String languages, String amenities) {
    }

    public Trip() {

    }

    public Trip(long tripId, String titre, String location, String bio, float hostname) {
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getNbFeedback() {
        return nbFeedback;
    }

    public void setNbFeedback(int nbFeedback) {
        this.nbFeedback = nbFeedback;
    }

    public double getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(double lastActivity) {
        this.lastActivity = lastActivity;
    }

    public double getReplyPercentage() {
        return replyPercentage;
    }

    public void setReplyPercentage(double replyPercentage) {
        this.replyPercentage = replyPercentage;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getHelpDescription() {
        return helpDescription;
    }

    public void setHelpDescription(String helpDescription) {
        this.helpDescription = helpDescription;
    }

    public int getNbWoopers() {
        return nbWoopers;
    }

    public void setNbWoopers(int nbWoopers) {
        this.nbWoopers = nbWoopers;
    }

    public String getHoursDescription() {
        return hoursDescription;
    }

    public void setHoursDescription(String hoursDescription) {
        this.hoursDescription = hoursDescription;
    }

    public TypeOfHelp getTypeOfHelp() {
        return typeOfHelp;
    }

    public void setTypeOfHelp(TypeOfHelp typeOfHelp) {
        this.typeOfHelp = typeOfHelp;
    }

    public Language getLanguages() {
        return languages;
    }

    public void setLanguages(Language languages) {
        this.languages = languages;
    }

    public Amenity getAmenities() {
        return amenities;
    }

    public void setAmenities(Amenity amenities) {
        this.amenities = amenities;
    }

    private TypeOfHelp typeOfHelp;
    private Language languages;
    private Amenity amenities;

    // Constructors, getters, and setters for the fields
}
