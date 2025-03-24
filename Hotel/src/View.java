public enum View {
    DIRECT_SEA_VIEW("direct sea view"),
    SIDE_SEA_VIEW("side sea view");

    private String description;

    private View(String description){
        this.description = description;
    }
    public String getDescription(){
        return description;
    }
}
