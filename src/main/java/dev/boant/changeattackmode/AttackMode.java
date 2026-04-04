package dev.boant.changeattackmode;

public enum AttackMode {
    HOLD,
    TOGGLE;

    public AttackMode next() {
        return this == HOLD ? TOGGLE : HOLD;
    }
}
