package ru.job4j.interview.demopackages.packageb;

// нет явно заданного спецификатора public, доступ по умолчанию (в границах пакета)
class ClassB1 {

    double b1 = 12.44; // внутренняя переменная

    public static void main(String[] args) {
        // доступ к классу ClassB2 из класса ClassB1
        ClassB2 cB2 = new ClassB2();
        cB2.b2 = 230;

        // здесь возникнет ошибка компиляции:
        // PackageA.ClassA1 cA1 = new PackageA.ClassA1(); // ошибка, ClassA1 объявляется в другом пакете
    }
}