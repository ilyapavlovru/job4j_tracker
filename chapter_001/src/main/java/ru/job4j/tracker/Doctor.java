package ru.job4j.tracker;

public class Doctor extends Profession {
    private boolean isCertified;
    public Diagnose heal(Pacient pacient) {
        Diagnose diagnose = new Diagnose();
        return diagnose;
    }
}
