package ru.job4j.interview.demopackages.packagea;

class ClassA2 { // нету явно заданного спецификатора public, доступ по умолчанию
    int a2;

    public static void main(String[] args) {
        // доступ из класса ClassA2 к классу ClassA1 - работает, оба класса в одном пакете
        ClassA1 cA1 = new ClassA1();
        cA1.a1 = 17;

        // PackageB.ClassB1 cB1 = new PackageB.ClassB1(); // а это ошибка компиляции, классы в разных пакетах
    }
}