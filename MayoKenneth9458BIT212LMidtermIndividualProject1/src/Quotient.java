public class Quotient {
    Polynomial quotientP;
    Polynomial remainderP;
    public Quotient(){
        quotientP = null;
        remainderP = null;
    }
    public void setQuotientP(Polynomial q){
        quotientP = q;
    }
    public void setRemainderP(Polynomial p){
        remainderP = p;
    }
    public Polynomial getQuotientP() {
        return quotientP;
    }
    public Polynomial getRemainderP() {
        return remainderP;
    }
    public String toString() {
        return (" Quotient: " + quotientP.toString() + " Remainder: " + remainderP.toString());
    }
}
