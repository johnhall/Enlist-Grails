package enlist.grails

class PointTransaction {
    public static final String TRANSFER = "TXF"
    public static final String VOLUNTEER = "VOL"
    public static final String REDEEM = "RDM"
    User acctOwner
    Date txnDate
    String txnType
    String description
    Integer amount
    static mapping = {
        acctOwner index: "pointTxnIdx,pointTxnIdx1"
        txnDate index: "pointTxnIdx"
    }
    
    static void removeAll(User acctOwner) {
        executeUpdate 'DELETE FROM PointTransaction WHERE acctOwner=:acctOwner', [acctOwner: acctOwner]
    }

    static constraints = {
        txnType inList: [TRANSFER, VOLUNTEER, REDEEM]
    }
}
