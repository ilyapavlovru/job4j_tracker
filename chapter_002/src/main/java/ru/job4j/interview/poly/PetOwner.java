package ru.job4j.interview.poly;

public class PetOwner {
    public void start() {
        Vet v = new Vet();
        Dog d = new Dog();
        Hippo h = new Hippo();

        v.giveShot(d);  // запускается метод makeNoise() из Dog
        v.giveShot(h);  // запускается метод makeNoise()  из Dog
    }
}
