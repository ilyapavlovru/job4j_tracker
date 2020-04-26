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

    public static boolean sent(String value, String[] abuses) throws ElementAbuseException {
        // if contains throw ElementAbuseException
        return true;
    }

    public static void process(String[] values, String key, String[] abuses) {
        try {
            if (indexOf(values, key) != -1) {
                sent(key, abuses);
            }
        } catch (ElementAbuseException ea) {
            ea.printStackTrace();
        } catch (ElementNotFoundException en) {
            en.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            th.printStackTrace();
        }
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