package by.belgosles.sergei.mdo;

class KindOFTestesTrees {
    private String viewOftestes , diameter, amount, number;

    KindOFTestesTrees(String viewOftestes, String diameter, String amount, String number) {
        this.viewOftestes = viewOftestes;
        this.diameter = diameter;
        this.amount = amount;
        this.number = number;
    }

   public String getViewOftestes() {
        return viewOftestes;
    }

    public void setViewOftestes(String viewOftestes) {
        this.viewOftestes = viewOftestes;
    }

    public String getDiameter() {
        return diameter;
    }

    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
