import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Book {
  private String title;
  private String genre;
  private int pages;
  private double wordPerPage;
  private Scanner scanner = new Scanner(System.in);

  /**
   * @brief Конструктор по умолчанию
   *
   */

  public Book() {
    title = "title";
    genre = "genre";
    pages = 100;
    wordPerPage = 100;
  }

  /**
   * @brief Конструктор с параметрами
   *
   */

  public Book(String title, String genre, int pages, double wordPerPage) {
    this.title = title;
    this.genre = genre;
    this.pages = pages;
    this.wordPerPage = wordPerPage;
  }

  /**
   * @brief Геттеры/Сеттеры
   *
   */

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    if (title.length() == 0) {
      System.out.printf("%s %s", incorrectStr(), this.title);
    } else {
      this.title = title;
    }
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    if (genre.length() == 0) {
      System.out.printf("%s %s", incorrectStr(), this.genre);
    } else {
      this.genre = genre;
    }
  }

  public int getPages() {
    return pages;
  }

  public void setPages(int pages) {
    if (pages <= 0) {
      System.out.printf("%s %d", incorrectNum(), this.pages);
    } else {
      this.pages = pages;
    }
  }

  public double getWordPerPage() {
    return wordPerPage;
  }

  public void setWordPerPage(double wordPerPage) {
    if (wordPerPage <= 0) {
      System.out.printf("%s %f", incorrectNum(), this.wordPerPage);
    } else {
      this.wordPerPage = wordPerPage;
    }
  }

  /**
   * @brief Предварительная проверка на не пустой список
   *
   */

  public static void prepareForEdit(List<Book> book, Scanner scanner) {
    int choice = 0;
    int size = book.size();
    checkListSize(size);
    if (size != 0) {
      choice = getBookIndex(size, scanner);
      book.get(choice - 1).modify();
    }
  }

  /**
   * @brief Получаем индекс для дальнейшего редактирования
   *
   */

  private static int getBookIndex(int size, Scanner scanner) {
    int choice = 0;
    while (choice <= 0 || choice > size) {
      printIndexFindMenu(size);
      choice = scanner.nextInt();
      scanner.nextLine();
      if (choice <= 0 || choice > size) {
        printErrorInput();
      }
    }
    return choice;
  }

  /**
   * @brief Функция сообщает о пустом списке
   *
   */

  private static void checkListSize(int size) {
    if (size == 0) {
      System.out.printf("\nНет введенных данных\n\n");
    }
  }

  /**
   * @brief Меню для выбора поля для дальнейшего редактирования
   *
   */

  public void modify() {
    int choice = 0;
    while (choice != 6) {
      printAttributes();
      choice = scanner.nextInt();
      scanner.nextLine();
      switch (choice) {
        case 1:
          String title = scanner.nextLine();
          setTitle(title);
          break;
        case 2:
          String genre = scanner.nextLine();
          setGenre(genre);
          break;
        case 3:
          int pages = scanner.nextInt();
          scanner.nextLine();
          setPages(pages);
          break;
        case 4:
          double wordPerPage = scanner.nextDouble();
          scanner.nextLine();
          setWordPerPage(wordPerPage);
          break;
        case 5:
          currentData();
          break;
        case 6:
          break;
        default:
          printErrorInput();
          break;
      }
    }
  }

  /**
   * @brief Проверка списка на пустоту перед сортировкой
   *
   */

  public static void prepareToSortList(List<Book> book, Scanner scanner) {
    int size = book.size();
    checkListSize(size);
    if (size != 0) {
      sortList(book, scanner);
    }
  }

  /**
   * @brief Меню полей для сортировки
   *
   */

  private static void sortList(List<Book> book, Scanner scanner) {
    int choice = 0;
    while (choice != 6) {
      Book.printAttributes();
      choice = scanner.nextInt();
      scanner.nextLine();
      switch (choice) {
        case 1:
          book.sort(Comparator.comparing(Book::getTitle));
          break;
        case 2:
          book.sort(Comparator.comparing(Book::getGenre));
          break;
        case 3:
          book.sort(Comparator.comparingInt(Book::getPages));
          break;
        case 4:
          book.sort(Comparator.comparingDouble(Book::getWordPerPage));
          break;
        case 5:
          output(book);
          break;
        case 6:
          break;
        default:
          printErrorInput();
          break;
      }
    }
  }

  /**
   * @brief Вывод полей каждого объекта из списка
   *
   */

  public static void output(List<Book> book) {
    int size = book.size();
    checkListSize(size);
    for (int i = 0; i < size; i++) {
      System.out.printf("\n%d: %s\n", i + 1, book.get(i));
    }
    if (size != 0) {
      System.out.printf("\n");
    }
  }

  /**
   * @brief Информационные выводы
   *
   */

  private static void printIndexFindMenu(int size) {
    System.out.printf(
        "\nВведите номер книги. Общее количество книг: %d. Отсчет начинается с 1\n", size);
  }

  public static void printMenu() {
    System.out.printf("Введите один из вариантов меню.\n");
    System.out.printf("1. Добавить пустой объект к массиву \n");
    System.out.printf("2. Добавить объект к массиву, заполненный вручную\n");
    System.out.printf("3. Редактировать значения\n");
    System.out.printf("4. Вывод информации про все объекты\n");
    System.out.printf("5. Сортировка массива\n");
    System.out.printf("6. Завершение программы\n");
  }

  private static void printAttributes() {
    System.out.printf("\n1. Название книги\n");
    System.out.printf("2. Жанр\n");
    System.out.printf("3. Количество страниц\n");
    System.out.printf("4. Количестов слов на страницу\n");
    System.out.printf("5. Вывести текущие данные\n");
    System.out.printf("6. Закончить ввод\n");
  }

  private void printEditMenu() {
    System.out.printf("Введите один из вариантов меню.\n");
    System.out.printf("1. Добавить пустой объект к массиву \n");
    System.out.printf("2. Добавить объект к массиву, заполненный вручную\n");
    System.out.printf("3. Редактировать значения\n");
    System.out.printf("4. Вывод информации про все объекты\n");
    System.out.printf("5. Сортировка массива\n");
    System.out.printf("6. Завершение программы\n");
  }

  public void currentData() {
    System.out.printf("\n%s\n", this);
  }

  /**
   * @brief Проверка на корректный ввод данных перед редактированием поля
   *
   */

  private void checkWordPerPage() {}

  public static void printErrorInput() {
    System.out.printf("\nНеверный ввод. Попробуйте еще раз\n");
  }

  private String incorrectStr() {
    return "Введена пустая строка, Оставлены предыдущие данные:";
  }

  private String incorrectNum() {
    return "Введено число меньшее или равное нулю, Оставлены предыдущие данные:";
  }

  private double wordCount(int pages, double wordPerPage) {
    return pages * wordPerPage;
  }

  @Override
  public String toString() {
    return "Title: " + title + " Genre: " + genre + " Pages: " + pages
        + " wordPerPage: " + wordPerPage + " wordCount " + wordCount(pages, wordPerPage);
  }
}
