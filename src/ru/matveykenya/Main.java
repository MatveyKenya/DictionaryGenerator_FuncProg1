package ru.matveykenya;

import java.io.*;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * Функциональное программирование.
 *
 * Использованы манады в виде Stream API
 * Использование final  объектов
 * Ссылки :: на методы классов
 */

public class Main {
    final static String FILE = "text.txt";
    final static TreeSet<String> dictionary = new TreeSet<>();

    public static void main(String[] args) {
        final String[] text = getTextLines(FILE);
        System.out.println("Всего строк в тексте = " + text.length);
        System.out.println("\nТекст для парсинга:");
        Arrays.stream(text).toList().forEach(System.out::println);
        // разбираем добытый текст выявляя уникальные слова и добавляем в словарь
        for (String sentence : text){
            dictionary.addAll(Arrays.stream(sentenceToUniqueWords(sentence)).toList());
        }
        System.out.println("\nитоговый словарь:");
        dictionary.forEach(System.out::println);
        System.out.println("\nВсего уникальных слов (с разными окончаниями т.к. русскый всёж)- " + dictionary.size());
    }

    /**
     * Read Strings Lines from text file
     * @param file path to file
     * @return String[]
     */
    private static String[] getTextLines(String file) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            return bufferedReader
                    .lines()
                    .filter(x -> x.length() >0)
                    .toArray(String[]::new);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String[0];
    }

    /**
     * parsing the sentence
     * @param sentence the sentence from text
     * @return String[] of unique words in this sentence
     */
    private static String[] sentenceToUniqueWords(String sentence){
        return Arrays.stream(sentence.split(" "))
                .map(x -> x.replaceAll("[^а-яА-Я]",""))
                .filter(x -> x.length() > 0)
                .distinct()
                .toArray(String[]::new);
    }
}
