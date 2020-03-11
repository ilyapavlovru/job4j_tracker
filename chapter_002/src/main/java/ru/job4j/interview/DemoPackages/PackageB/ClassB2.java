package ru.job4j.interview.DemoPackages.PackageB;

// нет явно заданного спецификатора public, доступ по умолчанию (в границах пакета)
class ClassB2
{
    int b2; // внутренняя переменная

    // инициализация работает, так как классы в одном пакете PackageB
    ClassB1 cB1 = new ClassB1();
}