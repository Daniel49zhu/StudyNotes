public enum SpaceShip {
    SCOUT,CARGO,TRANSPORT,CRUISER,BATTLESHIP,MOTHERSHIP;

    @Override
    public String toString() {
        String id = this.name();
        String lower = id.substring(1).toLowerCase();
        return id.charAt(0) + lower;
    }

    public static void main(String[] args) {
        for(SpaceShip s : SpaceShip.values()){
            System.out.println(s);
        }
    }
}
/* output
Scout
Cargo
Transport
Cruiser
Battleship
Mothership
 */
