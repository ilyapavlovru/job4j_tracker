package ru.job4j.interview.demopackages.packagea;

class ClassA1 { // нет явно заданного спецификатора public, доступ по умолчанию

    int a1;

    public static void main(String[] args) {
        // доступ к классу Class2 из класса Class1 - корректно, классы в одном пакете
        ClassA2 cA2 = new ClassA2();
        cA2.a2 = 109;


        // этот код не будет компилироваться: классы ClassA1 и ClassB1 в разных пакетах
        // PackageB.ClassB1 cB1 = new PackageB.ClassB1(); // ошибка!
    }
}