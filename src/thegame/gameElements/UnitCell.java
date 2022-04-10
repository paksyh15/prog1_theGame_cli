package thegame.gameElements;

public class UnitCell {
    Unit unit;  //archer, griffin stb.. osztályokból példa objektum
    Integer amount;  // egységek száma ezen a kockán
    Integer edgeHP;  // csak egy egység életét követjük, mert egyesével halnak meg

    public UnitCell(Unit unit, Integer amount) {
        this.unit = unit;
        this.amount = amount;
        this.edgeHP = this.unit.health;
    }
}
