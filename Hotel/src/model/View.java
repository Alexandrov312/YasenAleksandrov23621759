package model;

/**
 * Енумерация за тип гледка.
 */
public enum View {
    DIRECT_SEA_VIEW("direct sea view"),
    SIDE_SEA_VIEW("side sea view");

    private String description;

    View(String description){
        this.description = description;
    }
    public String getDescription(){
        return description;
    }
}
