package ru.job4j.ex;

public class FindEl {
    /**
     * Метод поиска индекса элемента в строковом массиве
     * @param value входной строковый массив
     * @param key ключевое слово для поиска
     * @return индекс найденного элемента
     * todo переделать на for-each
     */
    public static int indexOf(String[] value, String key) throws ElementNotFoundException {
        int rsl = -1;
        int index = 0;
        for (String current : value) {
            if (current != null) {
                if (current.equals(key)) {
                    rsl = index;
                    break;
                }
            }
            index++;
        }

        if (rsl == -1) {
            throw new ElementNotFoundException("Element not found");
        }
        return rsl;
    }

    public static void main(String[] args) {
        try {
            String[] shops = {"Ebay", null, "Amazon", null, "Ozon"};
            int index = indexOf(shops, "Holodilnik");
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }
    }
}